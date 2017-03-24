package com.visionet.ivr.protocol;

/**
 * 未找到客户以前的信息
 * @author zph
 *
 */
public class S2C_FindNoPhone extends MsgBase {
	{
		setMsgId(202);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("电话号码",null);
	}
}
