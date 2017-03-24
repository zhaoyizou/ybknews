package com.visionet.ivr.protocol;

/**
 * 乘客验证结果
 * @author zph
 *
 */
public class S2C_CustomerLogin extends MsgBase {

	{
		setMsgId(2000);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
		msgStructs.put("Result",null);
	}
}
