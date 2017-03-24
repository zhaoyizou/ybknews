package com.visionet.ivr.protocol;

/**
 * 乘客无订单
 * @author zph
 *
 */
public class S2C_EmptyOrder extends MsgBase{

	{
		setMsgId(2002);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
	}
}
