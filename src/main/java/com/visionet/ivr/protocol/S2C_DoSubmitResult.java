package com.visionet.ivr.protocol;

/**
 * 提交订单结果
 * @author zph
 *
 */
public class S2C_DoSubmitResult extends MsgBase {

	{
		setMsgId(204);
		msgStructs.put("Sessionid",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("订单编号",null);
		msgStructs.put("提交结果",null);
		msgStructs.put("电话号码",null);
	}
}
