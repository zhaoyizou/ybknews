package com.visionet.platform.cooperation.dto;

public class reassignmentNoticeDTO {
	private String orderNo;//必填	string	大众出行订单号
	private String partnerOrderNo;//必填	string	合作方订单号
	private int driverId;//必填	int	大众出行司机ID
	private String driverName;//必填	string	司机名称
	private String vehicleLicense;//必填	string	司机车牌
	private String driverPhone;//必填	string	司机手机号
	private String carModel;//必填	string	车型
	private int driverRate;//必填	int	司机评分1-5
	private String driverPhoto;//必填	string	司机头像URL
	private String latitude;//必填	string	司机位置纬度
	private String longitude;//必填	string	司机位置经度
	private String sign;//必填	string	参见1.8 sign生成算法
	private String channel;//必填	string	渠道名称，由大众出行给定
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPartnerOrderNo() {
		return partnerOrderNo;
	}
	public void setPartnerOrderNo(String partnerOrderNo) {
		this.partnerOrderNo = partnerOrderNo;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@Override
	public String toString() {
		return "reassignmentNoticeDTO [orderNo=" + orderNo + ", partnerOrderNo=" + partnerOrderNo + ", driverId="
				+ driverId + ", driverName=" + driverName + ", vehicleLicense=" + vehicleLicense + ", driverPhone="
				+ driverPhone + ", carModel=" + carModel + ", driverRate=" + driverRate + ", driverPhoto=" + driverPhoto
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", sign=" + sign + ", channel=" + channel
				+ "]";
	}
	
	
}
