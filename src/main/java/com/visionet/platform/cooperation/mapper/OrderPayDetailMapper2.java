package com.visionet.platform.cooperation.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.cooperation.model.OrderPayDetail;

public interface OrderPayDetailMapper2 extends BaseMapper<OrderPayDetail> {
	
	/**
	 * 查询订单余额支付金额
	 * 
	 * @param orderId
	 * @return
	 * @author 秦朝胜
	 */
	OrderPayDetail selectDetailByOrderId(String orderId);
}