package com.visionet.platform.cooperation.dto;

public class CarLocationDTO {

	private Integer driverId;
	private Integer orderStatus;// 必选 int 订单状态,参考3.2 订单状态对照表
	private Double longitude;// 必选 double 司机位置经度
	private Double latitude;// 必选 double 司机位置纬度
	private String driverName;// 必选 string 司机名称
	private String vehicleLicense;// 必选 string 司机车牌
	private String driverPhone;// 必选 string 司机手机号
	private String carModel;// 必选 string 车型
	private Integer driverRate;// 必选 int 司机评分
	private String driverPhoto;// 必选 string 司机头像URL

	private String orientation;
	private String altitude;
	private String speed;
	private String status;
	private String heartbeatTime;

	private String zipCode;

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
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

	public Integer getDriverRate() {
		return driverRate;
	}

	public void setDriverRate(Integer driverRate) {
		this.driverRate = driverRate;
	}

	public String getDriverPhoto() {
		return driverPhoto;
	}

	public void setDriverPhoto(String driverPhoto) {
		this.driverPhoto = driverPhoto;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(String heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "CarLocationDTO [driverId=" + driverId + ", orderStatus=" + orderStatus + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", driverName=" + driverName + ", vehicleLicense=" + vehicleLicense
				+ ", driverPhone=" + driverPhone + ", carModel=" + carModel + ", driverRate=" + driverRate
				+ ", driverPhoto=" + driverPhoto + ", orientation=" + orientation + ", altitude=" + altitude
				+ ", speed=" + speed + ", status=" + status + ", heartbeatTime=" + heartbeatTime + ", zipCode="
				+ zipCode + "]";
	}

}
