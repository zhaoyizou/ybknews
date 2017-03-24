package com.visionet.platform.cooperation.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.cooperation.model.CarUser;

public interface CarUserMapper2 extends BaseMapper<CarUser> {
	
	/**
	 * 查询第三方司机信息
	 * 
	 * @param driverId
	 * @param driverSource
	 * @return
	 * @author 秦朝胜
	 */
	CarUser selectByParam(int driverId, int driverSource);
}