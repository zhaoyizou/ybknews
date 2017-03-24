package com.visionet.ivr.protocol;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.visionet.ivr.exception.MyException;

public class MsgBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  uuid;
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 消息ID
	 */
	private String split = " ";
	/**
	 * 消息结构
	 */
	protected LinkedHashMap<String, String> msgStructs = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getMsgStructs() {
		return this.msgStructs;
	}
	
	{
		//// 默认消息ID排第一位
		msgStructs.put("MsgId", this.getMsgId() + "");
	}


	private int msgId;
	private String msgName, msgDescription;

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getMsgDescription() {
		return msgDescription;
	}

	public void setMsgDescription(String msgDescription) {
		this.msgDescription = msgDescription;
	}

	public int getMsgId() {
		return this.msgId;
	}

	public void setMsgId(int msgId) {
		
		this.msgId = msgId;
		msgStructs.put("MsgId",msgId + "");
	}

	/**
	 * 修改value值
	 * 
	 * @param key
	 * @param val
	 * @throws MyException
	 */
	public void put(String key, String val) throws MyException {
		if (msgStructs.containsKey(key)) {
			msgStructs.put(key,val!=null?val.replace(split, ""):val);//删除分隔符.否则不能正确解析消息
		} else {
			throw new MyException(this.getClass().getName() + " struct 不存在属性:" + key);
		}
	}

	public String get(String key) throws MyException {
		if (msgStructs.containsKey(key)) {
			return msgStructs.get(key);
		} else {
			throw new MyException(this.getClass().getName() + " struct 不存在属性:" + key);
		}
	}

}
