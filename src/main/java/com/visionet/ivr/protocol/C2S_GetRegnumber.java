package com.visionet.ivr.protocol;

/**
 * 索取注册码
 * @author zph
 *
 */
public class C2S_GetRegnumber extends MsgBase {
	{
		setMsgId(104);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
	}
}
