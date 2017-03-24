package com.visionet.ivr.protocol;

/**
 * 自动外呼结果
 * @author zph
 *
 */
public class C2S_AutoCalloutResult extends MsgBase {

	{
		setMsgId(105);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("Order ID",null);
		msgStructs.put("Telphone NO.",null);
		msgStructs.put("Result",null);
		msgStructs.put("Status",null);
		msgStructs.put("CarNo.",null);
		msgStructs.put("Reason",null);
	}
}
