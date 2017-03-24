package com.visionet.platform.cooperation.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.visionet.core.aws.SQSService;
import com.visionet.core.exception.BizException;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.service.BaseService;
import com.visionet.core.util.Constants;
import com.visionet.core.util.DateUtil;
import com.visionet.core.util.HttpClient;
import com.visionet.core.util.OrderCreaterUtil;
import com.visionet.core.util.ResourceUtil;
import com.visionet.core.util.ValidateUtils;
import com.visionet.domain.BaseJson;
import com.visionet.platform.cooperation.dto.CarInfoDTO;
import com.visionet.platform.cooperation.dto.CarsModelDTO;
import com.visionet.platform.cooperation.dto.CarsModelListDTO;
import com.visionet.platform.cooperation.dto.JPushDto;
import com.visionet.platform.cooperation.dto.MetaDTO;
import com.visionet.platform.cooperation.dto.OrderCancelDTO;
import com.visionet.platform.cooperation.dto.OrderResultDTO;
import com.visionet.platform.cooperation.dto.PeekFareIncreaseDTO;
import com.visionet.platform.cooperation.dto.PeekFareIncreaseDayDTO;
import com.visionet.platform.cooperation.dto.PeekListDTO;
import com.visionet.platform.cooperation.dto.PollingResultDTO;
import com.visionet.platform.cooperation.mapper.CarUserMapper2;
import com.visionet.platform.cooperation.mapper.CustomerMapper;
import com.visionet.platform.cooperation.mapper.OrderInvoiceMapper;
import com.visionet.platform.cooperation.mapper.OrderMapper2;
import com.visionet.platform.cooperation.mapper.OrderPayDetailMapper2;
import com.visionet.platform.cooperation.mapper.OrderStatusTrackingMapper;
import com.visionet.platform.cooperation.mapper.PushDesMapper;
import com.visionet.platform.cooperation.mapper.SysDictMapper;
import com.visionet.platform.cooperation.model.CarUser;
import com.visionet.platform.cooperation.model.Customer;
import com.visionet.platform.cooperation.model.Order;
import com.visionet.platform.cooperation.model.OrderInvoice;
import com.visionet.platform.cooperation.model.OrderStatusTracking;
import com.visionet.platform.cooperation.model.PushDes;
import com.visionet.platform.cooperation.model.SysDict;
import com.visionet.platform.thirdpartydata.mapper.ThirdPartyCityMapper;
import com.visionet.platform.thirdpartydata.mapper.ThirdPartyOrderMapper;
import com.visionet.platform.thirdpartydata.model.ThirdPartyCity;
import com.visionet.platform.thirdpartydata.model.ThirdPartyOrder;

/**
 * projectName:dzcx_partner
 * author:liusy@visionet.com.cn
 */
@Service
public class OrderService extends BaseService<Order> {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private static final int OPERATOR_SHOUQI = 4;//首汽约车
    private static final String serverUrl = ResourceUtil.getValueBykey("base", "order.server.url");
    private static final String bizUrl = ResourceUtil.getValueBykey("base", "cancleorder.server.url");
    private static final String car_order_pay_with_app = "订单待支付,请选择支付方式进行支付";
    private static final String car_will_start = ResourceUtil.getValueBykey("base", "car.willStart");// 通知乘客,司机已经接到乘客,准备出发
    private static final String yz_car_ready_for_customer_onboard = ResourceUtil.getValueBykey("base", "car.yz.readyForCustomerOnBoard");// yuzu司机到达目的地,等待乘客上车时推送消息给乘客
    private static final String RECIVE_SHOUYUE_ORDER = ResourceUtil.getValueBykey("base", "recive.shouyue.order");// 告诉乘客订单司机代收成功
    private static final String order_payed = ResourceUtil.getValueBykey("base", "car.orderPayed");// 告诉乘客订单司机代收成功
    private static final Long yuyuedanShishidanPoint = Long.valueOf(ResourceUtil.getValueBykey("base", "yuyuedan.shishidan.point"));
    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderMapper2 orderMapper;
    @Autowired
    private OrderStatusTrackingMapper orderStatusTrackingMapper;
    @Autowired
    private ThirdPartyOrderMapper thirdPartyOrderMapper;
    @Autowired
    private OrderInvoiceMapper orderInvoiceMapper;
    @Autowired
    private CarUserMapper2 carUserMapper;
    @Autowired
    private PushDesMapper pushDesMapper;
    @Autowired
    private OrderPayDetailMapper2 orderPayDetailMapper;
    @Autowired
    private ThirdPartyCityMapper thirdPartyCityMapper;

    /**
     * 结算后 – 评价司机
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param evaluateScore
     * @param describe
     */
    public void evaluateDriver(String orderNo, String partnerOrderNo, Integer evaluateScore, String describe) {
        if (StringUtils.isBlank(orderNo)) {
            throw new BizException("订单号不能为空");
        }
        Order order = orderMapper.selectByPrimaryKey(orderNo);
        if (order == null) {
            throw new BizException("该订单不存在");
        }
        if (order.getStatus() == null || order.getStatus() != 2) {
            throw new BizException("该订单不可评价");
        }
        Integer evaluateScore2 = order.getCarUserGrade();
        if (evaluateScore2 != null) {
            throw new BizException("订单已评价不能再次评价！");
        }
        Customer customer = customerMapper.selectByPrimaryKey(order.getCustomerPhone());
        if (customer == null) {
            throw new BizException("乘客不存在，不能评价");
        }
        order.setCarUserGrade(evaluateScore);
        order.setBakstr4(describe);
        orderMapper.updateByPrimaryKeySelective(order);

        CarUser carUser = carUserMapper.selectByPrimaryKey(order.getCarId());
        if (carUser == null) {
            throw new BizException("司机不存在");
        }
        String starStr = carUser.getBakstr2();
        Integer allStar = 0;
        if (StringUtils.isBlank(starStr)) {
            allStar = 0;
        } else {
            allStar = Integer.parseInt(starStr);
        }
        Integer orderNum = carUser.getOrderCount();
        if (orderNum == 0) {
            throw new BizException("司机未完成该订单");
        }
        Integer star = allStar + evaluateScore;
        Double d = (double) star;
        Double avgstar = d / orderNum;
        double value = new BigDecimal(avgstar).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if (value > 5) {
            value = 5;
        }
        carUser.setBakstr2(star.toString());
        carUser.setGrade(value);
        carUserMapper.updateByPrimaryKey(carUser);
    }

    /**
     * 下单前 – 获取车型列表
     *
     * @param cityId
     * @return
     * @throws Exception
     */
    public CarsModelListDTO getCarsModelList(Integer cityId) throws Exception {
        if (cityId == null || "".equals(cityId) || cityId.intValue() == 0) {
            throw new BizException("城市ID不能为空");
        }
        CarsModelListDTO carsModelListDTO = new CarsModelListDTO();

        // 车型
        List<SysDict> list = sysDictMapper.selectByDictTypeAndCityId("车型", cityId);
        if (list != null && !list.isEmpty()) {
            List<CarInfoDTO> carInfos = Lists.newArrayList();
            for (SysDict sysDict : list) {
                if (sysDict != null) {
                    String dictValue = sysDict.getDictValue();
                    if (StringUtils.isNotBlank(dictValue)) {
                        carInfos.add(JSONObject.parseObject(dictValue, CarInfoDTO.class));
                    }
                }
            }
            CarsModelDTO carsModelDTO = new CarsModelDTO();
            carsModelDTO.setCarInfos(carInfos);
            carsModelListDTO.setCarsModels(carsModelDTO);
        }
        // 加价
        List<SysDict> list2 = sysDictMapper.selectByDictTypeAndCityId("加价", cityId);
        if (list2 != null && !list2.isEmpty()) {
            PeekFareIncreaseDTO peekFareIncreaseDTO = new PeekFareIncreaseDTO();
            List<PeekFareIncreaseDayDTO> peekFareIncreaseDays = Lists.newArrayList();
            for (SysDict sysDict : list2) {
                if (sysDict != null) {
                    String dictValue = sysDict.getDictValue();
                    String dictName = sysDict.getDictName();
                    if (StringUtils.isNotBlank(dictValue) && StringUtils.isNotBlank(dictName)) {
                        PeekFareIncreaseDayDTO peekFareIncreaseDayDTO = new PeekFareIncreaseDayDTO();
                        peekFareIncreaseDayDTO.setDay(dictName);
                        peekFareIncreaseDayDTO.setPeekList(JSONObject.parseObject(dictValue, PeekListDTO.class));
                        peekFareIncreaseDays.add(peekFareIncreaseDayDTO);
                    }
                }
            }
            peekFareIncreaseDTO.setPeekFareIncreaseDays(peekFareIncreaseDays);
            carsModelListDTO.setPeekFareIncreases(peekFareIncreaseDTO);
        }

        return carsModelListDTO;
    }

