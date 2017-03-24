package com.visionet.ivr.protocol;

/**
 * 来电话，请求得到客户以前的信息
 * @author zph
 *
 */
public class C2S_CustomerPhone extends MsgBase{
	{
		setMsgId(101);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
	}
	
	
	
}
