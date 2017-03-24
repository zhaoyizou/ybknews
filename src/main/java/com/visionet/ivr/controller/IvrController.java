package com.visionet.ivr.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.visionet.core.support.Response;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.protocol.S2C_AutoCallout;
import com.visionet.ivr.socket.AbsSocketHandle;
import com.visionet.ivr.socket.IVRClient;
import com.wordnik.swagger.annotations.ApiParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RequestMapping("ivr/auto_callout")
@Api(value = "ivr/auto_callout", tags = { "IVR" }, description = "ivr相关接口")
@Controller
public class IvrController {

	//public String 
	Logger log=LoggerFactory.getLogger(IvrController.class);
	
	/**
	 * 自动外呼
	 * @param orderId
	 * @param custromPhone
	 * @param carNum
	 * @param Status 8有车,12 无车
	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	@ApiOperation(notes = "自动外呼", httpMethod = "POST", response = Response.class, value = "接收要转发的司机ID置")
	@ApiResponses(value = { @ApiResponse(code = 1, message = "无效的司机ID") })
	
	public String Auto_CallOut(
			@ApiParam(required=true,value="订单ID")String orderId,
			@ApiParam(required=true,value="乘客电话号码")String custromPhone,
			@ApiParam(required=true,value="车牌号码")String carNum,
			@ApiParam(required=true,value="状态")int status) throws MyException, IOException
	{
		//要判断老系统外呼是否已经成功.
		S2C_AutoCallout ac=new S2C_AutoCallout();
		ac.put("订单号", orderId);
		ac.put("状态Status", status+"");
		ac.put("乘客电话", custromPhone);
		ac.put("中标车辆", carNum);
		ac.put("车型", "-1");
		AbsSocketHandle client=AbsSocketHandle.getClient(IVRClient.class.getName());
		client.sendMsg(ac);
		return "true";
	}
	
	
	
}
