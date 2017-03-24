package com.visionet.platform.cooperation.mapper;

import java.util.List;
import java.util.Map;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.cooperation.model.PushDes;

public interface PushDesMapper extends BaseMapper<PushDes> {

	List<PushDes> selectByPhoneAndUserType(Map<String, Object> map);
	
	/**
	 * 
	 * 查询（根据手机号查询）
	 * 
	 * @param userType
	 * 
	 **/
	PushDes selectByPhone(java.lang.String phone, String userType);

}