    /**
     * 存储订单
     *
     * @return
     * @throws Exception
     */
    public OrderResultDTO insertOrder(String partnerOrderId, String partnerOrderNo, Integer businessType, Integer orderType, String carType, String startPlace,
                                      String startGps, String endPlace, Double expectedKm, String endGps, String expectedPrice,
                                      String customerPhone, String customerName, String callDate, String bookDate, Integer orderSource,
                                      String city, Integer cityId, String sign, String channel) throws Exception {
        if (!"1".equals(RECIVE_SHOUYUE_ORDER)) {
            throw new BizException("下单失败");
        }

        if (cityId == null || "".equals(cityId)) {
            throw new BizException("下单城市ID不能为空");
        }
        ThirdPartyCity thirdPartyCity = thirdPartyCityMapper.selectByDzcxCityId(cityId, 1);
        if (thirdPartyCity == null) {
            throw new BizException("下单失败");
        }
        Integer reciveOrder = thirdPartyCity.getReciveOrder();
        if (reciveOrder == 0) {
            throw new BizException("下单失败");
        }
        Date orderBookDate = null;
        // ----------------非空参数的为空判断 开始-------------------
        if (StringUtils.isEmpty(partnerOrderId)) {
            throw new BizException("合作方订单号ID");
        }
        if (StringUtils.isEmpty(partnerOrderNo)) {
            throw new BizException("合作方订单号");
        }
        ThirdPartyOrder tpo = thirdPartyOrderMapper.selectOneByPartnerOrderNo(partnerOrderNo);
        if(tpo != null){
        	throw new BizException("合作方订单号已存在");
        }
        if (StringUtils.isEmpty(customerPhone)) {
            throw new BizException("乘客手机号不能为空");
        }
        if (expectedKm == null || "".equals(expectedKm)) {
            throw new BizException("路径计算失败");
        }
        if (expectedPrice == null || "".equals(expectedPrice)) {
            throw new BizException("金额计算失败");
        }
        if (StringUtils.isEmpty(bookDate)) {
            throw new BizException("订单预约时间不能为空");
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                orderBookDate = sdf.parse(bookDate);
            } catch (Exception e) {
                throw new BizException("订单预约时间格式不正确，yyyy-MM-dd HH:mm:ss");
            }
            if (orderBookDate.before(new Date())) {
                throw new BizException("订单预约时间不能小于当前时间");
            }
        }
        if (orderType == null || "".equals(orderType)) {
            throw new BizException("订单类型有误");
        }

        if (StringUtils.isEmpty(city)) {
            throw new BizException("下单城市不能为空");
        }
        if (businessType == null || "".equals(businessType)) {
            throw new BizException("下单城市类型不能为空");
        }
        // ----------------非空参数的为空判断 结束-------------------

        // -------------验证乘客是否已经注册，未注册则进行预注册 开始------------------
        Customer customer = customerMapper.selectByPrimaryKey(customerPhone);
        if (customer == null) {
            // 用户不存在，注册
            Customer customer_new = new Customer();
            customer_new.setPhone(customerPhone);
            customer_new.setName(customerName);
            customer_new.setCreateDate(new Date());
            customer_new.setDelFlag(1);
            customer_new.setIsValid(0);
            customer_new.setLevel(1);
            customer_new.setIsApp(0);// 0未在APP端注册
            customer_new.setStatus(4);// 4待激活
            customer_new.setIsLogin(0);// 1已登录，0未登录
            customer_new.setSource("5");// 5首汽注册
            customer_new.setCity(city);
            customer_new.setCityId(cityId);
            customerMapper.insertSelective(customer_new);
        }
        // -----------------验证乘客是否已经注册，未注册则进行预注册 结束---------------

        // ----------------------存储订单 开始---------------------
        OrderResultDTO orderResultDTO = new OrderResultDTO();
        Order tOrder = new Order();
        Date date = new Date();
        String orderID = OrderCreaterUtil.createrOrder(businessType + "");
        tOrder.setOrderId(orderID);
        tOrder.setBusinessType(businessType);
        tOrder.setOrderType(orderType);// 6 首汽
        tOrder.setOrderSource(4);// 4 首汽
        tOrder.setCarType(carType);
        tOrder.setCarNumber(1);
        tOrder.setStartPlace(startPlace);
        tOrder.setStartGps(startGps);
        tOrder.setEndPlace(endPlace);
        tOrder.setEndGps(endGps);
        tOrder.setCustomerPhone(customerPhone);
        tOrder.setCustomerName(customerName);
        tOrder.setCallDate(date);// 叫车时间使用当前的系统时间
        tOrder.setStatus(0);// 状态0创建
        tOrder.setExpectedKm(expectedKm.floatValue());
        tOrder.setVirtual(0f);
        tOrder.setExpectedPrice(Float.valueOf(expectedPrice));
        tOrder.setIncreasePrice(0.0);
        tOrder.setIncreaseType(0);// 加价状态（0未加价，1扣加价，2还加价）
        tOrder.setCity(city);
        tOrder.setCityId(cityId);
        tOrder.setBookDate(orderBookDate);

        orderMapper.insertSelective(tOrder);// 调用增加数据方法

        SQSService.put(JSONObject.toJSONString(tOrder)); // 存入aws SQS

        // 插入一条订单状态信息
        OrderStatusTracking orderStatusTracking = new OrderStatusTracking();
        orderStatusTracking.setOrderId(orderID);
        orderStatusTracking.setCustomerPhone(customerPhone);
        orderStatusTracking.setBusinessType(businessType);
        orderStatusTracking.setNewStatus(0);// 0新建订单
        orderStatusTracking.setCreateDate(date);
        orderStatusTracking.setOperator(0);// 乘客
        orderStatusTrackingMapper.insertSelective(orderStatusTracking);

        // 插入一条第三方订单
        Integer thirdPartyOrderType = 1;// 0 实时单 1 预约单
        Long minutes = DateUtil.minusTime(new Date(), orderBookDate);
        if (yuyuedanShishidanPoint > minutes) {
            thirdPartyOrderType = 0;
        }
        ThirdPartyOrder thirdPartyOrder = new ThirdPartyOrder();
        thirdPartyOrder.setMerchantId(1);// 1首汽
        thirdPartyOrder.setSource(1);// 订单来源（0：大众出行、1：首汽约车）
        thirdPartyOrder.setOrderId(orderID);// 大众出行订单号
        thirdPartyOrder.setPartnerOrderId(partnerOrderId);
        thirdPartyOrder.setPartnerOrderNo(partnerOrderNo);
        thirdPartyOrder.setCreateDate(new Date());
        thirdPartyOrder.setOrderType(thirdPartyOrderType);
        thirdPartyOrder.setNotice(0);
        thirdPartyOrderMapper.insertSelective(thirdPartyOrder);

        // ----------------------存储订单 结束---------------------

