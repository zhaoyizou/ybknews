package com.visionet.ivr.protocol;

public class C2S_BackCall extends MsgBase{
	{
		setMsgId(2005);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("value",null);//不知道用来干嘛?默认-1
	}

}
