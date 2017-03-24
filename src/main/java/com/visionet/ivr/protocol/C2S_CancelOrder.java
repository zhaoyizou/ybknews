package com.visionet.ivr.protocol;

/**
 * 取消订单
 * @author zph
 *
 */
public class C2S_CancelOrder extends MsgBase{

	{
		setMsgId(102);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("订单号码",null);
	}
}
