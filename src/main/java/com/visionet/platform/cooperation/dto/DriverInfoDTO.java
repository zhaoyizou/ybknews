package com.visionet.platform.cooperation.dto;

public class DriverInfoDTO {

	// 司机id
	private int driverId;
	// 司机姓名
	private String name;
	// 司机电话
	private String phone;
	// 车辆型号
	private String modelName;
	// 车组型号: 舒适型，商务型
	private String groupName;
	// 车辆颜色
	private String vehicleColor;
	// 车辆图片链接
	private String vehiclePic;
	// 车牌号码
	private String licensePlates;
	// 司机头像链接
	private String photoSrc;
	// 司机星级
	private String driverRate;
	
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public String getVehiclePic() {
		return vehiclePic;
	}
	public void setVehiclePic(String vehiclePic) {
		this.vehiclePic = vehiclePic;
	}
	public String getLicensePlates() {
		return licensePlates;
	}
	public void setLicensePlates(String licensePlates) {
		this.licensePlates = licensePlates;
	}
	public String getPhotoSrc() {
		return photoSrc;
	}
	public void setPhotoSrc(String photoSrc) {
		this.photoSrc = photoSrc;
	}
	public String getDriverRate() {
		return driverRate;
	}
	public void setDriverRate(String driverRate) {
		this.driverRate = driverRate;
	}
}
