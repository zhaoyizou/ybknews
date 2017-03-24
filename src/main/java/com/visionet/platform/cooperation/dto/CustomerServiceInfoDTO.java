package com.visionet.platform.cooperation.dto;

public class CustomerServiceInfoDTO {

	// 客服编号
	private Integer opId;
	// 客服姓名
	private String opName;
	// 取消原因
	private String cancelReason;
	
	public Integer getOpId() {
		return opId;
	}
	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
}
