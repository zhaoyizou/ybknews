package com.visionet.ivr.socket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.util.spring.SpringUtils;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.mapper.OrderMapper;
import com.visionet.ivr.model.Order;
import com.visionet.ivr.protocol.MsgBase;
import com.visionet.ivr.protocol.S2C_FindPhone;
import com.visionet.ivr.service.ProtocolService;

public class QueueHandle extends Thread {

	Logger log = LoggerFactory.getLogger(this.getClass());
	private String source, destination;

	public QueueHandle(String source, String destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void run() {
		String msg = "{}";
		
		while (true) {
			// msg=RedisUtil.brpoplpush(source, destination, 0);
			// MsgBase msgbase=JSONObject.parseObject(msg, MsgBase.class);
			MsgBase msgbase = MsgQueue.take();
			switch (msgbase.getMsgId()) {
			case 101:
				ProtocolService orderMapper = SpringUtils.getBean(ProtocolService.class);
				break;
			case 102:
			case 103:
			case 105:
			case 1001:
			default:
				break;
			}

		}
	}
}
