package com.visionet.platform.cooperation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visionet.core.exception.BizException;
import com.visionet.core.service.BaseService;
import com.visionet.platform.cooperation.mapper.CarUserMapper2;
import com.visionet.platform.cooperation.model.CarUser;

@Service
public class CarUserService extends BaseService<CarUser>   {
	@Autowired
	private CarUserMapper2 carUserMapper;
	
	/**
	 * 验证司机信息是否存在  不存在则insert
	 * @param carUser
	 */
	public void valiCarUserExist(CarUser carUser){
		CarUser cu = carUserMapper.selectByPrimaryKey(carUser.getId());
		if(cu == null){
			if (!(carUserMapper.insertSelective(carUser) > 0)) {
				throw new BizException("第三方司机信息insert失败");
			}
		}
	}
}
