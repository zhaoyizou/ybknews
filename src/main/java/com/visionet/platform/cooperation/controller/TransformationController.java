package com.visionet.platform.cooperation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.visionet.core.support.Response;
import com.visionet.platform.cooperation.service.TransformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * projectName:dzcx_partner
 * author:liusy@visionet.com.cn
 * data:2016/11/29
 */
@Controller
@RequestMapping("transformation")
@Api(value = "transformation", description = "数据转发Api,大众内部使用", tags = {"Transformation Api"})
public class TransformationController {
    @Autowired
    private TransformationService transformationService;

    /**
     * 查看车型转发接口
     *
     * @param cityId
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getGroupsPrice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查看车型转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "查看车型转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject getGroupsPrice(
            @ApiParam(required = true, value = "城市ID") @RequestParam("cityId") String cityId,
            @ApiParam(required = true, value = "渠道名称，由首汽约车给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.getGroupsPrice(cityId, channel);
    }

    /**
     * 查看附近司机转发接口
     *
     * @param longitude
     * @param latitude
     * @param maxDistance
     * @param maxDriverCount
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "nearbyDrivers", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查看附近司机转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "查看附近司机转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject nearbyDrivers(
            @ApiParam(required = true, value = "查询点坐标-经度") @RequestParam("longitude") String longitude,
            @ApiParam(required = true, value = "查询点坐标-纬度") @RequestParam("latitude") String latitude,
            @ApiParam(required = true, value = "最大范围，单位公里，默认10公里") @RequestParam("maxDistance") String maxDistance,
            @ApiParam(required = true, value = "最多返回司机数量，默认30") @RequestParam("maxDriverCount") String maxDriverCount,
            @ApiParam(required = true, value = "渠道名称，由首汽约车给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.nearbyDrivers(longitude, latitude, maxDistance, maxDriverCount, channel);
    }

    /**
     * 查询司机实时位置转发接口
     *
     * @param orderId
     * @param partnerOrderNo
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "driverLocation", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查询司机实时位置转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "查询司机实时位置转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject getPartnerDriverLocation(
            @ApiParam(required = true, value = "订单号(出行订单号)") @RequestParam("partnerOrderNo") String orderId,
            @ApiParam(required = true, value = "订单号(合作方订单号)") @RequestParam("orderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.getPartnerDriverLocation(orderId, partnerOrderNo, channel);
    }

    /**
     * 下单转发接口
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
    @RequestMapping(value = "postInstantOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "下单转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject postInstantOrder(
            @ApiParam(required = true, value = "预定时间,时间戳.秒数") @RequestParam("bookingDate") String bookingDate,
            @ApiParam(required = true, value = "乘车人手机号") @RequestParam("riderPhone") String riderPhone,
            @ApiParam(required = true, value = "上车地点(URLEncode编码后的值)") @RequestParam("bookingStartAddr") String bookingStartAddr,
            @ApiParam(required = true, value = "下车地点(URLEncode编码后的值)") @RequestParam("bookingEndAddr") String bookingEndAddr,
            @ApiParam(required = true, value = "上车地点经度坐标") @RequestParam("bookingStartPointLo") String bookingStartPointLo,
            @ApiParam(required = true, value = "上车地点纬度坐标") @RequestParam("bookingStartPointLa") String bookingStartPointLa,
            @ApiParam(required = true, value = "下车地点经度坐标") @RequestParam("bookingEndPointLo") String bookingEndPointLo,
            @ApiParam(required = true, value = "下车地点纬度坐标") @RequestParam("bookingEndPointLa") String bookingEndPointLa,
            @ApiParam(required = true, value = "设备唯一标识码") @RequestParam("imei") String imei,
            @ApiParam(required = true, value = "城市ID") @RequestParam("cityId") String cityId,
            @ApiParam(required = true, value = "选购车型,多车型用,分隔.如 34,35") @RequestParam("groupIds") String groupIds,
            @ApiParam(required = true, value = "预估金额,多车型时以最低价格为准") @RequestParam("estimatedAmount") String estimatedAmount,
            @ApiParam(required = true, value = "乘车人姓名(URLEncode编码后的值)") @RequestParam("riderName") String riderName,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "渠道名称，由首汽约车给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.postInstantOrder(bookingDate,
                riderPhone,
                bookingStartAddr,
                bookingEndAddr,
                bookingStartPointLo,
                bookingStartPointLa,
                bookingEndPointLo,
                bookingEndPointLa,
                imei,
                cityId,
                groupIds,
                estimatedAmount,
                riderName,
                partnerOrderNo,
                channel
        );
    }

    /**
     * 取消订单转发接口
     * @author fangren
     * @param orderNo
     * @param partnerOrderNo
     * @param cancelType
     * @param reason
     * @param sign
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "取消订单转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "取消订单转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject cancelOrder(
    		 @ApiParam(required = true, value = "合作方订单号") @RequestParam("orderNo") String orderNo,
             @ApiParam(required = true, value = "大众出行订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
             @ApiParam(required = true, value = "客户违约金") @RequestParam("partnerCancelOrderPenaltyAmt") String partnerCancelOrderPenaltyAmt,
             @ApiParam(required = true, value = "取消类型：首汽约车给定 106 大众出行订单取消") @RequestParam("cancelType") String cancelType,
             @ApiParam(required = true, value = "取消原因") @RequestParam("reason") String reason,
             @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.cancelOrder(orderNo,partnerOrderNo,partnerCancelOrderPenaltyAmt,cancelType,reason,channel);
    }
    
    /**
     * 开发票申请转发接口
     * @author fangren
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
     */
    @RequestMapping(value = "postInvoice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "开发票申请转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "开发票申请转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject postInvoice(
    		@ApiParam(required = true, value = "合作方订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "发票抬头,URLEncode") @RequestParam("invoiceTitle") String invoiceTitle,
            @ApiParam(required = true, value = "发票内容, URLEncode") @RequestParam("invoiceContent") String invoiceContent,
            @ApiParam(required = true, value = "邮寄地址, URLEncode") @RequestParam("address") String address,
            @ApiParam(required = true, value = "邮编") @RequestParam("postCode") String postCode,
            @ApiParam(required = true, value = "收件人, URLEncode") @RequestParam("addressee") String addressee,
            @ApiParam(required = true, value = "联系电话") @RequestParam("phone") String phone,
            @ApiParam(required = true, value = "金额(单位,元)") @RequestParam("amount") String amount,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.postInvoice(orderNo, partnerOrderNo,invoiceTitle,invoiceContent,address,postCode,addressee,phone,amount,channel);
    }
    
    /**
     * 评价司机转发接口
     * @param orderNo
     * @param partnerOrderNo
     * @param evaluateScore
     * @param driverId
     * @param memo
     * @param channel
     * @return
     * @throws Exception
     * @author 王志鹏
     */
    @RequestMapping(value = "gradeOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "结算后 – 评价司机转发接口",
            httpMethod = "POST",
            response = Response.class,
            value = "结算后 – 评价司机转发接口")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public JSONObject gradeOrder(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "评分，可选值1到5") @RequestParam("evaluateScore") Integer evaluateScore,
            @ApiParam(required = true, value = "评分，可选值1到5") @RequestParam("driverId") String driverId,
            @ApiParam(required = true, value = "评价内容") @RequestParam("memo") String memo,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.gradeOrder(orderNo,driverId, evaluateScore,partnerOrderNo,  memo,channel);
    }


