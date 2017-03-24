package com.visionet.ivr.protocol;

/**
 * 返回注册码
 * @author zph
 *
 */
public class S2C_SetRegnumber extends MsgBase {

	{
		setMsgId(206);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
		msgStructs.put("注册码",null);
	}
}
