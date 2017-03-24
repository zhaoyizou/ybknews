package com.visionet.platform.cooperation.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.cooperation.model.Customer;

public interface CustomerMapper extends BaseMapper<Customer> {
	
	/**
	 * 查询乘客可开票额度
	 *  
	 * @param phone
	 * @return
	 * @author 秦朝胜
	 */
	Customer selectOneByPhone(String phone);
}