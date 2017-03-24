package com.visionet.ivr.socket;

import java.io.IOException;
import java.net.Socket;

import com.alibaba.fastjson.JSONObject;
import com.visionet.core.redis.RedisUtil;
import com.visionet.ivr.protocol.MsgBase;

/**
 * 老客服调度系统
 * 
 * @author zph
 *
 */
public class OldDispatchServiceSocketClient extends AbsSocketHandle {

	public OldDispatchServiceSocketClient(Socket clt) throws IOException {
		super(clt);
		// TODO Auto-generated constructor stub
	}

	public OldDispatchServiceSocketClient(String host, int port) throws IOException {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MsgHandle(MsgBase msg) {
		// TODO Auto-generated method stub
		log.debug("OldDispatchServiceClientSocket.MsgHandle:" + JSONObject.toJSONString(msg));
		// 直接转发给IVRClient 就行
		AbsSocketHandle ash = getClient(IVRClient.class.getName());
		
		try {
			/*//如果两个都给回复?都有以哪个为准,最后一次订单 ?,我要等两个都回复之后才能做出判断
			switch (msg.getMsgId()) {

			case 201:// S2C_FIND_PHONE (得到客户以前的信息)
				//这个消息应该不会再有了.
				break;
			case 202:// S2C_FIND_NO_PHONE (未找到客户以前的信息)
				//是否到新系统中去找一遍?/这个消息应该不会再有了.
				break;
			case 203:// S2C_CANCEL_ORDER_RESULT (取消订单结果)
				break;
			case 204:// S2C_DO_SUBMIT_RESULT(提交订单结果)
				break;
			case 205:// S2C_AUTO_CALLOUT(自动外呼)
				break;
			case 2000:// S2C_CUSTOMER_LOGIN(乘客验证结果)
				break;
			case 2001:// S2C_CUSTOMER_ORDERS (返回乘客订单)
				break;
			case 2002:// S2C_EMPTY _ORDER (乘客无订单)
				break;
			default:// 其它信息原封不动转给IVR
				ash.sendMsg(msg);
				break;

			}*/
			
			ash.sendMsg(msg);
			//log.info("转发消息到IVR:{}",);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 有些信息需要拦截转发给新系统

	}

}
