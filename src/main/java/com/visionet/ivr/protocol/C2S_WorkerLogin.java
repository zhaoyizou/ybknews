package com.visionet.ivr.protocol;

/**
 * 请求IVR登录
 * @author zph
 *
 */
public class C2S_WorkerLogin extends MsgBase {
	
	{
		setMsgId(100);
		msgStructs.put("工号",null);
		msgStructs.put("Password",null);
		
	}
	
}
