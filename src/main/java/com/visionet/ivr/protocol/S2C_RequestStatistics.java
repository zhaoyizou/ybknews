package com.visionet.ivr.protocol;

/**
 * 请求统计数据
 * @author zph
 *
 */
public class S2C_RequestStatistics extends MsgBase {
	{
		setMsgId(207);
		msgStructs.put("统计数据的起始时间",null);
		msgStructs.put("统计数据的结束时间",null);
	}
}
