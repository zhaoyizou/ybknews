package com.visionet.platform.cooperation.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.visionet.core.util.HttpClient;
import com.visionet.core.util.ResourceUtil;
import com.visionet.platform.cooperation.dto.BaseReturnDTO;
import com.visionet.platform.cooperation.dto.ReturnBanlanceAccountsDTO;
import com.visionet.platform.cooperation.mapper.CustomerMapper;
import com.visionet.platform.cooperation.mapper.OrderMapper2;
import com.visionet.platform.cooperation.model.Customer;
import com.visionet.platform.cooperation.model.Order;
import com.visionet.platform.thirdpartydata.mapper.ThirdPartyOrderMapper;
import com.visionet.platform.thirdpartydata.model.ThirdPartyOrder;

/**
 * author:zhangxh@visionet.com.cn
 */

@Service
public class TransformationService {
	private static final Logger log = LoggerFactory.getLogger(TransformationService.class);

    public static final String DZCX_CHANNEL = "dzcx";
    public static final String UTF_8 = "utf-8";
    private static final String PARTNER_URL = ResourceUtil.getValueBykey("base", "partner.server.url");
    private static final String PARTNER_DRIVER_LOCATION = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.driverLocation.url");
    private static final String PARTNER_GETGROUPSPRICE_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.getGroupsPrice.url");
    private static final String PARTNER_NEARBYDRIVERS_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.nearbyDrivers.url");
    private static final String PARTNER_POSTINSTANTORDER_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.postInstantOrder.url");
    private static final String PARTNER_CANCELORDER_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.cancelOrder.url");
    private static final String PARTNER_POSINVOICE_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.postInvoice.url");
    private static final String PARTNER_EVALUATEDRIVER_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.evaluateDriver.url");
    private static final String PARTNER_PAYNOTIFY_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.paynotify.url");
    private static final String PARTNER_WAITSERVE_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.waitServe.url");
    private static final String PARTNER_ARRIVENOW_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.arriveNow.url");
    private static final String PARTNER_STARTNEWSERVICE_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.startnewService.url");
    private static final String PARTNER_SUBMIT_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.submit.url");
    private static final String PARTNER_BANLANCEACCOUNTS_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.banlanceAccounts.url");
    private static final String PARTNER_DRIVERDPAY_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.driverDpay.url");
    private static final String PARTNER_INVOICEPRINT_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.invoiceprint.url");
    private static final String PARTNER_DRIVERINFO_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.driverInfo.url");
    public static final String SQYC_CHANNEL = ResourceUtil.getValueBykey("base", "sqyc.channel");

    @Autowired
    private OrderMapper2 orderMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ThirdPartyOrderMapper thirdPartyOrderMapper;
    
    /**
     * @throws Exception
     * @author 调用首约 司机待服务回调接口
     */
    public JSONObject waitServe(String username, String sqycOrderNumber, String partnerOrderNumber, String lo,
                                       String la, String altitude, String azimuth, String speed, String status) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("username", username);
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("lo", lo);
        params.put("la", la);
        params.put("altitude", altitude);
        params.put("azimuth", azimuth);
        params.put("speed", speed);
        params.put("status", String.valueOf(status));

