package com.visionet.core.lbs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.util.Constants;
import com.visionet.core.util.spring.SpringUtils;
import com.visionet.domain.CityBasePoint;
import com.visionet.platform.cooperation.mapper.CityBasePointMapper;

public class LBSUtils {
	private static final Logger log = LoggerFactory.getLogger(LBSUtils.class);

	// 基准纬度
	// public static double baseLat = 30.690065;
	// 基准经度
	// public static double baseLon = 120.85013;
	// 纬度差值 实际距离相差500米
	public static double unitLat = 0.004496;
	// 经度差值 实际距离相差500米
	public static double unitLon = 0.005247;

	private final static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 获取GPS在LBS上的位置
	 * 
	 * @param lat
	 *            纬度
	 * @param lon
	 *            经度
	 * @return x,y
	 */
	public static int[] gpsInLbs(Integer cityId, double lat, double lon) {
		CityBasePoint cityBasePoint = getCityBasePoint(cityId);
		if (cityBasePoint == null) {
			log.info("========城市：" + cityId + "，无基础GPS坐标点");
			return null;
		}
		String baseLat = cityBasePoint.getBaseLat();
		String baseLon = cityBasePoint.getBaseLon();
		// 坐标差值
		double spaceLat = lat - Double.valueOf(baseLat);
		double spaceLon = lon - Double.valueOf(baseLon);
		int x = (int) (spaceLat / unitLat);// 实际纬度相比基准点 相差实际距离 x*500 米
		int y = (int) (spaceLon / unitLon);// 实际经度相比基准点 相差实际距离 x*500 米
		if (x > 300) {
			x = 300;
		}
		if (y > 300) {
			y = 300;
		}
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		return new int[] { x, y };
	}

	/** return unit m */
	public static long GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		long m = (long) (s * 1000);
		return m;
	}

	/**
	 * 查询城市基准坐标
	 *
	 * @param cityId
	 * @return
	 */
	public static CityBasePoint getCityBasePoint(Integer cityId) {
		CityBasePoint cityBasePoint;
		Object objectData = RedisUtil.getObjectData(Constants.CITY_BASE_POINT_KEY + cityId);
		if (objectData == null) {
			CityBasePointMapper cityBasePointMapper = SpringUtils.getBean(CityBasePointMapper.class);
			cityBasePoint = cityBasePointMapper.selectByCityId(cityId);
			if (cityBasePoint == null) {
				return null;
			}
			RedisUtil.setObjectData(Constants.CITY_BASE_POINT_KEY + cityBasePoint.getCityId(), cityBasePoint, null);
		} else {
			cityBasePoint = (CityBasePoint) objectData;
		}
		return cityBasePoint;
	}

	/**
	 * 按半径寻找车辆
	 * 
	 * @param cityId
	 * @param lat
	 * @param lon
	 * @param businessType
	 * @param radius
	 * @return
	 */
	public static List<CarLbsDto> findCarByRadius(Integer cityId, double lat, double lon, String businessType,
			long radius) {
		int[] xy = gpsInLbs(cityId, lat, lon);
		if (xy == null) {
			return Lists.newArrayList();
		}
		int i = 0;
		if (radius > 3000) {
			radius = 3000;
		}
		if (radius % 500 == 0) {
			i = (int) (radius / 500);
		} else {
			i = (int) (radius / 500) + 1;
		}
		int x = xy[0];
		int y = xy[1];
		List<CarLbsDto> carList = new ArrayList<CarLbsDto>();

		for (int j = x - i; j <= x + i; j++) {
			// 超出范围
			if (j < 0 || j > 300) {
				continue;
			}
			for (int k = y - i; k <= y + i; k++) {
				if (k < 0 || k > 300) {
					continue;
				}
				List<Object> os = RedisUtil
						.getObjectListData(cityId + "_" + "carsLBS-" + businessType + "-" + j + "," + k);
				if (os == null || os.size() == 0) {
					continue;
				}
				for (Object object : os) {
					if (object instanceof CarLbsDto) {
						CarLbsDto carLbsDto = (CarLbsDto) object;
						String coor = RedisUtil.getData(cityId + "_" + "carCoor-" + carLbsDto.getPhone());

						if ((coor == null || "".equals(coor)) || (!coor.equals(j + "," + k))) {
							RedisUtil.lrem(cityId + "_" + "carsLBS-" + businessType + "-" + j + "," + k, carLbsDto);
						} else if (businessType != null && !"".equals(businessType)) {

							if (businessType.equals(carLbsDto.getBusinessType())) {
								Integer cityId1 = carLbsDto.getCityId();
								if (cityId1 != null && cityId.intValue() == cityId1.intValue()) {

									if (carLbsDto.getIsWork() == 1) {

										if (carLbsDto.getOnCallType() != null
												&& !"".equals(carLbsDto.getOnCallType())) {

											if (carLbsDto.getOnCallType() == 1) {

												if (Integer.valueOf(businessType).intValue() == 1) {

													long dis = GetDistance(carLbsDto.getLat(), carLbsDto.getLon(), lat,
															lon);
													if (dis <= radius) {
														List<String> carTypeList = new ArrayList<String>();
														
														carList.add(carLbsDto);
														
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return carList;
	}
}
