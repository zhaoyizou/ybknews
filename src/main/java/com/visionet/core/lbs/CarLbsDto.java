package com.visionet.core.lbs;

import java.io.Serializable;
import java.util.Date;

/**
 * 车主部分信息
 */
public class CarLbsDto implements Serializable {
	private static final long serialVersionUID = -3416393615366366773L;
	private Integer signType = 0;// 签约类型 1 签约司机  0 非签约
	private Integer cid;// 司机ID
	private Integer cityId;// 车主注册城市地区
	private String city;// 车主注册城市地区
	private String phone;//司机ID
	private String businessType;// 业务类型：0出租车司机，1约租车司机
	private double lat;
	private double lon;
	private String carType;// 车型
	private String userType;// 类型（0大众自营，1加盟，2其他车主）
	private String name;
	private String carNum;// 车牌

	// 0:停止接单，1:正在接单
	private Integer isWork;

	private Integer degradedType;//
	private Integer offBookType;
	private Integer onBookType;
	private Integer onCallType;
	private Date yesWorkStartTime;
	private Date yesWorkEndTime;
	private Date noWorkStartTime;
	private Date noWorkEndTime;

	private String orientation;
	private String speed;
	private String altitude;
	private String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public Integer getIsWork() {
		return isWork;
	}

	public void setIsWork(Integer isWork) {
		this.isWork = isWork;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CarLbsDto [signType=" + signType + ", cid=" + cid + ", cityId=" + cityId + ", city=" + city + ", phone="
				+ phone + ", businessType=" + businessType + ", lat=" + lat + ", lon=" + lon + ", carType=" + carType
				+ ", userType=" + userType + ", name=" + name + ", carNum=" + carNum + ", isWork=" + isWork
				+ ", degradedType=" + degradedType + ", offBookType=" + offBookType + ", onBookType=" + onBookType
				+ ", onCallType=" + onCallType + ", yesWorkStartTime=" + yesWorkStartTime + ", yesWorkEndTime="
				+ yesWorkEndTime + ", noWorkStartTime=" + noWorkStartTime + ", noWorkEndTime=" + noWorkEndTime
				+ ", orientation=" + orientation + ", speed=" + speed + ", altitude=" + altitude + ", zipCode="
				+ zipCode + "]";
	}

	public Integer getDegradedType() {
		return degradedType;
	}

	public void setDegradedType(Integer degradedType) {
		this.degradedType = degradedType;
	}

	public Integer getOffBookType() {
		return offBookType;
	}

	public void setOffBookType(Integer offBookType) {
		this.offBookType = offBookType;
	}

	public Integer getOnBookType() {
		return onBookType;
	}

	public void setOnBookType(Integer onBookType) {
		this.onBookType = onBookType;
	}

	public Integer getOnCallType() {
		return onCallType;
	}

	public void setOnCallType(Integer onCallType) {
		this.onCallType = onCallType;
	}

	public Date getYesWorkStartTime() {
		return yesWorkStartTime;
	}

	public void setYesWorkStartTime(Date yesWorkStartTime) {
		this.yesWorkStartTime = yesWorkStartTime;
	}

	public Date getYesWorkEndTime() {
		return yesWorkEndTime;
	}

	public void setYesWorkEndTime(Date yesWorkEndTime) {
		this.yesWorkEndTime = yesWorkEndTime;
	}

	public Date getNoWorkStartTime() {
		return noWorkStartTime;
	}

	public void setNoWorkStartTime(Date noWorkStartTime) {
		this.noWorkStartTime = noWorkStartTime;
	}

	public Date getNoWorkEndTime() {
		return noWorkEndTime;
	}

	public void setNoWorkEndTime(Date noWorkEndTime) {
		this.noWorkEndTime = noWorkEndTime;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof CarLbsDto)) {
			return false;
		}
		CarLbsDto carLbsDto = (CarLbsDto) obj;
		return phone.equals(carLbsDto.getPhone());
	}

	public Integer getSignType() {
		return signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
