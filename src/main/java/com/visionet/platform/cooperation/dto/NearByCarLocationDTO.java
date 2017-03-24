package com.visionet.platform.cooperation.dto;

public class NearByCarLocationDTO {
	private int businessType;// 必填 int 大众出行给定，1为专车
	private double longitude;// 必填 double 经度
	private double latitude;// 必填 double 纬度
	private int radius;// 必填 int 范围半径单位m，最大半径150000m
	private int cityId;// 必填 int 城市ID

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "NearByCarLocationDTO [businessType=" + businessType + ", longitude=" + longitude + ", latitude="
				+ latitude + ", radius=" + radius + ", cityId=" + cityId + "]";
	}

}
