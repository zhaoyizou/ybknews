package com.visionet.ivr.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.ivr.model.Order;


public interface OrderMapper extends BaseMapper<Order> {
	/**
	 * 根据手机号查询指定时间内的订单
	 * @param phone 手机
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 
	 */
	public Order selectByCustomePhoneBetweenTime(HashMap<String, Object> ags);
	
}