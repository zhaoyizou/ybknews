package com.visionet.platform.cooperation.dto;

public class MetaDTO {

	// 订单号 
	private String orderNo;
	// 合作方订单号
	private String partnerOrderNo;
	// 订单状态
	private String status;
	// status为accepted或reassign时，必传
	private DriverInfoDTO driverInfo;
	// status = completed时必传
	private FeeInfoDTO feeInfo;
	// status = offline_pay时必传
	private ChargeInfoDTO chargeInfo;
	// status = cs_canceled时必传
	private CustomerServiceInfoDTO customerServiceInfo;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DriverInfoDTO getDriverInfo() {
		return driverInfo;
	}
	public void setDriverInfo(DriverInfoDTO driverInfo) {
		this.driverInfo = driverInfo;
	}
	public FeeInfoDTO getFeeInfo() {
		return feeInfo;
	}
	public void setFeeInfo(FeeInfoDTO feeInfo) {
		this.feeInfo = feeInfo;
	}
	public ChargeInfoDTO getChargeInfo() {
		return chargeInfo;
	}
	public void setChargeInfo(ChargeInfoDTO chargeInfo) {
		this.chargeInfo = chargeInfo;
	}
	public CustomerServiceInfoDTO getCustomerServiceInfo() {
		return customerServiceInfo;
	}
	public void setCustomerServiceInfo(CustomerServiceInfoDTO customerServiceInfo) {
		this.customerServiceInfo = customerServiceInfo;
	}
}
