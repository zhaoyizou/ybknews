package com.visionet.ivr.protocol;

/**
 * 查询出乘客的近期订单
 * @author zph
 *
 */
public class C2S_CustomerOrders extends MsgBase {

	{
		setMsgId(1001);
		msgStructs.put("SessionID",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("phone",null);
		msgStructs.put("starttime",null);
		msgStructs.put("endtime",null);
	}
}
