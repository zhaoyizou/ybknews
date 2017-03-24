package com.visionet.platform.cooperation.service;

import com.visionet.core.exception.BizException;
import com.visionet.core.lbs.CarLbsDto;
import com.visionet.core.lbs.LBSUtils;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.util.DateUtil;
import com.visionet.core.util.ValidateUtils;
import com.visionet.platform.cooperation.dto.CarLocationDTO;
import com.visionet.platform.cooperation.dto.CarLocationListDTO;
import com.visionet.platform.cooperation.mapper.CarUserMapper2;
import com.visionet.platform.cooperation.mapper.OrderMapper2;
import com.visionet.platform.cooperation.model.CarUser;
import com.visionet.platform.cooperation.model.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.visionet.core.util.Constants.SQORDER_DZCAR_;

@Service
public class CarGpsService {
	@Autowired
	private CarUserMapper2 carUserMapper;
	@Autowired
	private OrderMapper2 orderMapper;

	/**
	 * 下单前 – 查看附近司机位置
	 */
	public List<CarLocationListDTO> nearByCarLocationList(int businessType, double longitude, double latitude,
			int radius, int cityId, String sign, String channel) throws BizException {

		checkInput(businessType, longitude, latitude, radius, cityId);

		List<CarLocationListDTO> retList = new ArrayList<CarLocationListDTO>();

		List<CarLbsDto> list = LBSUtils.findCarByRadius(cityId, latitude, longitude, String.valueOf(businessType),
				radius);
		for (CarLbsDto carLbsDto : list) {
			CarUser carUser = carUserMapper.selectByPrimaryKey(carLbsDto.getCid());
			if (carUser == null) {
				continue;
			}
			CarLocationListDTO clld = new CarLocationListDTO();
			clld.setDriverId(carUser.getId());
			clld.setLongitude(carLbsDto.getLon());
			clld.setLatitude(carLbsDto.getLat());
			clld.setDriverName(carUser.getName());
			clld.setVehicleLicense(carLbsDto.getCarNum());
			clld.setDriverPhone(carLbsDto.getPhone());
			clld.setCarModel(String.valueOf(carLbsDto.getCarType()));
			double grade = carUser.getGrade();
			int driverRate = (int) grade;
			clld.setDriverRate(driverRate);
			clld.setDriverPhoto(carUser.getHeadPic());

			clld.setOrientation(carLbsDto.getOrientation());
			clld.setAltitude(carLbsDto.getAltitude());
			clld.setSpeed(carLbsDto.getSpeed());

			Integer isWorkValue = carLbsDto.getIsWork();
			Integer returnStatus = 4;
			if (isWorkValue.intValue() == 0) {// 为出车
				returnStatus = 4;// 运营状态(1:载客2:接单3:空驶4:停运)
			} else if (isWorkValue.intValue() == 1) {// 出车
				returnStatus = 3;
			} else if (isWorkValue.intValue() == 2) {// 看单
				returnStatus = 3;
			} else if (isWorkValue.intValue() == 3) {// 抢单
				returnStatus = 2;
			} else if (isWorkValue.intValue() == 4) {// 行驶
				returnStatus = 1;
			}
			clld.setHeartbeatTime(DateUtil.returnString(new Date()));
			clld.setZipCode(carLbsDto.getZipCode());
			clld.setStatus(String.valueOf(returnStatus));

			retList.add(clld);
		}

		return retList;
	}

	/**
	 * 验证参数
	 *
	 * @param businessType
	 * @param longitude
	 * @param latitude
	 * @param radius
	 * @param cityId
	 */
	private void checkInput(int businessType, double longitude, double latitude, int radius, int cityId) {

		if (ValidateUtils.checkIsDouble(String.valueOf(businessType))) {
			if (businessType != 1) {
				throw new BizException("businessType参数非专车参数");
			}
		} else {
			throw new BizException("businessType参数非int类型");
		}

		if (!ValidateUtils.checkIsDouble(String.valueOf(longitude))) {
			throw new BizException("经度参数非double类型");
		}

		if (!ValidateUtils.checkIsDouble(String.valueOf(latitude))) {
			throw new BizException("纬度参数非double类型");
		}

		if (ValidateUtils.checkIsDouble(String.valueOf(radius))) {
			if (businessType <= 0 || businessType > 150000) {
				throw new BizException("范围半径参数应在1m和150000m以内");
			}
		} else {
			throw new BizException("范围半径参数非int类型");
		}
	}

