package com.visionet.ivr.service;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.visionet.core.util.spring.SpringUtils;
import com.visionet.ivr.exception.MyException;
import com.visionet.ivr.mapper.OrderMapper;
import com.visionet.ivr.model.Order;
import com.visionet.ivr.protocol.C2S_CustomerPhone;
import com.visionet.ivr.protocol.MsgBase;
import com.visionet.ivr.protocol.S2C_FindNoPhone;
import com.visionet.ivr.protocol.S2C_FindPhone;

@Service
public class ProtocolService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 创建101协议的回复信息
	 * @param msgbase
	 * @return
	 */
	public MsgBase CreateC2S_CustomerPhoneRecvMsg(C2S_CustomerPhone msgbase) {
		HashMap<String, Object> map = new HashMap<>();
		MsgBase result=null;
		try {
			map.put("phone", msgbase.get("电话号码"));
			map.put("start", new Date((new Date().getTime() - 30 * 60 * 1000)));
			OrderMapper orderMapper = SpringUtils.getBean(OrderMapper.class);
			Order order = orderMapper.selectByCustomePhoneBetweenTime(map);
			if (order != null) {
				// 组装成订单
				S2C_FindPhone s2c = new S2C_FindPhone();
				s2c.put("Sessionid", msgbase.get("Sessionid"));
				s2c.put("ChannelID", msgbase.get("ChannelID"));
				s2c.put("电话号码", order.getCallbackPhone());
				s2c.put("订单号", order.getOrderId());
				s2c.put("订单状态", null);// 新老系统的订单状态不一致
				s2c.put("车牌号", null);
				s2c.put("车当前位置", null);// 地址还是坐标?
				s2c.put("是否有分机", "0");// ????老系统数据可能有分机
				s2c.put("乘客姓名", order.getCustomerName());
				/*
				 * s2c.put("地标",null); //新系统有问题,((?![;\(\)]).)*[号弄道口]
				 * s2c.put("路",null); s2c.put("弄",null); s2c.put("支弄",null);
				 * s2c.put("门牌号码",null); s2c.put("栋",null);
				 * s2c.put("房间号码",null); s2c.put("临近道路",null);
				 * s2c.put("接客点",null); s2c.put("目的地",null);//老系统目的地是未知的
				 */
				s2c.put("订车次数", "1");// 总的订车次数吗?
				s2c.put("放空次数", "0");// ?
				s2c.put("客户级别", null);// ?应该有个默认级别
				s2c.put("乘客性别", null);// 新系统没有性别
				s2c.put("上次订单时间", null);// 订单时间和用车时间有什么不一样么?
				s2c.put("上次用车时间", null);
				s2c.put("乘客地址经度", null);
				s2c.put("乘客地址纬度", null);
				s2c.put("备注", null);
				s2c.put("订车形式", null);// 应该有默认值
				s2c.put("路程", null);// ?这个不知道什么意思
				s2c.put("重复订车", null);
				result=s2c;
			}else
			{
				S2C_FindNoPhone s2c = new S2C_FindNoPhone();
				s2c.put("Sessionid", msgbase.get("Sessionid"));
				s2c.put("ChannelID", msgbase.get("ChannelID"));
				s2c.put("电话号码",msgbase.get("电话号码"));
				result=s2c;
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return result;
	}
	
	//public void Create
}