        String s = HttpClient.postEncrypt(PARTNER_WAITSERVE_URL, params);
        JSONObject parseObject = JSONObject.parseObject(s);
        // BaseReturnDTO baseReturnDto = new BaseReturnDTO();
        // if (!"0".equals(parseObject.getString("returnCode"))) {
        // baseReturnDto.setReturnCode("2");
        // baseReturnDto.setReturnMessage("调用司机开始服务回调接口异常");
        // } else {
        // baseReturnDto.setReturnCode("0");
        // baseReturnDto.setReturnMessage("调用司机开始服务回调接口成功");
        // }
		log.info("[dzcx_partner]--调用首约 司机待服务回调接口--返回结果:_" + s);
        return parseObject;

    }

    /**
     * 查看司机实时位置
     *
     * @param orderId
     * @param partnerOrderNo
     * @param channel
     * @return
     * @throws Exception
     */
    public JSONObject getPartnerDriverLocation(String orderId, String partnerOrderNo, String channel) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("partnerOrderNo", orderId);
        params.put("orderNo", partnerOrderNo);
        params.put("channel", channel);
        String s = HttpClient.postEncrypt(PARTNER_DRIVER_LOCATION, params);
		log.info("[dzcx_partner]--调用首约 查看司机实时位置--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

    /**
     * 查看车型
     *
     * @param cityId
     * @param channel
     * @return
     * @throws Exception
     */
    public JSONObject getGroupsPrice(String cityId, String channel) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("cityId", cityId);
        params.put("channel", channel);
        String s = HttpClient.postEncrypt(PARTNER_GETGROUPSPRICE_URL, params);
		log.info("[dzcx_partner]--调用首约 查看车型--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

    /**
     * 下单
     *
     * @param bookingDate
     * @param riderPhone
     * @param bookingStartAddr
     * @param bookingEndAddr
     * @param bookingStartPointLo
     * @param bookingStartPointLa
     * @param bookingEndPointLo
     * @param bookingEndPointLa
     * @param imei
     * @param cityId
     * @param groupIds
     * @param estimatedAmount
     * @param riderName
     * @param partnerOrderNo
     * @param channel
     * @return
     * @throws Exception
     */
    public JSONObject postInstantOrder(String bookingDate,
                                       String riderPhone,
                                       String bookingStartAddr,
                                       String bookingEndAddr,
                                       String bookingStartPointLo,
                                       String bookingStartPointLa,
                                       String bookingEndPointLo,
                                       String bookingEndPointLa,
                                       String imei,
                                       String cityId,
                                       String groupIds,
                                       String estimatedAmount,
                                       String riderName,
                                       String partnerOrderNo,
                                       String channel) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("bookingDate", bookingDate);
        params.put("riderPhone", riderPhone);
        params.put("bookingStartAddr", bookingStartAddr);
        params.put("bookingEndAddr", bookingEndAddr);
        params.put("bookingStartPointLo", bookingStartPointLo);
        params.put("bookingStartPointLa", bookingStartPointLa);
        params.put("bookingEndPointLo", bookingEndPointLo);
        params.put("bookingEndPointLa", bookingEndPointLa);
        params.put("imei", imei);
        params.put("cityId", cityId);
        params.put("groupIds", groupIds);
        params.put("estimatedAmount", estimatedAmount);
        params.put("riderName", riderName);
        params.put("partnerOrderNo", partnerOrderNo);
        params.put("channel", channel);
        String s = HttpClient.postEncryptAddOrder(PARTNER_POSTINSTANTORDER_URL, params);
		log.info("[dzcx_partner]--调用首约  下单--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

    /**
     * 查看附近司机位置
     *
     * @param longitude
     * @param latitude
     * @param maxDistance
     * @param maxDriverCount
     * @param channel
     * @return
     * @throws Exception
     */
    public JSONObject nearbyDrivers(String longitude, String latitude, String maxDistance, String maxDriverCount, String channel) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("maxDistance", maxDistance);
        params.put("maxDriverCount", maxDriverCount);
        params.put("channel", channel);
        String s = HttpClient.postEncrypt(PARTNER_NEARBYDRIVERS_URL, params);
		log.info("[dzcx_partner]--调用首约  查看附近司机位置--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

    /**
     * 取消订单
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param cancelType
     * @param reason
     * @param channel
     * @return
     * @throws Exception
     * @author fangren
     */
    public JSONObject cancelOrder(String orderNo, String partnerOrderNo,String partnerCancelOrderPenaltyAmt,
    		String cancelType, String reason, String channel) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("orderNo", orderNo);
        params.put("partnerOrderNo", partnerOrderNo);
        params.put("partnerCancelOrderPenaltyAmt", partnerCancelOrderPenaltyAmt);
        params.put("reason", reason);
        params.put("cancelType", "106");// 首汽约车给定
        params.put("channel", channel);
        String s = HttpClient.postEncryptAddOrder(PARTNER_CANCELORDER_URL, params);
		log.info("[dzcx_partner]--调用首约  取消订单--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

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
     * @param channel
     * @return
     * @throws Exception
     * @author fangren
     */
    public JSONObject postInvoice(String orderNo, String partnerOrderNo,
                                  String invoiceTitle, String invoiceContent, String address,
                                  String postCode, String addressee, String phone, String amount, String channel) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderNo", orderNo);// 首汽订单
        params.put("partnerOrderNo", partnerOrderNo);// 大众订单
        params.put("invoiceTitle", invoiceTitle);
        params.put("invoiceContent", invoiceContent);
        params.put("address", address);
        params.put("postCode", postCode);
        params.put("addressee", addressee);
        params.put("phone", phone);
        params.put("amount", amount);
        params.put("channel", channel);
        String s = HttpClient.postEncrypt(PARTNER_POSINVOICE_URL, params);
		log.info("[dzcx_partner]--调用首约  开发票申请--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

    /**
     * 评价司机转发接口
     *
     * @param orderNo
     * @param driverId
     * @param evaluateScore
     * @param partnerOrderNo
     * @param describe
     * @param channel
     * @throws Exception
     * @author 王志鹏
     */
    public JSONObject gradeOrder(String orderNo, String driverId, Integer evaluateScore, String partnerOrderNo, String describe, String channel) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderNo", orderNo);
        params.put("driverId", driverId);
        params.put("evaluateScore", evaluateScore.toString());
        params.put("partnerOrderNo", partnerOrderNo);
        params.put("memo", describe);
        params.put("channel", channel);
        String ret = HttpClient.postEncrypt(PARTNER_EVALUATEDRIVER_URL, params);
		log.info("[dzcx_partner]--调用首约  评价司机转发接口--返回结果:_" + ret);
        return JSONObject.parseObject(ret);
    }


//    private static final String PARTNER_SUBMITBILL_URL = PARTNER_URL + ResourceUtil.getValueBykey("base", "partner.submitBill.url");

    /**
     * 下单后 –通知支付结算结果转发接口
     *
     * @param orderNo
     * @param amount
     * @param partnerOrderNo
     * @param couponAmount
     * @param chargeSettleAmount
     * @param giftSettleAmount
     * @param customerCreditcardAmount
     * @param driverPay
     * @param ticketAmount
     * @param channel
     * @return
     * @throws Exception
     * @author zhouwei
     */
    public JSONObject payNotify(String orderNo, String amount, String partnerOrderNo, String couponAmount,
    		String chargeSettleAmount, String giftSettleAmount, String customerCreditcardAmount,
    		String driverPay, String ticketAmount, String channel) throws Exception {
    	// 计算本次订单可开发票金额
    	Order result = orderMapper.selectByPrimaryKey(partnerOrderNo);
    	BigDecimal invoiceAmt = new BigDecimal(0);
    	Customer customer = customerMapper.selectOneByPhone(result.getCustomerPhone());
    	if(customer != null){
    		if(result.getTotalPrice() < customer.getInvoiceBalance()){
    			invoiceAmt = new BigDecimal(result.getTotalPrice());
    		}else{
    			invoiceAmt = new BigDecimal(customer.getInvoiceBalance());
    		}
    	}
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderNo", orderNo);
        params.put("amount", amount);
        params.put("partnerOrderNo", partnerOrderNo);
        params.put("couponAmount", couponAmount);
        params.put("chargeSettleAmount", chargeSettleAmount);
        params.put("giftSettleAmount", giftSettleAmount);
        params.put("customerCreditcardAmount", customerCreditcardAmount);
        params.put("driverPay", driverPay);
        params.put("ticketAmount", invoiceAmt.toString());
        params.put("channel", channel);
        String ret = HttpClient.postEncrypt(PARTNER_PAYNOTIFY_URL, params);
		log.info("[dzcx_partner]--调用首约  下单后 –通知支付结算结果转发接口--返回结果:_" + ret);
        return JSONObject.parseObject(ret);
    }

    /**
     * @throws Exception
     * @author张晓虎 --调用首约 --司机到达乘客出发地回调接口
     */

    public JSONObject arriveNow(String username, String sqycOrderNumber, String partnerOrderNumber,
                                String lo, String la, String altitude, String azimuth, String speed, String status) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("username", username);
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("lo", lo);
        params.put("la", la);
        params.put("altitude", altitude);
        params.put("azimuth", azimuth);
        params.put("speed", speed);
        params.put("status", String.valueOf(status));

        String s = HttpClient.postEncrypt(PARTNER_ARRIVENOW_URL, params);
        JSONObject parseObject = JSONObject.parseObject(s);
		log.info("[dzcx_partner]--调用首约  下单后 –司机到达乘客出发地回调接口--返回结果:_" + s);
        return parseObject;
    }

	/**
	 * 司机开始服务回调接口
	 * 
	 * @param sqycOrderNumber
	 * @param partnerOrderNumber
	 * @param username
	 * @param lo
	 * @param la
	 * @param altitude
	 * @param azimuth
	 * @param speed
	 * @param status
	 * @param factstartaddr
	 * @return
	 * @throws Exception
	 */
    public JSONObject startnewService(String sqycOrderNumber, String partnerOrderNumber, String username,
                                      String lo,
                                      String la, String altitude, String azimuth, String speed, String status, String factstartaddr)
            throws Exception {

        System.err.println(
                "sqycOrderNumber " + sqycOrderNumber + " partnerOrderNumber " + partnerOrderNumber + " username "
                        + username + " lo " + lo + " la " + la + " altitude " + altitude + " azimuth " + azimuth
                        + " SPEED " + speed
                        + " status " + status + " factstartaddr " + factstartaddr);

        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("username", username);
        params.put("lo", lo);
        params.put("la", la);
        params.put("altitude", altitude);
        params.put("azimuth", azimuth);
        params.put("speed", speed);
        params.put("factstartaddr", factstartaddr);
        params.put("status", String.valueOf(status));

        String s = HttpClient.postEncrypt(PARTNER_STARTNEWSERVICE_URL, params);
        JSONObject parseObject = JSONObject.parseObject(s);
		log.info("[dzcx_partner]--调用首约  司机开始服务回调接口--返回结果:_" + s);
        return parseObject;
    }

    /**
	 * @param othersFee
	 * @param actualPayAmount
	 * @param ssFees
	 * @param ksFees
	 * @param decimalsFees
	 * @throws Exception
	 * @author张晓虎 --调用首约 --司机生成账单接口回调接口
	 */
    public JSONObject submit(String username, String orderNumber, String partnerOrderNumber, String allFees,
                             String startTime, String entTime, String lo, String la, String allTime, String mileage, String longMileage,
                             String timeOut, String outMin, String veryLong, String outMil, String nighitDuration,
                             String nighitDurationFees, String nightMileage, String yjFees, String hotDuration, String hotDurationFees,
                             String hotMileage, String hotMileageFees, String waitingMinutes, String waitingFee, String pac,
                             String pacIncludeMinute, String pacIncludeMileage, String gsFees, String tcFees, String jcFees,
			String ssFees,String ksFees, String actualPayAmount, String othersFee,  String decimalsFees)
			throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("username", username);
        params.put("orderNumber", orderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("allFees", String.valueOf(allFees));
        params.put("startTime", String.valueOf(startTime));
        params.put("entTime", String.valueOf(entTime));
        params.put("lo", lo);
        params.put("la", la);
        params.put("allTime", allTime);
        params.put("mileage", mileage);
        params.put("longMileage", longMileage);
        params.put("timeOut", String.valueOf(timeOut));
        params.put("outMin", outMin);
        params.put("veryLong", String.valueOf(veryLong));
        params.put("outMil", outMil);
        params.put("nighitDuration", nighitDuration);
        params.put("nighitDurationFees", String.valueOf(nighitDurationFees));
        params.put("nightMileage", nightMileage);
        params.put("yjFees", String.valueOf(yjFees));
        params.put("hotDuration", hotDuration);
        params.put("hotDurationFees", String.valueOf(hotDurationFees));
        params.put("hotMileage", hotMileage);
        params.put("hotMileageFees", String.valueOf(hotMileageFees));
        params.put("waitingMinutes", String.valueOf(waitingMinutes));
        params.put("waitingFee", String.valueOf(waitingFee));
        params.put("pac", String.valueOf(pac));
        params.put("pacIncludeMinute", pacIncludeMinute);
        params.put("pacIncludeMileage", pacIncludeMileage);
        params.put("gsFees", String.valueOf(gsFees));
        params.put("tcFees", String.valueOf(tcFees));
        params.put("jcFees", String.valueOf(jcFees));
        params.put("ssFees", String.valueOf(ssFees));
		params.put("ksFees", String.valueOf(ksFees));
        params.put("actualPayAmount", String.valueOf(actualPayAmount));
		params.put("decimalsFees", String.valueOf(decimalsFees));
        params.put("othersFee", String.valueOf(othersFee));

        String s = HttpClient.postEncrypt(PARTNER_SUBMIT_URL, params);
        JSONObject parseObject = JSONObject.parseObject(s);
		log.info("[dzcx_partner]--调用首约  司机生成账单接口回调接口--返回结果:_" + s);
        return parseObject;
    }

    /**
     * @throws Exception
     * @author --调用首约 --司机结算回调接口
     */
    public JSONObject banlanceAccounts(String username, String sqycOrderNumber, String partnerOrderNumber)
            throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("username", username);
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);

        String s = HttpClient.postEncrypt(PARTNER_BANLANCEACCOUNTS_URL, params);
        ReturnBanlanceAccountsDTO dto = new ReturnBanlanceAccountsDTO();
        JSONObject parseObject = JSONObject.parseObject(s);
        if ("1103".equals(parseObject.getString("returnCode"))) {
            JSONObject dataJsonObject = parseObject.getJSONObject("data");
            String string = dataJsonObject.getString("collectedPay");
            dto.setCollectedPay(string);
            dto.setReturnCode("0");
			dto.setReturnMessage("调用司机结算回调接口成功");
        } else {
            if (!"0".equals(parseObject.getString("returnCode"))) {
                dto.setCollectedPay("0");
                dto.setReturnCode("2");
				dto.setReturnMessage("调用司机结算回调接口异常");
            } else {
                dto.setCollectedPay("0");
                dto.setReturnCode("0");
				dto.setReturnMessage("调用司机结算回调接口成功");
            }
        }
		log.info("[dzcx_partner]--调用首约  司机结算回调接口--返回结果:_" + s);
        return parseObject;
    }

    /**
     * @throws Exception
     * @author --调用首约 --司机代收成功回调接口(包括全额代收)
     */
    public JSONObject driverDpay(String username, String sqycOrderNumber, String partnerOrderNumber,
                                 String amount)
            throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("username", username);
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("amount", amount);

        String s = HttpClient.postEncrypt(PARTNER_DRIVERDPAY_URL, params);
        BaseReturnDTO baseReturnDto = new BaseReturnDTO();
        JSONObject parseObject = JSONObject.parseObject(s);

        // if (!"0".equals(parseObject.getString("returnCode"))) {
        // baseReturnDto.setReturnCode("2");
        // baseReturnDto.setReturnMessage("调用司机开始服务回调接口异常");
        // } else {
        // baseReturnDto.setReturnCode("0");
        // baseReturnDto.setReturnMessage("调用司机开始服务回调接口成功");
        // }
		log.info("[dzcx_partner]--调用首约  司机代收成功回调接口(包括全额代收)--返回结果:_" + s);
        return parseObject;
    }

    /**
     * @throws Exception
     * @author --调用首约 --司机端打印发票回调接口
     */
    public JSONObject invoiceprint(String sqycOrderNumber, String partnerOrderNumber, String username)
            throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", SQYC_CHANNEL);
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("username", username);

        String s = HttpClient.postEncrypt(PARTNER_INVOICEPRINT_URL, params);
        BaseReturnDTO baseReturnDto = new BaseReturnDTO();
        JSONObject parseObject = JSONObject.parseObject(s);

        // if (!"0".equals(parseObject.getString("returnCode"))) {
        // baseReturnDto.setReturnCode("2");
        // baseReturnDto.setReturnMessage("调用司机开始服务回调接口异常");
        // } else {
        // baseReturnDto.setReturnCode("0");
        // baseReturnDto.setReturnMessage("调用司机开始服务回调接口成功");
        // }
		log.info("[dzcx_partner]--调用首约  司机端打印发票回调接口--返回结果:_" + s);
        return parseObject;
    }

    /**
     * @throws Exception
     * @author房忍 --调用首约 --司机信息回调接口(改派)
     */
    public JSONObject driverInfo(String channel, String isReassign,
                                 String driverId, String sqycOrderNumber, String partnerOrderNumber,
                                 String name, String phone, String color, String licensePlates, String modelName, String responseTime,
                                 String cityId, String idCardNo, String superintendNo,
                                 String drivingYears, String partnerPhotoSrct, String groupName) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("channel", channel);
        params.put("isReassign", isReassign);// 是不是改派订单 0：否；1：是
        params.put("driverId", driverId);// 司机id
        params.put("sqycOrderNumber", sqycOrderNumber);
        params.put("partnerOrderNumber", partnerOrderNumber);
        params.put("name", name);
        params.put("phone", phone);
        params.put("color", color);// 车辆颜色
        params.put("licensePlates", licensePlates);// 车牌
