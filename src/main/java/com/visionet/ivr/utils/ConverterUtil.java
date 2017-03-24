package com.visionet.ivr.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.protocol.C2S_AutoCalloutResult;
import com.visionet.ivr.protocol.C2S_BackCall;
import com.visionet.ivr.protocol.C2S_CancelOrder;
import com.visionet.ivr.protocol.C2S_CustomerLogin;
import com.visionet.ivr.protocol.C2S_CustomerOrders;
import com.visionet.ivr.protocol.C2S_CustomerPhone;
import com.visionet.ivr.protocol.C2S_DoSubmit;
import com.visionet.ivr.protocol.C2S_GetRegnumber;
import com.visionet.ivr.protocol.C2S_HeartBeat;
import com.visionet.ivr.protocol.C2S_ResponseStatistics;
import com.visionet.ivr.protocol.C2S_WorkerLogin;
import com.visionet.ivr.protocol.MsgBase;
import com.visionet.ivr.protocol.S2C_AutoCallout;
import com.visionet.ivr.protocol.S2C_CancelOrderResult;
import com.visionet.ivr.protocol.S2C_CustomerLogin;
import com.visionet.ivr.protocol.S2C_CustomerOrders;
import com.visionet.ivr.protocol.S2C_DoSubmitResult;
import com.visionet.ivr.protocol.S2C_EmptyOrder;
import com.visionet.ivr.protocol.S2C_FindNoPhone;
import com.visionet.ivr.protocol.S2C_FindPhone;
import com.visionet.ivr.protocol.S2C_HeartBeat;
import com.visionet.ivr.protocol.S2C_RequestStatistics;
import com.visionet.ivr.protocol.S2C_SetRegnumber;
import com.visionet.ivr.protocol.S2C_WorkerLogin;

public class ConverterUtil {

	private static final Logger log=LoggerFactory.getLogger(ConverterUtil.class);
	/**
	 * 将int数值转换为占四个字节的byte数组，低位在前，高位在后
	 * 
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，高位在前，低位在后
	 */
	public static byte[] intToBytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * byte数组转int数值，低位在前，高位在后
	 * 
	 * @param src
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16)
				| ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

	/**
	 * byte数组转int数值， 高位在前,低位在后
	 */
	public static int bytesToInt2(byte[] src, int offset) {
		int value;
		value = (int) (((src[offset] & 0xFF) << 24) | ((src[offset + 1] & 0xFF) << 16) | ((src[offset + 2] & 0xFF) << 8)
				| (src[offset + 3] & 0xFF));
		return value;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，高位在前，低位在后
	 */
	public static byte[] ShortToBytes2(short value) {
		byte[] src = new byte[2];
		src[0] = (byte) ((value >> 8) & 0xFF);
		src[1] = (byte) (value & 0xFF);
		/*
		 * src[2] = (byte) ((value >> 8) & 0xFF); src[3] = (byte) (value &
		 * 0xFF);
		 */
		return src;
	}

	/**
	 * byte数组转short数值， 高位在前,低位在后
	 * 
	 * @param src
	 * @param offset
	 * @return
	 */
	public static short bytesToShort2(byte[] src, int offset) {
		short value;
		value = (short) (((src[offset] & 0xFF) << 8) | (src[offset + 1] & 0xFF));
		return value;
	}

	/**
	 * 把消息转换为字节数组
	 * 
	 * @return
	 */
	public static byte[] MsgtoBytes(MsgBase model) {
		StringBuffer str = new StringBuffer();
		LinkedHashMap<String, String> kv = model.getMsgStructs();
		Set<String> keys = kv.keySet();
		String val = null;
		for (String key : keys) {
			val = (String) kv.get(key);
			val = val == null ? "NULL" : val;
			str.append(URLEncoder.encode(val));
			str.append(" ");
		}
		if(str.length()>0)
		{
			str.delete(str.length()-1, str.length());
		}
		short length = (short) str.length();
		// 包头,整个包的长度,
		byte[] lbt = ConverterUtil.ShortToBytes2(length);
		byte[] body = str.toString().getBytes(Charset.forName("utf-8"));
		byte[] msg = new byte[lbt.length + body.length];
		// union
		System.arraycopy(lbt, 0, msg, 0, lbt.length);
		System.arraycopy(body, 0, msg, lbt.length, body.length);
		return msg;
	}

	/**
	 * 
	 * 字节转消息体
	 * 
	 * @param bts
	 * @return
	 * @throws Exception
	 */
	public static MsgBase getMsg(byte[] src) throws Exception {
		byte[] head = new byte[2];
		byte[] body = new byte[src.length - head.length];
		System.arraycopy(src, 0, head, 0, head.length);
		System.arraycopy(src, head.length, body, 0, src.length - head.length);
		//short length = bytesToShort2(head, 0);
		return getMsg(head, body);
	}
	/**
	 *  字节转Msg
	 * @param head
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static MsgBase getMsg(byte[] head,byte[] body) throws MyException
	{
		String bodyStr = new String(body, Charset.forName("utf-8"));
		log.info(bodyStr);
		String[] fileds = bodyStr.split(" ");
		Pattern reg=Pattern.compile("^\\d{1,10}$");  
		int msgType = -999999;
		if(reg.matcher(fileds[0]).matches())
		{
			msgType=Integer.parseInt(fileds[0]);
		}
		if(msgType==-999999)
		{
			log.info("消息类型{}无效...",fileds[0]);
			throw new MyException("消息类型无效:" + msgType);
			//return null;
		}
		MsgBase mb = MsgFactory(msgType);
		if (mb == null) {
			throw new MyException("未知协议:" + msgType);
		}
		// 按顺序设置值
		LinkedHashMap<String, String> kv = mb.getMsgStructs();
		Set<String> key = kv.keySet();
		if (key.size() != fileds.length) {
			throw new MyException("字段数目不匹配...");
		}
		//此处需要解码
		int i = 0;
		for (String k : key) {
			kv.put(k, URLDecoder.decode(fileds[i]));
			i++;
		}
		return mb;
	}

	/**
	 * 根据消息类别生成消息实体
	 * @param msgType
	 * @return
	 */
	public static MsgBase MsgFactory(int msgType) {
		switch (msgType) {
		case 100:
			return new C2S_WorkerLogin();
		case 101:
			return new C2S_CustomerPhone();
		case 102:
			return new C2S_CancelOrder();
		case 103:
			return new C2S_DoSubmit();
		case 104:
			return new C2S_GetRegnumber();
		case 105:
			return new C2S_AutoCalloutResult();
		case 106:
			return new C2S_HeartBeat();
		case 107:
			return new C2S_ResponseStatistics();
		case 1000:
			return new C2S_CustomerLogin();
		case 1001:
			return new C2S_CustomerOrders();
		case 200:
			return new S2C_WorkerLogin();
		case 201:
			return new S2C_FindPhone();
		case 202:
			return new S2C_FindNoPhone();
		case 203:
			return new S2C_CancelOrderResult();
		case 204:
			return new S2C_DoSubmitResult();
		case 205:
			return new S2C_AutoCallout();
		case 206:
			return new S2C_SetRegnumber();
		case 207:
			return new S2C_RequestStatistics();
		case 208:
			return new S2C_HeartBeat();
		case 2000:
			return new S2C_CustomerLogin();
		case 2001:
			return new S2C_CustomerOrders();
		case 2002:
			return new S2C_EmptyOrder();
		case 2005:
			return new C2S_BackCall();
		default:
			return null;
		}

	}

}
