package com.visionet.platform.cooperation.dto;

public class ReturnBanlanceAccountsDTO {
	private String returnCode;
	private String returnMessage;
	private String collectedPay;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getCollectedPay() {
		return collectedPay;
	}
	public void setCollectedPay(String collectedPay) {
		this.collectedPay = collectedPay;
	}
	@Override
	public String toString() {
		return "ReturnBanlanceAccountsDTO [returnCode=" + returnCode + ", returnMessage=" + returnMessage
				+ ", collectedPay=" + collectedPay + "]";
	}
}
