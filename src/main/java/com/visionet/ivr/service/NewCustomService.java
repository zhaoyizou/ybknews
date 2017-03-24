package com.visionet.ivr.service;

import com.visionet.ivr.exception.MsgException;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.protocol.MsgBase;
import com.visionet.ivr.protocol.S2C_FindNoPhone;
import com.visionet.ivr.protocol.S2C_FindPhone;

public class NewCustomService {

	/**
	 * 
	 * 查询订单是否存在,并返回消息
	 * @param cfind
	 * @param client
	 * @return
	 * @throws MsgException
	 * @throws MyException 
	 */
	public static MsgBase C2S_CUSTOMER_PHONE(MsgBase cfind) throws MsgException, MyException
	{
		S2C_FindPhone find=	 new S2C_FindPhone();//找到信息
		find.put("Sessionid", cfind.get("Sessionid"));
		find.put("ChannelID", cfind.get("ChannelID"));
		
		S2C_FindNoPhone ufind=new S2C_FindNoPhone();//没有找到信息
		ufind.put("Sessionid", cfind.get("Sessionid"));
		ufind.put("ChannelID", cfind.get("ChannelID"));
		
		return find;
		
	}
	
	public static MsgBase C2S_CANCEL_ORDER(MsgBase msg)
	{
		return null;
	}

	
}
