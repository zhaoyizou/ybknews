package com.visionet.platform.cooperation.dto;

public class CarLocationListDTO {

	private int driverId;
	private double longitude;// 必选 double 司机位置经度
	private double latitude;// 必选 double 司机位置纬度
	private String driverName;// 必选 string 司机名称
	private String vehicleLicense;// 必选 string 司机车牌
	private String driverPhone;// 必选 string 司机手机号
	private String carModel;// 必选 string 车型
	private int driverRate;// 必选 int 司机评分
	private String driverPhoto;// 必选 string 司机头像URL
	private String orientation;
	private String altitude;
	private String speed;
	private String heartbeatTime;
	private String status;
	private String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(String heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CarLocationListDTO [driverId=" + driverId + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", driverName=" + driverName + ", vehicleLicense=" + vehicleLicense + ", driverPhone=" + driverPhone
				+ ", carModel=" + carModel + ", driverRate=" + driverRate + ", driverPhoto=" + driverPhoto
				+ ", orientation=" + orientation + ", altitude=" + altitude + ", speed=" + speed + ", heartbeatTime="
				+ heartbeatTime + ", status=" + status + ", zipCode=" + zipCode + "]";
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

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleLicense() {
		return vehicleLicense;
	}

	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getDriverRate() {
		return driverRate;
	}

	public void setDriverRate(int driverRate) {
		this.driverRate = driverRate;
	}

	public String getDriverPhoto() {
		return driverPhoto;
	}

	public void setDriverPhoto(String driverPhoto) {
		this.driverPhoto = driverPhoto;
	}

}
