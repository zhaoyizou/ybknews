package com.visionet.ivr.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.util.UUID;
import com.visionet.core.util.spring.SpringUtils;
import com.visionet.ivr.exception.MsgException;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.mapper.OrderMapper;
import com.visionet.ivr.protocol.MsgBase;
import com.visionet.ivr.service.NewCustomService;

public class IVRClient extends AbsSocketHandle {
//private static OrderMapper orderMapper=SpringUtils.getBean(OrderMapper.class);
	public IVRClient(Socket clt) throws IOException {
		super(clt);
		// TODO Auto-generated constructor stub
	}

	public IVRClient(String host, int port) throws IOException {
		
		super(host, port);
		//this.setDaemon(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MsgHandle(MsgBase msg) throws MsgException, MyException {
		log.debug("IVRClient.MsgHandle:" + JSONObject.toJSONString(msg));
		//拦截消息.存到redis 队列中
		switch (msg.getMsgId()) {
			case 101:
			case 102:
			case 103:
			case 105:
			case 1001:
				MsgQueue.offer(msg);//添加到队列
				//RedisUtil.lpush("ivr", JSONObject.toJSONString(msg));
				break;
			default:
				break;
			}		
		/*if(true){
			return;
		}*/
		AbsSocketHandle ash = getClient(OldDispatchServiceSocketClient.class.getName());
		
		try {
			if(ash!=null)
			//if(msg.getMsgId()!=101){
			
				ash.sendMsg(msg);//转发消息,101不发送
				//log.info("转发消息到调度系统:{}",JSONObject.toJSONString(ash));
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
			//断开重连
			if(!ash.RestConnection())
			{
				log.error("与 OldDispatchServiceClientSocket==>{}:{} 断开连接,无法重连",ash.getHostName(),ash.getProt());
			}else{
				log.error("与 OldDispatchServiceClientSocket==>{}:{} 断开连接,无法重连",ash.getHostName(),ash.getProt());
			}
			
		}
		/*try {
			MsgBase mb = null;
			switch (msg.getMsgId()) {
			case 101:// C2S_CUSTOMER_PHONE (来电话，请求得到客户以前的信息)
				// 老系统数据导入到新系统后,直接从数据库中查,有就直接返回,不往老系统中查了.
				HashMap<String, Object> map=new HashMap<>();
				map.put("phone", msg.get("电话号码"));
				map.put("start", new Date(new Date().getTime()-1000*60*30));
				orderMapper.selectByCustomePhoneBetweenTime(map);
				break;
			case 102:// C2S_CANCEL_ORDER (取消订单)
				// 新老系统都发送消息
				ash.sendMsg(msg);//转发给老系统
				//新系统不管
				break;
			case 103:// C2S_DO_SUBMIT(发送订单到服务器)
				
				break;
			case 105:// C2S_AUTO_CALLOUT_RESULT(自动外呼结果)

				break;
			case 1001:// C2S_CUSTOMER_ORDERS（查询出乘客的近期订单）
				break;
			default:
				ash.sendMsg(msg);// 直接转发出去.
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	/**
	 * IVR端不能重连,只能等对方请求过来.
	 */
	@Override
	public boolean RestConnection() {
		// TODO Auto-generated method stub
		CloseSocket();//一定要关闭原有连接
		log.info("放弃重连..");
		return false;
	}

}
