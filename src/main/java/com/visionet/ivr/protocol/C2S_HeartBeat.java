package com.visionet.ivr.protocol;

/**
 * 心跳包
 * @author zph
 *
 */
public class C2S_HeartBeat extends MsgBase {
	{
		//消息ID
		setMsgId(106);
		msgStructs.put("value",null);//不知道用来干嘛的
	}
}
