package com.visionet.platform.cooperation.dto;

/**
 * 轮询订单状态返回DTO
 * @author zhouwei
 *
 */
public class PollingResultDTO {
	private String orderStatus;// 必填 string 大众出行订单状态.参考附录：订单状态码
	private String vehicleLicense;// 必填 string 车牌号
	private String carModel;// 必填 string 车型
	private String driverName;// 必填 string 司机姓名
	private String driverPhone;// 必填 string 司机电话
	private String orderNo;// 必填 string 大众出行订单号
	private String startAddr;// 必填 string 上车地点
	private String endAddr;// 必填 string 下车地点
	private String driverId;// 必填 string 大众出行司机ID

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getVehicleLicense() {
		return vehicleLicense;
	}

	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStartAddr() {
		return startAddr;
	}

	public void setStartAddr(String startAddr) {
		this.startAddr = startAddr;
	}

	public String getEndAddr() {
		return endAddr;
	}

	public void setEndAddr(String endAddr) {
		this.endAddr = endAddr;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

}
