package com.visionet.ivr.protocol;

/**
 * 返回乘客订单
 * @author zph
 *
 */
public class S2C_CustomerOrders extends MsgBase {

	{
		setMsgId(2001);
		msgStructs.put("SessionID",null);
		msgStructs.put("ChannelID",null);
		msgStructs.put("OrderID",null);
		msgStructs.put("OrderStatus",null);
		msgStructs.put("CarNo",null);
		msgStructs.put("Car position",null);
		msgStructs.put("road",null);
		msgStructs.put("lane",null);
		msgStructs.put("branch",null);
		msgStructs.put("roadno",null);
		msgStructs.put("building",null);
		msgStructs.put("doorno",null);
		msgStructs.put("mark",null);
		msgStructs.put("crossroad",null);
		msgStructs.put("pickupPlace",null);
		msgStructs.put("destn",null);
		msgStructs.put("takeTime",null);
	}
}
