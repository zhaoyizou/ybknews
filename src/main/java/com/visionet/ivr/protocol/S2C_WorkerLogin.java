package com.visionet.ivr.protocol;

/**
 * IVR登录结果
 * @author zph
 *
 */
public class S2C_WorkerLogin extends MsgBase {
	{
		setMsgId(200);
		msgStructs.put("工号",null);
		msgStructs.put("Result",null);
	}
	
	
}