	/**
	 * 
	 * 
	 * Query driver in real time position
	 * 
	 * @param orderNo
	 * @param partnerOrderNo
	 * @param sign
	 * @param channel
	 * @return
	 */
	public CarLocationDTO getCarLocation(String orderNo, String partnerOrderNo, String sign, String channel) {
		String carId2 = RedisUtil.getData(SQORDER_DZCAR_ + orderNo);
		if (StringUtils.isBlank(carId2)) {
			throw new BizException("该订单没有司机接单");
		}

		if (StringUtils.isBlank(orderNo)) {
			throw new BizException("大众出行订单号不能为空");
		}
		if (StringUtils.isBlank(partnerOrderNo)) {
			throw new BizException("合作方订单号不能为空");
		}
		Order order = orderMapper.selectByPrimaryKey(orderNo);
		if (order == null) {
			throw new BizException("根据大众出行订单号查询不到该订单信息");
		}
		Integer carId = order.getCarId();
		if (carId == null || carId == 0) {
			throw new BizException("该订单没有司机接单");
		}
		CarUser carUser = carUserMapper.selectByPrimaryKey(carId);
		if (carUser == null) {
			throw new BizException("司机信息异常");
		} else {
			if (!carUser.getPhone().equals(order.getCarUserPhone())) {
				throw new BizException("司机信息异常");
			}
		}
		Integer status = order.getStatus();
		if (status == null) {
			throw new BizException("订单信息异常,获取不到司机实时位置");
		} else if (status == 3) {
			throw new BizException("该订单已取消,获取不到司机实时位置");
		} else if (status == 2) {
			throw new BizException("该订单已完成,获取不到司机实时位置");
		}

		Integer cityId = order.getCityId();
		if (cityId == null || cityId == 0) {
			throw new BizException("订单城市Id信息异常");
		}

		CarLocationDTO retData = new CarLocationDTO();

		Object data = RedisUtil.getObjectData(cityId + "_carCoor-" + carUser.getPhone() + "-object");
		if (data != null) {
			if (data instanceof CarLbsDto) {
				CarLbsDto cld = (CarLbsDto) data;
				retData.setDriverId(order.getCarId());
				retData.setOrderStatus(order.getStatus());
				retData.setLongitude(cld.getLon());
				retData.setLatitude(cld.getLat());
				retData.setDriverName(carUser.getName());
				retData.setVehicleLicense(carUser.getCarNumber());
				retData.setDriverPhone(order.getCarUserPhone());
				retData.setCarModel(String.valueOf(carUser.getCarType()));
				double grade = carUser.getGrade();
				int driverRate = (int) grade;
				retData.setDriverRate(driverRate);
				retData.setDriverPhoto(carUser.getHeadPic());
				retData.setOrientation(cld.getOrientation());
				retData.setAltitude(cld.getAltitude());
				retData.setSpeed(cld.getSpeed());
				Integer isWorkValue = cld.getIsWork();
				Integer returnStatus = 4;
				if (isWorkValue.intValue() == 0) {// 为出车
					returnStatus = 4;// 运营状态(1:载客2:接单3:空驶4:停运)
				} else if (isWorkValue.intValue() == 1) {// 出车
					returnStatus = 3;
				} else if (isWorkValue.intValue() == 2) {// 看单
					returnStatus = 3;
				} else if (isWorkValue.intValue() == 3) {// 抢单
					returnStatus = 2;
				} else if (isWorkValue.intValue() == 4) {// 行驶
					returnStatus = 1;
				}
				retData.setStatus(String.valueOf(returnStatus));
				retData.setHeartbeatTime(DateUtil.returnString(new Date()));
				retData.setZipCode(cld.getZipCode());
			}
		}
		return retData;
	}

}
