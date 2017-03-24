package com.visionet.ivr.protocol;

/**
 * 取消订单结果
 * @author zph
 *
 */
public class S2C_CancelOrderResult extends MsgBase {

	{
		setMsgId(203);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("订单号码",null);
		msgStructs.put("结果",null);
	}
}