    /**
     * @throws Exception
     *             调用首约 司机待服务回调接口 运营状态(1:载客2:接单3:空驶4:停运)
     */
    @RequestMapping(value = "driver/waitServe", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机待服务回调接口", httpMethod = "POST", response = Response.class, value = "司机待服务回调接口")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject waitServe(
            @ApiParam(required = true, value = "司机手机号") @RequestParam("username") String username,
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "高德经度") @RequestParam("lo") String lo,
            @ApiParam(required = true, value = "高德纬度") @RequestParam("la") String la,
            @ApiParam(required = true, value = "海拔") @RequestParam("altitude") String altitude,
            @ApiParam(required = true, value = "方位角(车头朝向)") @RequestParam("azimuth") String azimuth,
            @ApiParam(required = true, value = "车速") @RequestParam("speed") String speed ,
            @ApiParam(required = true, value = "运营状态(1:载客2:接单3:空驶4:停运)") @RequestParam("status") String status)
            throws Exception {

        return transformationService.waitServe(username, sqycOrderNumber, partnerOrderNumber, lo, la, altitude, azimuth,
                        speed, status);
    }

    /**
     * @throws Exception
     * @author张晓虎 --调用首约 --司机到达乘客出发地回调接口
     */
    @RequestMapping(value = "driver/arriveNow", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机到达乘客出发地回调接口", httpMethod = "POST", response = Response.class, value = "司机到达乘客出发地回调接口")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject arriveNow(
            @ApiParam(required = true, value = "司机的手机号") @RequestParam("username") String username,
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "高德经度") @RequestParam("lo") String lo,
            @ApiParam(required = true, value = "高德纬度") @RequestParam("la") String la,
            @ApiParam(required = true, value = "海拔") @RequestParam("altitude") String altitude,
            @ApiParam(required = true, value = "方位角(车头朝向)") @RequestParam("azimuth") String azimuth,
            @ApiParam(required = true, value = "车速") @RequestParam("speed") String speed,
            @ApiParam(required = true, value = "运营状态(1:载客2:接单3:空驶4:停运)") @RequestParam("status") String status)
            throws Exception {
        System.err.println("司机到达乘客出发地回调接口");
        return transformationService.arriveNow(username, sqycOrderNumber, partnerOrderNumber, lo, la, altitude, azimuth,
                speed, status);
    }

    /**
     * @throws Exception
     * @author张晓虎 --调用首约 --司机开始服务回调接口
     */
    @RequestMapping(value = "driver/startnewService", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机开始服务回调接口", httpMethod = "POST", response = Response.class, value = "司机开始服务回调接口")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject startnewService(
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "司机的手机号") @RequestParam("username") String username,
            @ApiParam(required = true, value = "高德经度") @RequestParam("lo") String lo,
            @ApiParam(required = true, value = "高德纬度") @RequestParam("la") String la,
            @ApiParam(required = true, value = "海拔") @RequestParam("altitude") String altitude,
            @ApiParam(required = true, value = "方位角(车头朝向)") @RequestParam("azimuth") String azimuth,
            @ApiParam(required = true, value = "车速") @RequestParam("speed") String speed,
            @ApiParam(required = true, value = "运营状态(1:载客2:接单3:空驶4:停运)") @RequestParam("status") String status,
            @ApiParam(required = true, value = "实际上车地址") @RequestParam("factstartaddr") String factstartaddr)
            throws Exception {
        System.err.println("司机开始服务回调接口");
        return transformationService.startnewService(sqycOrderNumber, partnerOrderNumber, username, lo, la, altitude,
                azimuth, speed, status, factstartaddr);
    }

    /**
     * @throws Exception
     * @author张晓虎 --调用首约 --司机生成账单接口回调接口
     */
    @RequestMapping(value = "order/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机生成账单接口回调接口", httpMethod = "POST", response = Response.class, value = "司机生成账单接口回调接口")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject submit(
            @ApiParam(required = true, value = "司机的手机号") @RequestParam("username") String username,
            @ApiParam(required = true, value = "首汽订单编号") @RequestParam("orderNumber") String orderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "全部费用") @RequestParam("allFees") String allFees,
            @ApiParam(required = true, value = "服务开始时间 时间戳 秒") @RequestParam("startTime") String startTime,
            @ApiParam(required = true, value = "服务结束时间 时间戳 秒") @RequestParam("entTime") String entTime,
            @ApiParam(required = true, value = "高德实际下车经度") @RequestParam("lo") String lo,
            @ApiParam(required = true, value = "高德实际下车纬度") @RequestParam("la") String la,
            @ApiParam(required = true, value = "时长(毫秒)") @RequestParam("allTime") String allTime,
            @ApiParam(required = true, value = "里程") @RequestParam("mileage") String mileage,
            @ApiParam(required = true, value = "长途里程-超回空里程") @RequestParam("longMileage") String longMileage,
            @ApiParam(required = true, value = "超时费") @RequestParam("timeOut") String timeOut,
            @ApiParam(required = true, value = "超时数") @RequestParam("outMin") String outMin,
            @ApiParam(required = true, value = "超公里费") @RequestParam("veryLong") String veryLong,
            @ApiParam(required = true, value = "超公里数") @RequestParam("outMil") String outMil,
            @ApiParam(required = true, value = "夜间时长") @RequestParam("nighitDuration") String nighitDuration,
            @ApiParam(required = true, value = "夜间时长费用 (默认为0,如果有夜间时长费用，请加到夜间里程费用)") @RequestParam("nighitDurationFees") String nighitDurationFees,
            @ApiParam(required = true, value = "夜间里程") @RequestParam("nightMileage") String nightMileage,
            @ApiParam(required = true, value = "夜间里程费用") @RequestParam("yjFees") String yjFees,
            @ApiParam(required = true, value = "高峰时长") @RequestParam("hotDuration") String hotDuration,
            @ApiParam(required = true, value = "高峰时长费用") @RequestParam("hotDurationFees") String hotDurationFees,
            @ApiParam(required = true, value = "高峰里程") @RequestParam("hotMileage") String hotMileage,
            @ApiParam(required = true, value = "高峰里程费用(默认为0,如果有高峰里程费用，请放到高峰时长费用)") @RequestParam("hotMileageFees") String hotMileageFees,
            @ApiParam(required = true, value = "等待时长、分钟") @RequestParam("waitingMinutes") String waitingMinutes,
            @ApiParam(required = true, value = "等待费用") @RequestParam("waitingFee") String waitingFee,
            @ApiParam(required = true, value = "套餐价") @RequestParam("pac") String pac,
            @ApiParam(required = true, value = "套餐含分钟") @RequestParam("pacIncludeMinute") String pacIncludeMinute,
            @ApiParam(required = true, value = "套餐含里程") @RequestParam("pacIncludeMileage") String pacIncludeMileage,
            @ApiParam(required = true, value = "高速费") @RequestParam("gsFees") String gsFees,
            @ApiParam(required = true, value = "停车费") @RequestParam("tcFees") String tcFees,
            @ApiParam(required = true, value = "机场费") @RequestParam("jcFees") String jcFees,
            @ApiParam(required = true, value = "食宿费") @RequestParam("ssFees") String ssFees,
            @ApiParam(required = true, value = "空驶费(对应longMileage)") @RequestParam("ksFees") String ksFees,
            @ApiParam(required = true, value = "实际支付金额") @RequestParam("actualPayAmount") String actualPayAmount,
			@ApiParam(required = true, value = "其他费用") @RequestParam("othersFee") String othersFee,
			@ApiParam(required = true, value = "抹零") @RequestParam("decimalsFees") String decimalsFees
    )
            throws Exception {
        return transformationService.submit(username, orderNumber, partnerOrderNumber, allFees, startTime,
                entTime, lo, la, allTime, mileage, longMileage, timeOut, outMin, veryLong, outMil, nighitDuration,
                nighitDurationFees, nightMileage, yjFees, hotDuration, hotDurationFees, hotMileage, hotMileageFees,
                waitingMinutes, waitingFee, pac, pacIncludeMinute, pacIncludeMileage, gsFees, tcFees, jcFees, ssFees,ksFees,
				actualPayAmount, othersFee, decimalsFees);
    }


    /**
     * @throws Exception
     * @author张晓虎 --调用首约 --司机结算回调接口
     */
    @RequestMapping(value = "driver/banlanceAccounts", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机结算回调接口", httpMethod = "POST", response = Response.class, value = "司机结算回调接口")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject banlanceAccounts(
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "司机的手机号") @RequestParam("username") String username) throws Exception {
        return transformationService.banlanceAccounts(username, sqycOrderNumber, partnerOrderNumber);
    }

    /**
     * @throws Exception
     *  --调用首约 --司机代收成功回调接口(包括全额代收)
     */
    @RequestMapping(value = "driver/driverDpay", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机代收成功回调接口(包括全额代收)", httpMethod = "POST", response = Response.class, value = "司机代收成功回调接口(包括全额代收)")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject driverDpay(
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "实际代收金额") @RequestParam("amount") String amount,
            @ApiParam(required = true, value = "司机的手机号") @RequestParam("username") String username
    ) throws Exception {
        return transformationService.driverDpay(username, sqycOrderNumber, partnerOrderNumber, amount);
    }

    /**
     * @throws Exception
     * @author --调用首约 --司机端打印发票回调接口
     */
    @RequestMapping(value = "driver/invoiceprint", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机端打印发票回调接口", httpMethod = "POST", response = Response.class, value = "司机端打印发票回调接口")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject invoiceprint(
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "司机的手机号") @RequestParam("username") String username) throws Exception {
        return transformationService.invoiceprint(sqycOrderNumber, partnerOrderNumber, username);
    }

    /**
     * @throws Exception
     * @author房忍 --调用首约 --司机信息回调接口(改派)
     */
    @RequestMapping(value = "driver/driverInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "司机信息回调接口(改派)", httpMethod = "POST", response = Response.class, value = "司机信息回调接口(改派)")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject driverInfo(
            @ApiParam(required = true, value = "渠道号") @RequestParam("channel") String channel,
            @ApiParam(required = true, value = "是不是改派订单 0：否；1：是") @RequestParam("isReassign") String isReassign,
            @ApiParam(required = true, value = "司机id") @RequestParam("driverId") String driverId,
            @ApiParam(required = true, value = "首汽约车-订单编号") @RequestParam("sqycOrderNumber") String sqycOrderNumber,
            @ApiParam(required = true, value = "合作者订单编号") @RequestParam("partnerOrderNumber") String partnerOrderNumber,
            @ApiParam(required = true, value = "司机姓名") @RequestParam("name") String name,
            @ApiParam(required = true, value = "司机电话") @RequestParam("phone") String phone,
            @ApiParam(required = true, value = "车辆颜色") @RequestParam("color") String color,
            @ApiParam(required = true, value = "车牌") @RequestParam("licensePlates") String licensePlates,
//            @ApiParam(required = true, value = "车型Id") @RequestParam("modelId") String modelId,
            @ApiParam(required = true, value = "车型") @RequestParam("modelName") String modelName,
            @ApiParam(required = true, value = "响应时间,时间戳,秒") @RequestParam("responseTime") String responseTime,
            @ApiParam(required = true, value = "服务城市 ") @RequestParam("cityId") String cityId,
            @ApiParam(required = true, value = "身份证号") @RequestParam("idCardNo") String idCardNo,
            @ApiParam(required = true, value = "服务监督码") @RequestParam("superintendNo") String superintendNo,
            @ApiParam(required = true, value = "驾龄") @RequestParam("drivingYears") String drivingYears,
            @ApiParam(required = true, value = "司机头像") @RequestParam("partnerPhotoSrct") String partnerPhotoSrct,
            @ApiParam(required = true, value = "车型:舒适型、商务7座、豪华型(线上没有)") @RequestParam("groupName") String groupName) throws Exception {
        return transformationService.driverInfo(channel, isReassign, driverId, sqycOrderNumber, partnerOrderNumber, name,
                phone, color, licensePlates, modelName, responseTime, cityId, idCardNo, superintendNo,
                drivingYears, partnerPhotoSrct, groupName);
    }

    /**
     * @throws Exception
     * @author --下单后 –通知支付结算结果
     */
    @RequestMapping(value = "payNotify", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后 –通知支付结算结果", httpMethod = "POST", response = Response.class, value = "下单后 –通知支付结算结果")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "invalid input") })
    public JSONObject payNotify(
            @ApiParam(required = true, value = "订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "订单本次已结算总金额(单位,元)") @RequestParam("amount") String amount,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "优惠券抵扣金额(单位,元)(明细)") @RequestParam("couponAmount") String couponAmount,
            @ApiParam(required = true, value = "充值账户结算金额(单位,元)(明细)") @RequestParam("chargeSettleAmount") String chargeSettleAmount,
            @ApiParam(required = true, value = "赠送账户结算金额(单位,元)(明细)") @RequestParam("giftSettleAmount") String giftSettleAmount,
            @ApiParam(required = true, value = "信用卡账户结算金额(单位,元)(明细)") @RequestParam("customerCreditcardAmount") String customerCreditcardAmount,
            @ApiParam(required = true, value = "首汽司机应代收金额(单位,元)(明细)") @RequestParam("driverPay") String driverPay,
            @ApiParam(required = true, value = "乘客打印发票金额(单位，元)") @RequestParam("ticketAmount") String ticketAmount,
            @ApiParam(required = true, value = "渠道名称，由首汽约车给定") @RequestParam("channel") String channel) throws Exception {
        return transformationService.payNotify(orderNo, amount, partnerOrderNo, couponAmount, chargeSettleAmount, giftSettleAmount, customerCreditcardAmount, driverPay, ticketAmount, channel);
    }
    
}
