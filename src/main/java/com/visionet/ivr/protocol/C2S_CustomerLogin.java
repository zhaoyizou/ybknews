package com.visionet.ivr.protocol;

/**
 * 乘客验证
 * @author zph
 *
 */
public class C2S_CustomerLogin extends MsgBase {

	{
		setMsgId(1000);
		msgStructs.put("SessionID",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("phone",null);
		msgStructs.put("Password",null);
	}
}