//		params.put("modelId", modelId);// 车型id
        params.put("modelName", modelName);// 车型
        params.put("groupName", groupName);// 车型
        params.put("responseTime", responseTime);// 响应时间,时间戳,秒
        params.put("cityId", cityId);
//        params.put("idCardNo", idCardNo);// 身份证号
        params.put("superintendNo", "");// 服务监督码
        params.put("drivingYears", "");// 驾龄
        params.put("partnerPhotoSrct", partnerPhotoSrct);// 司机头像
        String s = HttpClient.postEncrypt(PARTNER_DRIVERINFO_URL, params);
		log.info("[dzcx_partner]--调用首约  司机信息回调接口(改派)--返回结果:_" + s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        if (StringUtils.isNotBlank(s)) {
            if ("0".equals(jsonObject.getString("returnCode"))){
                ThirdPartyOrder thirdPartyOrder = thirdPartyOrderMapper.selectByOrderIdAndPartnerOrderNo(partnerOrderNumber, sqycOrderNumber);
                thirdPartyOrder.setDriverInfoNotice(1);
                thirdPartyOrderMapper.updateByPrimaryKey(thirdPartyOrder);
            }
        }
        //            RedisUtil.setData("RETURN_CODE_ERROR_" + partnerOrderNumber, partnerOrderNumber, 20 * 60);
        log.info("[dzcx_partner]--调用首约  司机信息回调接口(改派)--返回结果:_" + s);
        return JSONObject.parseObject(s);
    }

    /**
     * 司机生成账单
     * @author 王志鹏
     * @param sign
     * @param channel
     * @param account
     * @param orderNumber
     * @param partnerOrderNumber
     * @param allFees
     * @param startTime
     * @param endTime
     * @param lo
     * @param la
     * @param allTime
     * @param mileage
     * @param longMileage
     * @param timeOutFees
     * @param outMin
     * @param veryLong
     * @param outMil
     * @param nighitDuration
     * @param nighitDurationFees
     * @param nightMileage
     * @param yjFees
     * @param hotDuration
     * @param hotDurationFees
     * @param hotMileage
     * @param hotMileageFees
     * @param waitingMinutes
     * @param waitingFee
     * @param pac
     * @param pacIncludeMinute
     * @param pacIncludeMileage
     * @param gsFees
     * @param tcFees
     * @param jcFees
     * @return
     * @throws Exception
     */
    /*public JSONObject submitBill(String channel, String account, String orderNumber, String partnerOrderNumber,
            Double allFees, Long startTime, Long endTime, String lo, String la, String allTime, String mileage,
			String longMileage, Double timeOutFees, String outMin, Double veryLong, String outMil,
			String nighitDuration, Double nighitDurationFees, String nightMileage, Double yjFees, String hotDuration,
			Double hotDurationFees, String hotMileage, Double hotMileageFees, Double waitingMinutes, Double waitingFee,
			Double pac, String pacIncludeMinute, String pacIncludeMileage, Double gsFees, Double tcFees, Double jcFees) throws Exception {
		Map<String, String> params = new HashMap<>();
//		params.put("sign", sign);
		params.put("channel", channel);
		params.put("account", account);
		params.put("orderNumber", orderNumber);
		params.put("partnerOrderNumber", partnerOrderNumber);
		params.put("allFees", allFees.toString());
		params.put("startTime", startTime.toString());
		params.put("endTime", endTime.toString());
		params.put("lo", lo);
		params.put("la", la);
		params.put("allTime", allTime);
		params.put("mileage", mileage);
		params.put("longMileage", longMileage);
		params.put("timeOutFees", timeOutFees.toString());
		params.put("outMin", outMin);
		params.put("veryLong", veryLong.toString());
		params.put("outMil", outMil);
		params.put("nighitDuration", nighitDuration);
		params.put("nighitDurationFees", nighitDurationFees.toString());
		params.put("nightMileage", nightMileage);
		params.put("yjFees", yjFees.toString());
		params.put("hotDuration", hotDuration);
		params.put("hotDurationFees", hotDurationFees.toString());
		params.put("hotMileage", hotMileage);
		params.put("hotMileageFees", hotMileageFees.toString());
		params.put("waitingMinutes", waitingMinutes.toString());
		params.put("waitingFee", waitingFee.toString());
		params.put("pac", pac.toString());
		params.put("pacIncludeMinute", pacIncludeMinute);
		params.put("pacIncludeMileage", pacIncludeMileage);
		params.put("gsFees", gsFees.toString());
		params.put("tcFees", tcFees.toString());
		params.put("jcFees", jcFees.toString());
		String ret = HttpClient.postEncrypt(PARTNER_SUBMITBILL_URL, params);
		return JSONObject.parseObject(ret);
	}*/

    /**
     * 司机代收成功回调接口
     * @author 王志鹏
     * @param sign
     * @param channel
     * @param sqycOrderNumber
     * @param partnerOrderNumber
     * @param amount
     * @param account
     * @return
     * @throws Exception
     */
    /*public JSONObject driverDpay(String channel,String sqycOrderNumber,String partnerOrderNumber,Double amount,String account) throws Exception{
    	Map<String, String> params = new HashMap<String, String>();
		params.put("sqycOrderNumber", sqycOrderNumber);
		params.put("partnerOrderNumber", partnerOrderNumber);
		params.put("amount",amount.toString());
		params.put("account", account);
//		params.put("sign", sign);
		params.put("channel", channel);
		String ret = HttpClient.postEncrypt(PARTNER_DRIVERDPAY_URL, params);
		return JSONObject.parseObject(ret);
    }*/
}
