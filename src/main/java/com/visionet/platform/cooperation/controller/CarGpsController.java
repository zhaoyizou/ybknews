package com.visionet.platform.cooperation.controller;

import com.alibaba.fastjson.JSON;
import com.visionet.core.support.Response;
import com.visionet.platform.cooperation.dto.CarLocationDTO;
import com.visionet.platform.cooperation.dto.CarLocationListDTO;
import com.visionet.platform.cooperation.service.CarGpsService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author张晓虎
 * 车辆Gps位置API
 */
@Controller
@RequestMapping("cooperation/cargps")
@Api(value = "cooperation/cargps", tags = {"Gps Location API"}, description = "车辆Gps位置API")
public class CarGpsController {
    private static final Logger log = LoggerFactory.getLogger(CarGpsController.class);

    @Autowired
    private CarGpsService carGpsService;

    /**
     * @author张晓虎
     * 下单前 – 查看附近司机位置
     */
    @RequestMapping(value = "nearByCarLocationList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单前 – 查看附近司机位置",
            httpMethod = "POST",
            response = Response.class,
            value = "下单前 – 查看附近司机位置")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<List<CarLocationListDTO>> nearByCarLocationList(
            @ApiParam(required = true, value = "业务类型,大众指定业务类型为1,代表专车") @RequestParam("businessType") Integer businessType,
            @ApiParam(required = true, value = "经度") @RequestParam("longitude") Double longitude,
            @ApiParam(required = true, value = "纬度") @RequestParam("latitude") Double latitude,
            @ApiParam(required = true, value = "范围半径 单位是m") @RequestParam("radius") Integer radius,
            @ApiParam(required = true, value = "城市ID") @RequestParam("cityId") Integer cityId,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) {
        return Response.OK(carGpsService.nearByCarLocationList(businessType, longitude, latitude, radius, cityId, sign, channel));
    }

    /**
     * @author张晓虎
     * 下单后 – 查看司机实时位置
     */
    @RequestMapping(value = "getCarLocation", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下单后 – 查询司机实时位置", httpMethod = "POST", response = Response.class, value = "下单后 – 查询司机实时位置")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public Response<CarLocationDTO> getCarLocation(
            @ApiParam(required = true, value = "大众出行订单号") @RequestParam("orderNo") String orderNo,
            @ApiParam(required = true, value = "合作方订单号") @RequestParam("partnerOrderNo") String partnerOrderNo,
            @ApiParam(required = true, value = "参见1.8 sign生成算法") @RequestParam("sign") String sign,
            @ApiParam(required = true, value = "渠道名称，由大众出行给定") @RequestParam("channel") String channel) {
        CarLocationDTO carLocation = carGpsService.getCarLocation(orderNo, partnerOrderNo, sign, channel);
        log.info("getCarLocation返回结果:{}", JSON.toJSONString(carLocation));
        return Response.OK(carLocation);
    }
}
