package com.visionet.ivr.protocol;

/**
 * 回复统计数据
 * @author zph
 *
 */
public class C2S_ResponseStatistics extends MsgBase {

	{
		setMsgId(107);
		msgStructs.put("接线员进电量",null);
		msgStructs.put("接线员应答数",null);
		msgStructs.put("VIP进电量",null);
		msgStructs.put("VIP应答数",null);
		msgStructs.put("手机IVR进电量",null);
		msgStructs.put("手机IVR应答数",null);
		msgStructs.put("座机IVR进电量",null);
		msgStructs.put("座机IVR应答数",null);
		msgStructs.put("投诉进电量",null);
		msgStructs.put("投诉应答数",null);
		msgStructs.put("驾驶员进电量",null);
		msgStructs.put("驾驶员应答数",null);
		msgStructs.put("呼出",null);
		msgStructs.put("统计数据的起始时间",null);
		msgStructs.put("统计数据的结束时间",null);
	}
}
