package com.visionet.platform.cooperation.controller;

import com.visionet.core.support.Response;
import com.visionet.platform.cooperation.model.CarUser;
import com.visionet.platform.cooperation.service.CarUserService;
import com.visionet.platform.cooperation.service.OrderService;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.net.URLDecoder;

import static com.visionet.core.util.Constants.UTF_8;

@Controller
@RequestMapping("cooperation/order")
@Api(value = "cooperation/order", tags = {"Order API"}, description = "订单相关API")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CarUserService carUserService;

    /**
     * 结算后 – 评价司机
     *
     * @param orderNo        订单号
     * @param partnerOrderNo 合作方订单号
     * @param evaluateScore  评价得分
     * @param describe       评价内容
     * @param sign           签名
     * @param channel        渠道
     * @return
     * @author 王志鹏
     */
    @RequestMapping(value = "evaluateDriver", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "结算后 – 评价司机",
            httpMethod = "POST",
            response = Response.class,
            value = "结算后 – 评价司机")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> evaluateDriver(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "评分，可选值1到5") @RequestParam("evaluateScore") Integer evaluateScore,
            @ApiParam(required = true, value = "评价内容") @RequestParam("describe") String describe,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.evaluateDriver(orderNo, partnerOrderNo, evaluateScore, URLDecoder.decode(describe, UTF_8));
        return Response.OK();
    }

    /**
     * 下单前 – 获取车型列表
     *
     * @return
     * @author 张贺 2016/11/23
     */
    @RequestMapping(value = "getCarsModelList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单前 – 获取车型列表",
            httpMethod = "POST",
            response = Response.class,
            value = "下单前 – 获取车型列表")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> getCarsModelList(
            @ApiParam(required = true, value = "城市ID") @RequestParam("cityId") Integer cityId,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return Response.OK(orderService.getCarsModelList(cityId));
    }

    /**
     * 下单 – 下单接口
     *
     * @return
     * @author 张贺 2016/11/23
     */
    @RequestMapping(value = "newOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单 – 下单接口",
            httpMethod = "POST",
            response = Response.class,
            value = "下单 – 下单接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> newOrder(
            @ApiParam(required = true, value = "合作方订单号ID") @RequestParam("partnerOrderId") String partnerOrderId,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "业务类型,大众出行指定：1") @RequestParam("businessType") Integer businessType,
            @ApiParam(required = true, value = "订单类型，大众出行指定：6") @RequestParam("orderType") Integer orderType,
            @ApiParam(required = true, value = "用车类型 多种车型“;”分割0舒适型，1商务型，2豪华型 0;1;2") @RequestParam("carType") String carType,
            @ApiParam(required = true, value = "出发地地址") @RequestParam("startPlace") String startPlace,
            @ApiParam(required = true, value = "乘客出发地的经纬度,逗号分隔（先经度后纬度）121.43684,31.216673") @RequestParam("startGps") String startGps,
            @ApiParam(required = true, value = "目的地") @RequestParam("endPlace") String endPlace,
            @ApiParam(required = true, value = "预估里程 单位为Km") @RequestParam("expectedKm") Double expectedKm,
            @ApiParam(required = true, value = "乘客目的地的经纬度逗号分隔（先经度后纬度） 121.43684,31.216673") @RequestParam("endGps") String endGps,
            @ApiParam(required = true, value = "预估金额") @RequestParam("expectedPrice") String expectedPrice,
            @ApiParam(required = true, value = "乘客手机号") @RequestParam("customerPhone") String customerPhone,
            @ApiParam(required = true, value = "乘客姓名") @RequestParam("customerName") String customerName,
            @ApiParam(required = true, value = "用车时间 2016-11-11 15:16:46") @RequestParam("callDate") String callDate,
            @ApiParam(required = true, value = "预约时间 2016-11-11 15:16:46") @RequestParam("bookDate") String bookDate,
            @ApiParam(required = true, value = "来源,大众出行指定：4") @RequestParam("orderSource") Integer orderSource,
            @ApiParam(required = true, value = "城市") @RequestParam("city") String city,
            @ApiParam(required = true, value = "城市ID") @RequestParam("cityId") Integer cityId,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return Response.OK(orderService.insertOrder(partnerOrderId,
                partnerOrderNo,
                businessType,
                orderType,
                URLDecoder.decode(carType, UTF_8),
                URLDecoder.decode(startPlace, UTF_8),
                URLDecoder.decode(startGps, UTF_8),
                URLDecoder.decode(endPlace, UTF_8),
                expectedKm, URLDecoder.decode(endGps, UTF_8),
                URLDecoder.decode(expectedPrice, UTF_8),
                customerPhone,
                URLDecoder.decode(customerName, UTF_8),
                URLDecoder.decode(callDate, UTF_8),
                URLDecoder.decode(bookDate, UTF_8),
                orderSource,
                URLDecoder.decode(city, UTF_8),
                cityId, sign, channel));
    }

    /**
     * 下单后 – 结算通知
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param sign
     * @param channel
     * @return
     * @author 秦朝胜
     */
    @RequestMapping(value = "settlementNotice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后 – 结算通知",
            httpMethod = "POST",
            response = Response.class,
            value = "下单后 – 结算通知"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> settlementNotice(
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel,
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "1 乘客支付 2 司机代收") @RequestParam("payType") String payType,
            @ApiParam(required = true, value = "需要司机代付的金额") @RequestParam("payAmount") BigDecimal payAmount) {
        orderService.settlementNotice(sign, channel, orderNo, partnerOrderNo, payType, payAmount);
        return Response.OK();
    }

    /**
     * 取消订单
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param cancelType
     * @param reason
     * @param sign
     * @param channel
     * @return
     * @author 房忍
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后 – 订单取消",
            httpMethod = "POST",
            response = Response.class,
            value = "下单后 – 订单取消")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> cancelOrder(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "1 乘客取消 2司机取消 3 其他") @RequestParam("cancelType") Integer cancelType,
            @ApiParam(required = true, value = "违约金金额") @RequestParam("cancelAmount") BigDecimal cancelAmount,
            @ApiParam(required = true, value = "取消原因") @RequestParam("reason") String reason,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.cancelOrder(orderNo,partnerOrderNo,cancelType,cancelAmount,URLDecoder.decode(reason, UTF_8));
        return Response.OK();
    }

    /**
     * 提交开发票申请
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
     * @return
     * @author fangren
     */
    @RequestMapping(value = "postInvoice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "结算后 –提交开发票申请",
            hidden = true,
            httpMethod = "POST",
            response = Response.class,
            value = "结算后 –提交开发票申请"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> postInvoice(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "发票抬头,URLEncode") @RequestParam("invoiceTitle") String invoiceTitle,
            @ApiParam(required = true, value = "发票内容, URLEncode") @RequestParam("invoiceContent") String invoiceContent,
            @ApiParam(required = true, value = "邮寄地址, URLEncode") @RequestParam("address") String address,
            @ApiParam(required = true, value = "邮编") @RequestParam("postCode") String postCode,
            @ApiParam(required = true, value = "收件人, URLEncode") @RequestParam("addressee") String addressee,
            @ApiParam(required = true, value = "联系电话") @RequestParam("phone") String phone,
            @ApiParam(required = true, value = "金额(单位,元)") @RequestParam("amount") BigDecimal amount,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.postInvoice(orderNo, partnerOrderNo,
                URLDecoder.decode(invoiceTitle, UTF_8),
                URLDecoder.decode(invoiceContent, UTF_8),
                URLDecoder.decode(address, UTF_8),
                postCode,
                URLDecoder.decode(addressee, UTF_8),
                phone, amount, sign, channel);
        return Response.OK();
    }


    /**
     * 提供给首汽订单回调接口 - 同步接单司机信息
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param driverId
     * @param driverName
     * @param vehicleLicense
     * @param driverPhone
     * @param carModel
     * @param driverRate
     * @param driverPhoto
     * @param latitude
     * @param longitude
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 周伟
     */
    @RequestMapping(value = "syncOrderInformation", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "提供给首汽订单回调接口 - 同步接单司机信息",
            httpMethod = "POST",
            response = Response.class,
            hidden = true,
            value = "提供给首汽订单回调接口 - 同步接单司机信息")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> syncOrderInformation(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "大众出行司机id") @RequestParam("driverId") Integer driverId,
            @ApiParam(required = true, value = "司机名称") @RequestParam("driverName") String driverName,
            @ApiParam(required = true, value = "司机车牌") @RequestParam("vehicleLicense") String vehicleLicense,
            @ApiParam(required = true, value = "司机手机号") @RequestParam("driverPhone") String driverPhone,
            @ApiParam(required = true, value = "车型") @RequestParam("carModel") String carModel,
            @ApiParam(required = true, value = "司机评分1-5") @RequestParam("driverRate") String driverRate,
            @ApiParam(required = true, value = "司机头像URL") @RequestParam("driverPhoto") String driverPhoto,
            @ApiParam(required = true, value = "司机位置纬度") @RequestParam("latitude") String latitude,
            @ApiParam(required = true, value = "司机位置经度") @RequestParam("latitude") String longitude,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.checkSyncOrderInfoDTO(orderNo, partnerOrderNo, driverId, driverName, vehicleLicense, driverPhone,
                carModel, driverRate, driverPhoto, latitude, longitude, sign, channel);
        CarUser carUser = new CarUser();
        carUser.setId(driverId);
        carUser.setName(URLDecoder.decode(driverName, UTF_8));
        carUser.setCarNumber(URLDecoder.decode(vehicleLicense, UTF_8));
        carUser.setPhone(driverPhone);
        carUser.setGrade(Double.valueOf(driverRate));
        carUser.setCarType(Integer.valueOf(carModel));
        carUser.setHeadPic(driverPhoto);
        carUser.setType(1);
        carUser.setIsValid(1);
        carUser.setDriverSource(1);
        carUserService.valiCarUserExist(carUser);
        orderService.syncOrderInformation(orderNo, driverId);
        return Response.OK();
    }

    /**
     * 下单后 – 轮询订单状态
     *
     * @param orderNo
     * @param partnerOrderNo
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 周伟
     */
    @RequestMapping(value = "pollingOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后 – 轮询订单状态",
            hidden = true,
            httpMethod = "POST",
            response = Response.class,
            value = "下单后 – 轮询订单状态"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> pollingOrder(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return Response.OK(orderService.pollingOrder(orderNo, partnerOrderNo, sign, channel));
    }

    /**
     * 下单后 – 通知支付
     *
     * @param orderNo
     * @param amount
     * @param partnerOrderNo
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 周伟
     */
    @RequestMapping(value = "payNotify", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后 – 通知大众乘客结算",
            httpMethod = "POST",
            response = Response.class,
            value = "下单后 – 通知大众乘客结算"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> payNotify(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "订单金额") @RequestParam("amount") BigDecimal amount,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.payNotify(orderNo,amount);
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 司机接单
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "carUserOrderTaking", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 司机接单",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 司机接单"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> carUserOrderTaking(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.carUserOrderTaking(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 司机出发
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "carUserSetOut", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 司机出发",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 司机出发"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> carUserSetOut(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.carUserSetOut(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 司机到达
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "carUserReach", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 司机到达",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 司机到达"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> carUserReach(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.carUserReach(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 司机服务中
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "orderStart", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 司机服务中",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 司机服务中"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> orderStart(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.orderStart(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 订单改派
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "orderReassign", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 订单改派",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 订单改派"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> orderReassign(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.orderReassign(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 司机代收
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "carUserPay", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 司机代收成功通知",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 司机代收成功通知"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> carUserPay(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.carUserPay(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 取消订单（系统取消/客服取消）
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "syCancelOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 – 取消订单（系统取消/客服取消）",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 取消订单（系统取消/客服取消）"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> syCancelOrder(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.syCancelOrder(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 服务完成结算
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "orderCompleted", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 - 服务完成结算",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 服务完成结算"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> orderCompleted(
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	orderService.orderCompleted(URLDecoder.decode(meta, UTF_8));
    	return Response.OK();
    }
    
    /**
     * 首约回调 – 打印发票申请
     * 
     * @param meta
     * @param eventId
     * @param eventTime
     * @param expiredTime
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 秦朝胜
     */
    @RequestMapping(value = "printInvoiceApplication", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "首约回调 - 打印发票申请",
            httpMethod = "POST",
            response = Response.class,
            value = "首约回调 – 打印发票申请"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> printInvoiceApplication  (
    		@ApiParam(required = true, value = "数据，json字符串") @RequestParam("meta") String meta,
            @ApiParam(required = true, value = "事件id,唯一标识") @RequestParam("eventId") String eventId,
            @ApiParam(required = true, value = "事件发生时间戳") @RequestParam("eventTime") int eventTime,
            @ApiParam(required = true, value = "请求过期时间戳") @RequestParam("expiredTime") int expiredTime,
            @ApiParam(required = true, value = "请求签名，根据签名生成规则计算") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "首汽标志") @RequestParam("channel") String channel) throws Exception {
    	return Response.OK(orderService.printInvoiceApplication(URLDecoder.decode(meta, UTF_8)));
    }
    
    /**
     * 下单后–通知大众是否采用大众司机
     * @param orderNo
     * @param partnerOrderNo
     * @param driverId
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 曾金峰
     */
    @RequestMapping(value = "usingTheDriver", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后–通知大众是否采用大众司机",
            httpMethod = "POST",
            response = Response.class,
            value = "下单后–通知大众是否采用大众司机"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> usingTheDriver(
    		@ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
    		@ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
    		@ApiParam(required = true, value = "大众出行司机ID") @RequestParam("driverId") Integer driverId,
    		@ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
    		@ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
    	orderService.usingTheDriver(orderNo, partnerOrderNo, driverId, sign, channel);
    	return Response.OK();
	}

    /**
     * 司机代收完成通知
     * @param orderNo
     * @param amount
     * @param partnerOrderNo
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 周伟
     */
    @RequestMapping(value = "noticeDriver", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "结算后–司机代收完成通知",
            httpMethod = "POST",
            response = Response.class,
            value = "结算后–司机代收完成通知"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> noticeDriver(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "代收金额") @RequestParam("amount") BigDecimal amount,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.noticeDriver(orderNo,partnerOrderNo,amount);
    	return Response.OK();
    }

    /**
     * 下单后–通知大众停止发单
     * @param orderNo
     * @param partnerOrderNo
     * @param driverId
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     * @author 曾金峰
     */
    @RequestMapping(value = "stopAssNotice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后–通知大众停止发单",
            httpMethod = "POST",
            response = Response.class,
            value = "下单后–通知大众停止发单"
    )
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<?> stopAssNotice(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        orderService.stopAssNotice(orderNo, partnerOrderNo);
        return Response.OK();
    }
}