        orderResultDTO.setOrderNo(orderID);
        return orderResultDTO;
    }

    /**
     * 下单后 – 更新订单状态
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param orderStatus
     * @param sign
     * @param channel
     * @author 秦朝胜
     *//*
    public void updateOrderStatus(String orderNo, String partnerOrderNo, String orderStatus, String sign, String channel) {
        if (StringUtils.isEmpty(orderNo)) {
            throw new BizException("大众出行订单号不能为空");
        }
        if (StringUtils.isEmpty(partnerOrderNo)) {
            throw new BizException("合作方订单号不能为空");
        }
        if (StringUtils.isEmpty(orderStatus)) {
            throw new BizException("订单状态码不能为空");
        }
        if (!ValidateUtils.checkIsInt(orderStatus)) {
            throw new BizException("订单状态码非整型");
        }
        Order result = orderMapper.selectByPrimaryKey(orderNo);
        Order order = new Order();
        order.setOrderId(orderNo);
        order.setStatus(Integer.valueOf(orderStatus));
        order.setUpdateDate(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
        OrderStatusTracking orderStatusTracking = new OrderStatusTracking();
        orderStatusTracking.setOrderId(orderNo);
        orderStatusTracking.setCustomerPhone(result.getCustomerPhone());
        orderStatusTracking.setBusinessType(result.getBusinessType());
        orderStatusTracking.setPreStatus(result.getStatus());
        orderStatusTracking.setNewStatus(Integer.valueOf(orderStatus));
        orderStatusTracking.setCreateDate(new Date());
        orderStatusTracking.setOperator(OPERATOR_SHOUQI);//首汽约车
        orderStatusTrackingMapper.insertSelective(orderStatusTracking);
    }*/

    /**
     * 下单后 –结算通知
     *
     * @param sign
     * @param channel
     * @param orderNo
     * @param partnerOrderNo
     * @param payType
     * @author 秦朝胜
     */
    public void settlementNotice(String sign, String channel, String orderNo, String partnerOrderNo, String payType, BigDecimal payAmount) {
    	if (StringUtils.isEmpty(orderNo)) {
            throw new BizException("大众出行订单号不能为空");
        }
        if (StringUtils.isEmpty(partnerOrderNo)) {
            throw new BizException("合作方订单号不能为空");
        }
        if (StringUtils.isEmpty(payType)) {
            throw new BizException("订单支付类型不能为空");
        }
        if (!ValidateUtils.checkIsInt(payType)) {
            throw new BizException("订单支付类型非整型");
        }
        if("2".equals(payType)){// 司机代收
        	int amount = payAmount.compareTo(BigDecimal.ZERO);
            if (amount == 0) {
                throw new BizException("司机代付金额不能为0");
            }
        }
        // 修改合作订单记录
        ThirdPartyOrder tpOrder = new ThirdPartyOrder();
        tpOrder.setOrderId(orderNo);
        tpOrder.setPartnerOrderNo(partnerOrderNo);
        tpOrder.setUpdateDate(new Date());
        if(Integer.valueOf(payType) == 1){// 乘客支付
        	tpOrder.setPayType(Integer.valueOf(payType));
        	tpOrder.setFinishDate(new Date());
        	// 当支付类型为乘客支付时，修改订单表中订单状态为已完成
        	Order order = new Order();
        	order.setStatus(2);// 已完成
        	order.setFinishDate(new Date());
        	order.setOrderId(orderNo);
            order.setUpdateDate(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        }
        if(Integer.valueOf(payType) == 2){// 司机代收
        	// TODO 通知司机代收车费
        	tpOrder.setPayType(Integer.valueOf(payType));
        	tpOrder.setPayAmount(payAmount);
        }
        thirdPartyOrderMapper.updateBySelective(tpOrder);
    }

    /**
     * 取消订单
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param cancelType
     * @param reason
     * @author 房忍
     */
    public void cancelOrder(String orderNo, String partnerOrderNo, Integer cancelType, BigDecimal cancelAmount, String reason) {
        if (StringUtils.isEmpty(orderNo)) {
            throw new BizException("订单号不能为空");
        }
        if (StringUtils.isEmpty(partnerOrderNo)) {
            throw new BizException("合作方订单号不能为空");
        }
        if (cancelType == null || cancelType.intValue() == 0) {
            throw new BizException("取消类型不能为空");
        }

        ThirdPartyOrder tpo = thirdPartyOrderMapper.selectByOrderIdAndPartnerOrderNo(orderNo, partnerOrderNo);
        if (tpo == null) {
            throw new BizException("订单号不是合作方订单");
        }

        Order order = orderMapper.selectByPrimaryKey(orderNo);
        if (order == null) {
            throw new BizException("该订单不存在");
        }
        Integer status = order.getStatus();
        if ("1".equals(order.getBusinessType())) {
            if (status == 8 || status == 9 || status == 11 || status == 12) {
                throw new BizException("10", "订单不能取消");
            }
        }
        if (status == 2) {
            throw new BizException("11", "取消异常");
        }
        if (status == 3) {
            throw new BizException("11", "取消异常");
        }
        //乘客取消
        if (cancelType.intValue() == 1) {
            BaseJson<OrderCancelDTO> baseJson = new BaseJson<>();
            OrderCancelDTO orderCancelDTO = new OrderCancelDTO();
            List<String> orderIds = Lists.newArrayList();
            orderIds.add(orderNo);
            orderCancelDTO.setOrderIds(orderIds);
            orderCancelDTO.setType(1);// 是否强制取消 0否，1是
            orderCancelDTO.setSourceType(4);// 操作人0：乘客 1：司机 2：订单服务器 3：后台 4：首约
            orderCancelDTO.setDescription(reason);
            baseJson.setBody(orderCancelDTO);

            String ret = HttpClient.post(serverUrl + bizUrl, JSONObject.toJSONString(baseJson));
            if (StringUtils.isBlank(ret)) {
                throw new BizException("11", "取消异常");
            }
            JSONObject json = JSONObject.parseObject(ret);
            if (json.containsKey("success")) {
                if (!"0".equals(json.getString("success"))) {
                    throw new BizException(json.getString("msg"));
                }
                // 取消成功
            } else {
                throw new BizException("11", "取消异常");
            }

            tpo.setCustomerCancelCharge(cancelAmount);
            tpo.setUpdateDate(new Date());
            Integer num = thirdPartyOrderMapper.updateByPrimaryKeySelective(tpo);
            if (num != 1) {
                throw new BizException("更新违约金额异常");
            }
        } else if (cancelType.intValue() == 2) {
            RedisUtil.setData(REDIS_ORDER_STATUS + order.getOrderId(), ORDER_STATUS_CANCLE_VALUE, 3 * 60);
            RedisUtil.delData(GRAP_ORDER_RESULT + orderNo);
            log.info("=====》司机取消：{}", order.getOrderId());
        } else if (cancelType.intValue() == 3) {
            RedisUtil.setData(REDIS_ORDER_STATUS + order.getOrderId(), ORDER_STATUS_CANCLE_VALUE, 3 * 60);
            RedisUtil.delData(GRAP_ORDER_RESULT + orderNo);
            BaseJson<OrderCancelDTO> baseJson = new BaseJson<>();
            OrderCancelDTO orderCancelDTO = new OrderCancelDTO();
            List<String> orderIds = Lists.newArrayList();
            orderIds.add(orderNo);
            orderCancelDTO.setOrderIds(orderIds);
            orderCancelDTO.setType(1);// 是否强制取消 0否，1是
            orderCancelDTO.setSourceType(4);// 操作人0：乘客 1：司机 2：订单服务器 3：后台 4：首约
            orderCancelDTO.setDescription(reason);
            baseJson.setBody(orderCancelDTO);

            String ret = HttpClient.post(serverUrl + bizUrl, JSONObject.toJSONString(baseJson));
            if (StringUtils.isBlank(ret)) {
                throw new BizException("11", "取消异常");
            }
            log.info("=====》其他取消：{}", order.getOrderId());
        }

        // 乘客（一天之内取消三次）处理----暂时不做处理
        log.info("订单：" + orderNo + "取消成功");
    }
    public static final String ORDER_STATUS_CANCLE_VALUE = "cancle";// 订单取消状态

    /**
     * 开发票申请
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param invoiceTitle
     * @param invoiceContent
     * @param address
     * @param postCode
     * @param addressee
     * @param phone
     * @param amount
     * @param sign
     * @param channel
     * @author fangren
     */
    public void postInvoice(String orderNo, String partnerOrderNo, String invoiceTitle, String invoiceContent,
                            String address, String postCode, String addressee, String phone,
                            BigDecimal amount, String sign, String channel) {
        if (StringUtils.isEmpty(orderNo)) {
            throw new BizException("订单号不能为空");
        }
        if (StringUtils.isEmpty(partnerOrderNo)) {
            throw new BizException("合作方订单号不能为空");
        }
        ThirdPartyOrder tpo=thirdPartyOrderMapper.selectByOrderIdAndPartnerOrderNo(orderNo,partnerOrderNo);
        if(tpo == null){
    		throw new BizException("订单号不是合作方订单");
        }

        Order order = orderMapper.selectByPrimaryKey(orderNo);
        if (order == null) {
            throw new BizException("该订单不存在");
        }
        if (StringUtils.isEmpty(phone)) {
            throw new BizException("手机号不能为空");
        }
        if (StringUtils.isEmpty(address)) {
            throw new BizException("收货地址不能为空");
        }
        if (StringUtils.isEmpty(invoiceTitle)) {
            throw new BizException("发票抬头不能为空");
        }
        // Customer customer = customerMapper.selectByPrimaryKey(phone);
        // Double invoiceBalance = customer.getInvoiceBalance();
        // Double invailInvoice = invoiceBalance - moneySum;
        // if (invailInvoice < 0) {
        // throw new BizException("可开发票余额不足");
        // }
        // customer.setInvoiceBalance(invailInvoice);
        // customerMapper.updateByPrimaryKeySelective(customer);

        if (order.getIsInvoice() != 0) {
            throw new BizException("该订单已经申请开票");
        }
        // -----------新的开发票逻辑开始----------
        OrderInvoice orderInvoice = new OrderInvoice();
        orderInvoice.setNumber(1);// 张数
        orderInvoice.setAmount(amount.doubleValue());// 面额
        orderInvoice.setOrderId(orderNo);// 订单id串（中间以,隔开）
        orderInvoice.setPhone(phone);// 发票申请人
        orderInvoice.setTotalAmount(amount.doubleValue());// 总额
        orderInvoice.setReceiver(addressee);// 接收人
        orderInvoice.setCompanyName(invoiceTitle);// 公司名称
        orderInvoice.setApplyDate(new Date());
        orderInvoice.setReceiverPhone(phone);
        orderInvoice.setReceiverAddress(address);
        orderInvoice.setInvoiceType(1);//设置0:个人发票，1：公司发票
        orderInvoice.setStatus(1);// 新申请
        orderInvoice.setArea(order.getCity());// 地区
        orderInvoice.setZipCode(postCode);// 邮编
        orderInvoice.setInvoiceDescription(invoiceContent);// 描述
        Integer num = orderInvoiceMapper.insertSelective(orderInvoice);
        if (num != 1) {
            throw new BizException("申请开票异常");
        }
        // -----------新的开发票逻辑结束----------

        // 修改订单是否开发票的状态
        order.setIsInvoice(1);// 是否开票：0 未开票  ; 1 已开票
        order.setUpdateDate(new Date());
        int j = orderMapper.updateByPrimaryKeySelective(order);
        if (j != 1) {
            throw new BizException("更新订单开发票状态异常，申请开票异常");
        }
    }

    public boolean syncOrderInformation(String orderId, Integer driverId) {
        CarUser cu = carUserMapper.selectByPrimaryKey(driverId);
        if (cu == null) {
            throw new BizException("未获取到指定司机");
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            throw new BizException("未获取到指定订单信息");
        }
        order.setCarId(cu.getId());
        order.setCarUserPhone(cu.getPhone());
        order.setCarUserName(cu.getName());
        if (!(orderMapper.updateByPrimaryKeySelective(order) > 0)) {
            throw new BizException("订单信息修改失败");
        }
        return true;
    }

    public void checkSyncOrderInfoDTO(String orderNo, String partnerOrderNo, Integer driverId, String driverName,
                                      String vehicleLicense, String driverPhone, String carModel, String driverRate, String driverPhoto,
                                      String latitude, String longitude, String sign, String channel) {
        if (StringUtils.isBlank(orderNo)) {
            throw new BizException("orderNo参数为空");
        }
        if (StringUtils.isBlank(partnerOrderNo)) {
            throw new BizException("partnerOrderNo参数为空");
        }
        if (driverId == null) {
            throw new BizException("driverId参数为空");
        }
        if (StringUtils.isBlank(driverName)) {
            throw new BizException("driverName参数为空");
        }
        if (StringUtils.isBlank(vehicleLicense)) {
            throw new BizException("vehicleLicense参数为空");
        }
        if (StringUtils.isBlank(driverPhone)) {
            throw new BizException("driverPhone参数为空");
        }
        if (StringUtils.isBlank(carModel)) {
            throw new BizException("carModel参数为空");
        }
        if (StringUtils.isBlank(driverRate)) {
            throw new BizException("driverRate参数为空");
        }
        if (StringUtils.isBlank(driverPhoto)) {
            throw new BizException("driverPhoto参数为空");
        }
        if (StringUtils.isBlank(latitude)) {
            throw new BizException("latitude参数为空");
        }
        if (StringUtils.isBlank(longitude)) {
            throw new BizException("longitude参数为空");
        }
        if (StringUtils.isBlank(sign)) {
            throw new BizException("sign参数为空");
        }
        if (StringUtils.isBlank(channel)) {
            throw new BizException("channel参数为空");
        }
    }

    /*public void reassignmentNotice(String orderNo, String partnerOrderNo, Integer driverId, String driverName,
                                   String vehicleLicense, String driverPhone, String carModel, Integer driverRate, String driverPhoto, String latitude,
                                   String longitude, String sign, String channel) {

        if (StringUtils.isEmpty(orderNo)) {
            throw new BizException("大众出行订单号不能为空");
        }
        if (driverId == null) {
            throw new BizException("司机ID不能为空");
        }
        if (StringUtils.isEmpty(driverName)) {
            throw new BizException("司机姓名不能为空");
        }
        if (StringUtils.isEmpty(vehicleLicense)) {
            throw new BizException("司机车牌不能为空");
        }
        if (StringUtils.isEmpty(driverPhone)) {
            throw new BizException("司机手机号不能为空");
        }
        if (driverRate == null) {
            throw new BizException("司机评分不能为空");
        }
        if (StringUtils.isEmpty(carModel)) {
            throw new BizException("车型不能为空");
        }
        if (StringUtils.isEmpty(driverPhoto)) {
            throw new BizException("司机头像URL不能为空");
        }

        CarUser carUserParameter = new CarUser();
        carUserParameter.setId(driverId);

        Order orderParameter = new Order();
        orderParameter.setOrderId(orderNo);
        Order order = orderMapper.selectOne(orderParameter);
        CarUser selectOne = carUserMapper.selectOne(carUserParameter);
        if (selectOne == null) {
            carUserParameter.setPhone(driverPhone);
            carUserParameter.setName(driverName);
            carUserParameter.setCarNumber(vehicleLicense);
            carUserParameter.setType(0);//类型(0自营，1非自营)
            carUserParameter.setDriverSource(1);//司机来源(0:默认,1:首汽)
            carUserParameter.setCity(order.getCity());
            carUserParameter.setCityId(order.getCityId());
            carUserParameter.setDelFlag(1);
            carUserParameter.setGrade(Double.parseDouble(driverRate.toString()));
            carUserParameter.setStatus(1);//状态0无效，1正常，2锁定，3拉黑, 4待激活,5已退款,6停运 7:修改信息已审核
            carUserParameter.setRegisterDate(new Date());
            carUserParameter.setHeadPic(driverPhoto);
            if (carUserMapper.insertSelective(carUserParameter) != 1) {
                throw new BizException("司机注册失败");
            }

        }

        orderParameter.setCarId(driverId);
        orderParameter.setCarType(carModel);
        orderParameter.setCarUserPhone(driverPhone);
        orderParameter.setCarUserName(driverName);
        orderParameter.setUpdateDate(new Date());
        orderParameter.setStatus(1);//状态0创建，1已接单，2已完成，3取消，4放空，5无供，6超时，7强制派单中,8待支付，9到达目的，未支付, 10取消(车主取消),11开始计价,12计价完成,13强派拒绝,14司机到达出发地,15出租车待支付
        if (orderMapper.updateByPrimaryKeySelective(orderParameter) != 1) {
            throw new BizException("改派订单失败");
        }

        //插入订单状态信息
        OrderStatusTracking orderStatusTracking = new OrderStatusTracking();
        orderStatusTracking.setOrderId(orderNo);
        orderStatusTracking.setCarUserPhone(driverPhone);
        orderStatusTracking.setCustomerPhone(order.getCustomerPhone());
        orderStatusTracking.setBusinessType(order.getBusinessType());
        orderStatusTracking.setPreStatus(order.getStatus());
        orderStatusTracking.setNewStatus(1);
        orderStatusTracking.setCreateDate(new Date());
        orderStatusTracking.setOperator(4);//操作人0：乘客，1：司机，2：订单服务器 3：后台，4：首汽约车
        orderStatusTrackingMapper.insertSelective(orderStatusTracking);
    }*/

    /**
     * 轮询订单状态
     * @param orderNo
     * @param partnerOrderNo
     * @param sign
     * @param channel
     * @return
     */
    public PollingResultDTO pollingOrder(String orderNo, String partnerOrderNo, String sign, String channel) {
        if (orderNo == null) {
            throw new BizException("参数orderNo为空");
        }
        if (partnerOrderNo == null) {
            throw new BizException("参数partnerOrderNo为空");
        }
        if (sign == null) {
            throw new BizException("参数sign为空");
        }
        if (channel == null) {
            throw new BizException("参数channel为空");
        }
        Order order = orderMapper.selectByPrimaryKey(orderNo);
        if (order == null) {
            throw new BizException("未查询到此订单信息");
        }
        PollingResultDTO resultDTO = new PollingResultDTO();
        resultDTO.setOrderNo(order.getOrderId());
        resultDTO.setOrderStatus(order.getStatus().toString());
        if (order.getCarId() != null) {
            CarUser carUser = carUserMapper.selectByPrimaryKey(order.getCarId());
            if (carUser == null) {
                throw new BizException("未查询到该订单司机信息");
            }
            resultDTO.setDriverId(order.getCarId().toString());
            resultDTO.setDriverName(carUser.getName());
            resultDTO.setDriverPhone(carUser.getPhone());
            resultDTO.setStartAddr(order.getStartPlace());
            resultDTO.setEndAddr(order.getEndPlace());
            resultDTO.setCarModel(carUser.getCarType().toString());
            resultDTO.setVehicleLicense(carUser.getCarNumber());
        }
        return resultDTO;
    }

	public void payNotify(String orderNo, BigDecimal amount) {
		if (orderNo == null) {
			throw new BizException("参数orderNo为空");
		}
		if (amount == null) {
			throw new BizException("参数amount为空");
		}
		if(amount.compareTo(BigDecimal.valueOf(0))!=1){//-1为小于 0为等于 1为大于  如需要支付金额不大于0则生成异常信息
			throw new BizException("需付款金额不大于0");
		}
		Order order = orderMapper.selectByPrimaryKey(orderNo);
		if (order == null) {
            throw new BizException("未查询到此订单信息");
        }
		String customerPhone = order.getCustomerPhone();
		if(customerPhone != null){
			Customer customer = customerMapper.selectByPrimaryKey(customerPhone);
			if(customer==null){
				throw new BizException("未查询到乘客信息");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("phone", customerPhone);
			map.put("userType", "2");
			List<PushDes> pushDesList = pushDesMapper.selectByPhoneAndUserType(map);
            if (pushDesList == null || pushDesList.isEmpty()) {
                throw new BizException("乘客不存在");
            }
            PushDes pushDes = pushDesList.get(0);
            if (pushDes == null || StringUtils.isBlank(pushDes.getChannelId())) {
                throw new BizException("乘客不存在");
            }
            String channelId = pushDes.getChannelId();
			BaseJson<JPushDto> JPushDtoJson = new BaseJson<JPushDto>();
			JPushDto jPushdto = new JPushDto();
			JSONObject contentJson = new JSONObject();
			contentJson.put("type", "orderNeedPay");
			contentJson.put("content", car_order_pay_with_app);
			contentJson.put("orderId", orderNo);
			contentJson.put("money", amount);
			jPushdto.setContent(contentJson);
			List<String> desList = new ArrayList<String>();
			desList.add(channelId);
			jPushdto.setDes(desList);
			jPushdto.setAlert(car_order_pay_with_app);
			jPushdto.setTimeToLive(6000);
			jPushdto.setType(1);// 1通知2透传
			jPushdto.setUserType(2);// 用户类型 1:出租车司机，2：乘客主，3:约租车司机
			jPushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
			JPushDtoJson.setBody(jPushdto);
			push2socket(JPushDtoJson);// 调用socket的推送
		}
	}

	/**
	 * push to customer
	 */
	public static JSONObject push2socket(BaseJson<JPushDto> jPushDtoJson) throws BizException {
		String push2socket = null;
		push2socket = ResourceUtil.getValueBykey("base", "yzc_car_transport_info");
		String jso = HttpClient.post(push2socket, JSONObject.toJSONString(jPushDtoJson));
		if (jso == null) {
			throw new BizException("服务器访问失败");
		}
		JSONObject json = JSONObject.parseObject(jso);
		return json;
	}

    /**
     *  首约回调 – 司机接单
     *
     * @param meta
     * @author 秦朝胜
     */
    public void carUserOrderTaking(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已接单
    	if(result.getStatus() == 1){
    		throw new BizException("订单已接单");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	// 判断首约司机是否在大众平台注册
    	String carType = "";
    	CarUser carUserResult = carUserMapper.selectByParam(metaDto.getDriverInfo().getDriverId(),1);
    	if(carUserResult == null){
            carUserResult = new CarUser();
            carUserResult.setDriverId(metaDto.getDriverInfo().getDriverId());// 第三方司机Id
            carUserResult.setDriverSource(1); // 司机来源：首汽
            carUserResult.setName(metaDto.getDriverInfo().getName());// 司机姓名
            carUserResult.setPhone(metaDto.getDriverInfo().getPhone());// 司机手机号
            carUserResult.setCarNumber(metaDto.getDriverInfo().getLicensePlates());// 车牌号码
            carUserResult.setGrade(Double.valueOf(metaDto.getDriverInfo().getDriverRate()));
            carUserResult.setBusinessType(1);// 专车
            carUserResult.setType(1);// 加盟
            carUserResult.setRegisterDate(new Date());// 注册时间
            carUserResult.setIsValid(1);// 已认证
            carUserResult.setCity(result.getCity());// 默认司机城市为订单城市
            carUserResult.setCityId(result.getCityId());// 默认司机城市Id为订单城市Id
            carUserResult.setHeadPic(metaDto.getDriverInfo().getPhotoSrc());// 头像信息
            carUserResult.setCompany("首汽约车");
            carUserResult.setStatus(1);// 正常
            carUserResult.setDelFlag(1);// 有效
        	if("34".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(0);
                carType = "舒适型";
    		}
    		if("35".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(1);
                carType = "商务型";
    		}
    		if("41".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(2);
                carType = "豪华型";
    		}
    		carUserMapper.insertUseGeneratedKeys(carUserResult);
    		order.setCarId(carUserResult.getId());
    		order.setCarUserName(metaDto.getDriverInfo().getName());
        	order.setCarUserPhone(metaDto.getDriverInfo().getPhone());
    	}else{
    	    // 修改司机实时信息
            carUserResult.setName(metaDto.getDriverInfo().getName());// 司机姓名
            carUserResult.setPhone(metaDto.getDriverInfo().getPhone());// 司机手机号
            carUserResult.setCarNumber(metaDto.getDriverInfo().getLicensePlates());// 车牌号码
            carUserResult.setBusinessType(1);// 专车
            carUserResult.setType(1);// 加盟
            carUserResult.setRegisterDate(new Date());// 注册时间
            carUserResult.setIsValid(1);// 已认证
            carUserResult.setCity(result.getCity());// 默认司机城市为订单城市
            carUserResult.setCityId(result.getCityId());// 默认司机城市Id为订单城市Id
            carUserResult.setHeadPic(metaDto.getDriverInfo().getPhotoSrc());// 头像信息
            carUserResult.setCompany("首汽约车");
            carUserResult.setStatus(1);// 正常
            carUserResult.setDelFlag(1);// 有效
            carUserResult.setGrade(Double.valueOf(metaDto.getDriverInfo().getDriverRate()));
            if("34".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(0);
                carType = "舒适型";
            }
            if("35".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(1);
                carType = "商务型";
            }
            if("41".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(2);
                carType = "豪华型";
            }
            carUserMapper.updateByPrimaryKey(carUserResult);
    		order.setCarId(carUserResult.getId());
    		order.setCarUserName(carUserResult.getName());
        	order.setCarUserPhone(carUserResult.getPhone());
    	}
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("accepted".equals(metaDto.getStatus())){
    		order.setStatus(1);// 已接单
			order.setOrderStartDate(new Date());
    	}
    	order.setCarUserChannel(1);// 1：首汽约车
    	order.setUpdateDate(new Date());
		order.setOrderStartDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order);
    	// 添加订单状态历史记录
    	addOrderStatusDetail(result, 1);
        String orderId = metaDto.getPartnerOrderNo();
        // 在redis中添加司机接单成功信息
        RedisUtil.setData("CARUSER_ORDER_TAKING_" + metaDto.getPartnerOrderNo(), metaDto.getPartnerOrderNo(), 60 * 10);
        // TODO: liusy 16/7/18 17:06  刪除预约单
        RedisUtil.delData("bkOrderData-" + result.getOrderId());

        RedisUtil.delData("yydkd-" + orderId);// 删除预约单看单的人的信息
        RedisUtil.delData("yydkdReject-" + orderId);
        RedisUtil.delData("yywfGrab-" + orderId);
        RedisUtil.setData(REDIS_ORDER_STATUS + order.getOrderId(), "grabed", 20 * 60);
        RedisUtil.setData(SHOUYUE_RECIVE_KEY + order.getOrderId(), order.getOrderId() + ";" + carUserResult.getId(), 20 * 60);

        // TODO: 16/7/7 抢单成功后给乘客发短信
        String carNumber = StringUtils.isBlank(carUserResult.getCarNumber()) ? ""
                : carUserResult.getCarNumber().substring(carUserResult.getCarNumber().length() - 4,
                carUserResult.getCarNumber().length());
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("phone", result.getCustomerPhone());
            map.put("userType", "2");
            List<PushDes> pushDesList = pushDesMapper.selectByPhoneAndUserType(map);
            if (pushDesList == null || pushDesList.isEmpty()) {
                throw new BizException("乘客不存在");
            }
            PushDes pushDes = pushDesList.get(0);
            if (pushDes == null || StringUtils.isBlank(pushDes.getChannelId())) {
                throw new BizException("乘客不存在");
            }
            // TODO: 16/7/7 给乘客推送 有人接单了
            String channelId = pushDes.getChannelId();
            BaseJson<JPushDto> JPushDtoJson = new BaseJson<>();
            JPushDto jPushdto = new JPushDto();
            JSONObject contentJson = new JSONObject();
            contentJson.put("type", "ok");
            contentJson.put("orderId", orderId);
            jPushdto.setContent(contentJson);
            List<String> desList = new ArrayList<String>();
            desList.add(channelId);
            jPushdto.setDes(desList);
            jPushdto.setAlert("有司机接单了");
            jPushdto.setTimeToLive(6000);
            jPushdto.setType(1);// 1通知2透传
            jPushdto.setUserType(2);// 用户类型 1:出租车司机，2：乘客主，3:约租车司机
            jPushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
            JPushDtoJson.setBody(jPushdto);
            push2socket(JPushDtoJson);// 调用socket的推送

            String name = StringUtils.isBlank(carUserResult.getName()) ? "" : carUserResult.getName().substring(0, 1);
            sendBySMS(result.getCustomerPhone(), "下单成功-您从" + result.getStartPlace() + "出发的行程，将由尾号" + carNumber + "的"+ carType +"专车接驾，司机"
                    + name + "师傅，电话" + carUserResult.getPhone() + "。",  1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendBySMS(String phone, String messages, Integer type) {
        String serverUrl = ResourceUtil.getValueBykey("base", "order.server.url");
        String smsUrl = ResourceUtil.getValueBykey("base", "sms.server.url");
        JSONObject body = new JSONObject();
        body.put("dest", phone);
        body.put("message", messages);
        body.put("type", type);
        System.out.println("messages===" + messages);
        String headstr = "{\"screeny\":\"1334\",\"cd\":\"fTr/KQDa8vYVauyd0592HQ==\",\"mos\":\"IOS 8.3\",\"ver\":\"1\",\"de\":\"2015-07-28 14:04:42\",\"aid\":\"NetworkFramework\",\"phone\":\"1234567\",\"screenx\":\"750\"}";
        JSONObject headsjson = JSONObject.parseObject(headstr);
        JSONObject json = new JSONObject();
        json.put("head", headsjson);
        json.put("body", body);
        HttpClient.post(serverUrl + smsUrl, json.toJSONString());
    }
    /**
     * 首约回调 – 司机出发
     *
     * @param meta
     */
    public void carUserSetOut(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("accepted".equals(metaDto.getStatus())){
    		order.setStatus(1);// 已接单
    	}
    	order.setUpdateDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 首约回调 – 司机到达
     *
     * @param meta
     * @author 秦朝胜
     */
    public void carUserReach(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("arriving".equals(metaDto.getStatus())){
    		order.setStatus(14);// 司机到达出发地
    	}
    	order.setTranportStartDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order);
    	// 添加订单状态历史记录
    	addOrderStatusDetail(result, 14);
    	// TODO  发推送给乘客
    	String customerPhone = result.getCustomerPhone();// 乘客手机号
		PushDes pushDes = pushDesMapper.selectByPhone(customerPhone, "2");
		if (pushDes != null && StringUtils.isNotEmpty(pushDes.getChannelId())) {
			String channelId = pushDes.getChannelId();
			BaseJson<JPushDto> jPushDtoJson = new BaseJson<JPushDto>();
			JPushDto jPushdto = new JPushDto();
			JSONObject contentJson = new JSONObject();
			String ss = yz_car_ready_for_customer_onboard;// 专车到达乘客指定地点,等待乘客上车
			contentJson.put("content", ss);
			contentJson.put("type", "pickPassenger");
			contentJson.put("orderId", metaDto.getPartnerOrderNo());
			jPushdto.setContent(contentJson);// 推送的业务数据 Json格式
			List<String> desList = new ArrayList<String>();
			desList.add(channelId);
			jPushdto.setDes(desList);// 发送的目标 pushType:0
			// 可以为空，1:极光的唯一标识// 2:极光的tag
			jPushdto.setAlert(ss);// 消息栏显示的内容
			jPushdto.setTimeToLive(6000);// 消息离线保存的时间 单位为秒
			jPushdto.setType(1);// type消息的类型 1：通知消息 2：透传消息 //尼玛尼玛
			jPushdto.setUserType(2);// 用户类型 1:出租车司机，2：乘客主，3:约租车司机
			jPushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
			jPushDtoJson.setBody(jPushdto);
			push2socket(jPushDtoJson);
		}
    }

    /**
     * 首约回调 – 司机服务中
     *
     * @param meta
     * @author 秦朝胜
     */
    public void orderStart(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("in_progress".equals(metaDto.getStatus())){
    		order.setStatus(11);// 开始计价
    	}
    	orderMapper.updateByPrimaryKeySelective(order);
    	// 添加订单状态历史记录
    	addOrderStatusDetail(result, 11);
    	// TODO  发推送给乘客
    	String customerPhone = result.getCustomerPhone();
		PushDes pushDes = pushDesMapper.selectByPhone(customerPhone, "2");
		if (pushDes != null && StringUtils.isNotEmpty(pushDes.getChannelId())) {
			String channelId = pushDes.getChannelId();
			BaseJson<JPushDto> JPushDtoJson = new BaseJson<JPushDto>();
			JPushDto jPushdto = new JPushDto();
			JSONObject contentJson = new JSONObject();
			contentJson.put("content", car_will_start);
			contentJson.put("type", "pickpi");
			contentJson.put("orderId", metaDto.getPartnerOrderNo());
			List<String> desList = new ArrayList<String>();
			desList.add(channelId);
			jPushdto.setContent(contentJson);
			jPushdto.setDes(desList);
			jPushdto.setAlert(car_will_start);
			jPushdto.setTimeToLive(6000);
			jPushdto.setType(1);// 1通知2透传
			jPushdto.setUserType(2);// 1:出租车司机，2：乘客主，3:约租车司机
			jPushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
			JPushDtoJson.setBody(jPushdto);
			push2socket(JPushDtoJson);// 调用socket的推送,给货主推送消息提示货主已开始计价
		}
    }

    /**
     * 首约回调 – 订单改派
     *
     * @param meta
     * @author 秦朝胜
     */
    public void orderReassign(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	// 判断首约司机是否在大众平台注册
    	CarUser carUserResult = carUserMapper.selectByParam(metaDto.getDriverInfo().getDriverId(),1);
    	if(carUserResult == null){
    		CarUser carUser = new CarUser();
    		carUser.setDriverId(metaDto.getDriverInfo().getDriverId());// 第三方司机Id
        	carUser.setDriverSource(1); // 司机来源：首汽
    		carUser.setName(metaDto.getDriverInfo().getName());// 司机姓名
    		carUser.setPhone(metaDto.getDriverInfo().getPhone());// 司机手机号
    		carUser.setCarNumber(metaDto.getDriverInfo().getLicensePlates());// 车牌号码
    		carUser.setBusinessType(1);// 专车
    		carUser.setType(1);// 加盟
    		carUser.setRegisterDate(new Date());// 注册时间
    		carUser.setIsValid(1);// 已认证
    		carUser.setCity(result.getCity());// 默认司机城市为订单城市
    		carUser.setCityId(result.getCityId());// 默认司机城市Id为订单城市Id
    		carUser.setCompany("首汽约车");
    		carUser.setHeadPic(metaDto.getDriverInfo().getPhotoSrc());// 头像信息
    		carUser.setStatus(1);// 正常
    		carUser.setDelFlag(1);// 有效
            carUser.setGrade(Double.valueOf(metaDto.getDriverInfo().getDriverRate()));
        	if("34".equals(metaDto.getDriverInfo().getGroupName())){
        		carUser.setCarType(0);
    		}
        	if("35".equals(metaDto.getDriverInfo().getGroupName())){
        		carUser.setCarType(1);
    		}
    		if("41".equals(metaDto.getDriverInfo().getGroupName())){
    			carUser.setCarType(2);
    		}
    		carUserMapper.insertUseGeneratedKeys(carUser);
    		order.setCarId(carUser.getId());
    		order.setCarUserName(metaDto.getDriverInfo().getName());
        	order.setCarUserPhone(metaDto.getDriverInfo().getPhone());
    	}else{
            // 修改司机实时信息
            carUserResult.setName(metaDto.getDriverInfo().getName());// 司机姓名
            carUserResult.setPhone(metaDto.getDriverInfo().getPhone());// 司机手机号
            carUserResult.setCarNumber(metaDto.getDriverInfo().getLicensePlates());// 车牌号码
            carUserResult.setBusinessType(1);// 专车
            carUserResult.setType(1);// 加盟
            carUserResult.setRegisterDate(new Date());// 注册时间
            carUserResult.setIsValid(1);// 已认证
            carUserResult.setCity(result.getCity());// 默认司机城市为订单城市
            carUserResult.setCityId(result.getCityId());// 默认司机城市Id为订单城市Id
            carUserResult.setHeadPic(metaDto.getDriverInfo().getPhotoSrc());// 头像信息
            carUserResult.setCompany("首汽约车");
            carUserResult.setStatus(1);// 正常
            carUserResult.setDelFlag(1);// 有效
            carUserResult.setGrade(Double.valueOf(metaDto.getDriverInfo().getDriverRate()));
            if("34".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(0);
            }
            if("35".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(1);
            }
            if("41".equals(metaDto.getDriverInfo().getGroupName())){
                carUserResult.setCarType(2);
            }
            carUserMapper.updateByPrimaryKey(carUserResult);
        	order.setCarId(carUserResult.getId());
    		order.setCarUserName(carUserResult.getName());
        	order.setCarUserPhone(carUserResult.getPhone());
			order.setOrderStartDate(new Date());
    	}
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("reassign".equals(metaDto.getStatus())){
    		order.setStatus(1);// 已接单
			order.setOrderStartDate(new Date());
    	}
    	order.setCarUserChannel(1);// 1：首汽约车
    	order.setUpdateDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order);
    	ThirdPartyOrder thirdPartyOrder = new ThirdPartyOrder();
    	thirdPartyOrder.setPartnerOrderNo(metaDto.getOrderNo());
    	thirdPartyOrder.setOrderId(metaDto.getPartnerOrderNo());
    	thirdPartyOrder.setMerchantId(1);// 第三方合作ID   1:首汽约车
    	thirdPartyOrder.setUpdateDate(new Date());
    	thirdPartyOrderMapper.updateByParams(thirdPartyOrder);
    	String orderId = metaDto.getPartnerOrderNo();
        // 在redis中添加司机接单成功信息
    	RedisUtil.setData("CARUSER_ORDER_TAKING_" + metaDto.getPartnerOrderNo(), metaDto.getPartnerOrderNo(), 60*10);
        // TODO: liusy 16/7/18 17:06  刪除预约单
        RedisUtil.delData("bkOrderData-" + result.getOrderId());

        RedisUtil.delData("yydkd-" + orderId);// 删除预约单看单的人的信息
        RedisUtil.delData("yydkdReject-" + orderId);
        RedisUtil.delData("yywfGrab-" + orderId);
        
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("phone", result.getCustomerPhone());
            map.put("userType", "2");
            List<PushDes> pushDesList = pushDesMapper.selectByPhoneAndUserType(map);
            if (pushDesList == null || pushDesList.isEmpty()) {
                throw new BizException("乘客不存在");
            }
            PushDes pushDes = pushDesList.get(0);
            if (pushDes == null || StringUtils.isBlank(pushDes.getChannelId())) {
                throw new BizException("乘客不存在");
            }
            // TODO: 16/7/7 给乘客推送 有人接单了
            String channelId = pushDes.getChannelId();
            BaseJson<JPushDto> JPushDtoJson = new BaseJson<>();
            JPushDto jPushdto = new JPushDto();
            JSONObject contentJson = new JSONObject();
            contentJson.put("type", "toSend");
            contentJson.put("orderId", orderId);
            jPushdto.setContent(contentJson);
            List<String> desList = new ArrayList<String>();
            desList.add(channelId);
            jPushdto.setDes(desList);
            jPushdto.setAlert("订单改派成功");
            jPushdto.setTimeToLive(6000);
            jPushdto.setType(1);// 1通知2透传
            jPushdto.setUserType(2);// 用户类型 1:出租车司机，2：乘客主，3:约租车司机
            jPushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
            JPushDtoJson.setBody(jPushdto);
            push2socket(JPushDtoJson);// 调用socket的推送
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 首约回调 – 司机代收
     *
     * @param meta
     * @author 秦朝胜
     */
    public void carUserPay(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	// 司机代收车费成功后修改订单状态为已完成
    	if("offline_pay".equals(metaDto.getStatus())){
    		order.setStatus(2);
    	}
    	order.setFinishDate(new Date());
    	order.setUpdateDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order);
    	// 修改合作订单记录
        ThirdPartyOrder tpOrder = new ThirdPartyOrder();
        tpOrder.setOrderId(metaDto.getPartnerOrderNo());
        tpOrder.setPartnerOrderNo(metaDto.getOrderNo());
        tpOrder.setUpdateDate(new Date());
        tpOrder.setPayStatus(1);// 司机代收成功
        tpOrder.setFinishDate(new Date());
        thirdPartyOrderMapper.updateBySelective(tpOrder);
        // 添加订单状态历史记录
    	addOrderStatusDetail(result, 2);
    	// 推送
		PushDes pushDes = pushDesMapper.selectByPhone(result.getCustomerPhone(), "2");
		String channelId = pushDes.getChannelId();
		BaseJson<JPushDto> jPushDtoJson = new BaseJson<JPushDto>();
		JPushDto jpushdto = new JPushDto();
		JSONObject contentJson = new JSONObject();
		contentJson.put("type", "ordedPayed");
		contentJson.put("content", order_payed);
		contentJson.put("orderId", result.getOrderId());
		jpushdto.setContent(contentJson);// 推送的业务数据 Json格式
		List<String> desList = new ArrayList<String>();
		desList.add(channelId);
		jpushdto.setDes(desList);// 发送的目标 pushType:0 可以为空，1:极光的唯一标识,
		// 2:极光的tag
		jpushdto.setAlert(order_payed);// 消息栏显示的内容
		jpushdto.setTimeToLive(6000);// 消息离线保存的时间 单位为秒
		jpushdto.setType(1);// type消息的类型 1：通知消息 2：透传消息
		jpushdto.setUserType(2);// 用户类型 1:出租车司机，2：乘客主，3:约租车司机
		jpushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
		jPushDtoJson.setBody(jpushdto);
		// 调用socket的推送,给货主推送消息提示司机代收成功
		push2socket(jPushDtoJson);
		System.out.println("push2socket------------司机代收成功");
    }

    /**
     * 首约回调 – 取消订单（系统取消/客服取消）
     *
     * @param meta
     * @author 秦朝胜
     */
    public void syCancelOrder(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("sys_canceled".equals(metaDto.getStatus()) || "cs_canceled".equals(metaDto.getStatus())){
    		order.setStatus(3);// 已取消
    	}
    	order.setBakstr1(metaDto.getCustomerServiceInfo().getCancelReason());
    	order.setUpdateDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order);
    	// 添加订单状态历史记录
    	addOrderStatusDetail(result, 3);
    	// 给乘客短信
		String name = org.springframework.util.StringUtils.isEmpty(result.getCarUserName()) ? "" : result.getCarUserName().substring(0, 1);
		sendBySMS(result.getCustomerPhone(), "订单取消成功-您从" + result.getStartPlace() + "出发的行程，已经成功取消，司机"
				+ name + "师傅，电话" + result.getCarUserPhone() + "。",   1);
		log.info("强制取消成功:" + metaDto.getPartnerOrderNo());
    }

    /**
     * 首约回调 – 服务完成结算
     *
     * @param meta
     * @author 秦朝胜
     */
    public void orderCompleted(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() == 2){
    		throw new BizException("订单已完成");
    	}
    	// 判断订单状态是否为已取消
    	if(result.getStatus() == 3){
    		throw new BizException("订单已取消");
    	}
    	// 判断订单状态是否为已超时
    	if(result.getStatus() == 6){
    		throw new BizException("订单已超时");
    	}
    	Order order = new Order();
    	order.setOrderId(metaDto.getPartnerOrderNo());
    	if("completed".equals(metaDto.getStatus())){
    		order.setStatus(9);// 到达目的，未支付
    	}
    	order.setTotalPrice(Float.valueOf(metaDto.getFeeInfo().getActualPayAmount()));
    	order.setActualPrice(Float.valueOf(metaDto.getFeeInfo().getActualPayAmount()));
    	order.setUpdateDate(new Date());
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			Date sDate = sdf.parse(metaDto.getFeeInfo().getStartDate());
			Date eDate = sdf.parse(metaDto.getFeeInfo().getEndDate());
			order.setTranportStartDate(sDate);
	    	order.setTranportEndDate(eDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	orderMapper.updateByPrimaryKeySelective(order);
    	// 添加订单状态历史记录
    	addOrderStatusDetail(result, 9);
    	// 发推送给乘客支付订单金额
    	String customerPhone = result.getCustomerPhone();
		if(customerPhone != null){
			Customer customer = customerMapper.selectByPrimaryKey(customerPhone);
			if(customer==null){
				throw new BizException("未查询到乘客信息");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("phone", customerPhone);
			map.put("userType", "2");
			List<PushDes> pushDesList = pushDesMapper.selectByPhoneAndUserType(map);
			if(pushDesList==null || pushDesList.isEmpty()){
				throw new BizException("乘客不存在");
			}
			PushDes pushDes = pushDesList.get(0);
			if(pushDes==null || StringUtils.isBlank(pushDes.getChannelId())){
				throw new BizException("乘客不存在");
			}
			String channelId = pushDes.getChannelId();
			BaseJson<JPushDto> JPushDtoJson = new BaseJson<JPushDto>();
			JPushDto jPushdto = new JPushDto();
			JSONObject contentJson = new JSONObject();
			contentJson.put("type", "orderNeedPay");
			contentJson.put("content", car_order_pay_with_app);
			contentJson.put("orderId", metaDto.getPartnerOrderNo());
			contentJson.put("money", metaDto.getFeeInfo().getActualPayAmount());
			jPushdto.setContent(contentJson);
			List<String> desList = new ArrayList<String>();
			desList.add(channelId);
			jPushdto.setDes(desList);
			jPushdto.setAlert(car_order_pay_with_app);
			jPushdto.setTimeToLive(6000);
			jPushdto.setType(1);// 1通知2透传
			jPushdto.setUserType(2);// 用户类型 1:出租车司机，2：乘客主，3:约租车司机
			jPushdto.setPushType(1);// 0：全部推送1：通过唯一标识推送2：通过tag推送
			JPushDtoJson.setBody(jPushdto);
			push2socket(JPushDtoJson);// 调用socket的推送
		}
		Order order1 = new Order();
		order1.setOrderId(metaDto.getPartnerOrderNo());
    	order1.setStatus(8);// 待支付
    	order1.setUpdateDate(new Date());
    	orderMapper.updateByPrimaryKeySelective(order1);
    	// 添加订单状态历史记录
    	result.setStatus(9);// // 到达目的，未支付
    	addOrderStatusDetail(result, 8);
    }

    /**
     * 添加订单状态历史记录
     *
     * @param order
     * @param status
     * @author 秦朝胜
     */
    private void addOrderStatusDetail(Order order, int status){
    	OrderStatusTracking orderStatusTracking = new OrderStatusTracking();
        orderStatusTracking.setOrderId(order.getOrderId());
        orderStatusTracking.setCustomerPhone(order.getCustomerPhone());
        orderStatusTracking.setBusinessType(order.getBusinessType());
        orderStatusTracking.setPreStatus(order.getStatus());
        orderStatusTracking.setNewStatus(status);
        orderStatusTracking.setCreateDate(new Date());
        orderStatusTracking.setOperator(OPERATOR_SHOUQI);//首汽约车
        orderStatusTrackingMapper.insertSelective(orderStatusTracking);
    }

    /**
     * 首约回调 – 打印发票申请
     *
     * @param meta
     * @return
     * @author 秦朝胜
     */
    public BigDecimal printInvoiceApplication(String meta){
    	if(StringUtils.isEmpty(meta)){
    		throw new BizException("meta数据不能为空");
    	}
    	MetaDTO metaDto = JSONObject.parseObject(meta, MetaDTO.class);
    	Order result = orderMapper.selectByPrimaryKey(metaDto.getPartnerOrderNo());
    	if(result == null){
    		throw new BizException("未查询到此订单信息");
    	}
    	// 判断订单状态是否为已完成
    	if(result.getStatus() != 2){
    		throw new BizException("订单未完成,不能开发票");
    	}
    	BigDecimal invoice = new BigDecimal(0);
    	if("apply_invoice".equals(metaDto.getStatus())){
    		Customer customer = customerMapper.selectOneByPhone(result.getCustomerPhone());
    		if(customer != null){
    			if(result.getTotalPrice() < customer.getInvoiceBalance()){
        			invoice = new BigDecimal(result.getTotalPrice());
        			Double balance = customer.getInvoiceBalance() - invoice.doubleValue();
        			customer.setInvoiceBalance(balance);
        		}else{
        			invoice = new BigDecimal(customer.getInvoiceBalance());
        			customer.setInvoiceBalance(0d);
        		}
        		// 修改乘客开票额度
        		customerMapper.updateByPrimaryKeySelective(customer);
        		// 修改订单中开票状态以及开票金额
        		Order order = new Order();
        		order.setOrderId(metaDto.getPartnerOrderNo());
        		order.setIsInvoice(1);// 已开票
        		order.setOtherPrice2(invoice.floatValue());
        		orderMapper.updateByPrimaryKeySelective(order);
    		}
    	}
    	return invoice;
    }

    /**
     * 下单后–通知大众是否采用大众司机
     * @param orderNo
     * @param partnerOrderNo
     * @param driverId
     * @param sign
     * @param channel
     */
    public void usingTheDriver(String orderNo,String partnerOrderNo,Integer driverId,String sign,String channel) {

        if (StringUtils.isBlank(orderNo)) {
            throw new BizException("大众订单号不能为空");
        }
        if (StringUtils.isBlank(partnerOrderNo)) {
            throw new BizException("合作方单号不能为空");
        }
        if (driverId == null) {
            throw new BizException("大众出行司机ID不能为空");
        }
        String data = RedisUtil.getData("RETURN_CODE_ERROR_" + orderNo);
        if (StringUtils.isNotBlank(data)) {
            throw new BizException("司机信息同步异常");
        }
        CarUser carUser = carUserMapper.selectByPrimaryKey(driverId);
        if (carUser == null) {
            throw new BizException("司机不存在");
        }
        Order order = orderMapper.selectByPrimaryKey(orderNo);
        if (order == null) {
            throw new BizException("订单不存在");
        }
        Date bookDate = order.getBookDate();
        Long seconds = 24 * 60 * 60l;
        if (bookDate != null) {
            seconds = (bookDate.getTime() - System.currentTimeMillis()) / 1000;
            seconds += 4 * 60 * 60;
        }
        Order parameterOrder = new Order();
        parameterOrder.setOrderId(orderNo);
        parameterOrder.setCarId(driverId);
		parameterOrder.setBusinessType(carUser.getBusinessType());
		parameterOrder.setOrderType(6);//订单类型(0.app用车，1.allcenter用车，2.app预约，3.callcenter预约，4.协议价，5.接送机，6.首汽)
		parameterOrder.setOrderSource(4);//订单来源：0 android；1 IOS；2 wechat；3 callcenter；4 首汽
//        parameterOrder.setCarType(carUser.getCarType() + "");
        parameterOrder.setCarUserPhone(carUser.getPhone());
		parameterOrder.setCarUserName(carUser.getName());
		parameterOrder.setStatus(1);//状态0创建，1已接单，2已完成，3取消 ......
		parameterOrder.setUpdateDate(new Date());
		parameterOrder.setOrderStartDate(new Date());
        int i = orderMapper.updateByPrimaryKeySelective(parameterOrder);
        if (i != 1) {
            throw new BizException("订单更新失败");
        }

		 //插入订单状态信息
        OrderStatusTracking orderStatusTracking = new OrderStatusTracking();
        orderStatusTracking.setOrderId(orderNo);
        orderStatusTracking.setCarUserPhone(carUser.getPhone());
        orderStatusTracking.setCustomerPhone(order.getCustomerPhone());
        orderStatusTracking.setBusinessType(order.getBusinessType());
        orderStatusTracking.setPreStatus(order.getStatus());
        orderStatusTracking.setNewStatus(1);
        orderStatusTracking.setCreateDate(new Date());
        orderStatusTracking.setOperator(4);//操作人0：乘客，1：司机，2：订单服务器 3：后台，4：首汽约车
        int i1 = orderStatusTrackingMapper.insertSelective(orderStatusTracking);
        if (i1 != 1) {
            throw new BizException("插入订单状态信息失败");
        }
        ThirdPartyOrder thirdPartyOrder = thirdPartyOrderMapper.selectByOrderIdAndPartnerOrderNo(orderNo, partnerOrderNo);
        thirdPartyOrder.setDriverInfoNoticeCallBack(1);
        thirdPartyOrder.setDriverId(driverId);
        thirdPartyOrderMapper.updateByPrimaryKey(thirdPartyOrder);
        RedisUtil.setData(Constants.SQORDER_DZCAR_ + orderNo, driverId + "", seconds.intValue());
    }

    public void noticeDriver(String orderNo, String partnerOrderNo, BigDecimal amount) {
		if (orderNo == null) {
			throw new BizException("参数orderNo为空");
		}
		Order order = orderMapper.selectByPrimaryKey(orderNo);
        if (order == null) {
            throw new BizException("该订单不存在");
        }
		if (partnerOrderNo == null) {
			throw new BizException("参数partnerOrderNo为空");
		}
		if (amount == null) {
			throw new BizException("参数amount为空");
		}
		if(amount.compareTo(BigDecimal.valueOf(0))!=1){//-1为小于 0为等于 1为大于  如需要支付金额不大于0则生成异常信息
			throw new BizException("需付款金额不大于0");
		}
		ThirdPartyOrder thirdPartyOrder = thirdPartyOrderMapper.selectByOrderIdAndPartnerOrderNo(orderNo, partnerOrderNo);
		if(thirdPartyOrder == null){
			throw new BizException("未查询到此第三方订单信息");
		}
		thirdPartyOrder.setPayAmount(amount);
		thirdPartyOrder.setPayType(2);
		thirdPartyOrder.setPayStatus(1);
		if(!(thirdPartyOrderMapper.updateByPrimaryKeySelective(thirdPartyOrder)>0)){
			throw new BizException("司机代收金额update失败");
		}
	}
    public static final String GRAP_ORDER_RESULT = "grap_order_result_";// 存放订单抢单结果
    public static final String ORDER_STATUS_GRABED_VALUE = "grabed";// 订单已被抢状态
    public static final String REDIS_ORDER_STATUS = "order_status_";
    public static final String SHOUYUE_RECIVE_KEY = "SHOUYUE_RECIVE_KEY_";

    public void stopAssNotice(String orderNo, String partnerOrderNo) {
        RedisUtil.setData(REDIS_ORDER_STATUS + orderNo, orderNo, 20 * 60);
    }
}
