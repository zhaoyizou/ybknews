package com.visionet.ivr.protocol;

/**
 * 开始自动外呼
 * @author zph
 *
 */
public class S2C_AutoCallout extends MsgBase {

	{
		setMsgId(205);
		msgStructs.put("订单号",null);
		msgStructs.put("状态 Status",null);
		msgStructs.put("乘客电话",null);
		msgStructs.put("中标车辆",null);
		msgStructs.put("车辆位置",null);
		msgStructs.put("车型",null);
		
	}
}
