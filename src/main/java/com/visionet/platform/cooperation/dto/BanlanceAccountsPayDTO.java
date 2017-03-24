package com.visionet.platform.cooperation.dto;

public class BanlanceAccountsPayDTO {
	private String collectedPay;

	@Override
	public String toString() {
		return "BanlanceAccountsPayDTO [collectedPay=" + collectedPay + "]";
	}

	public String getCollectedPay() {
		return collectedPay;
	}

	public void setCollectedPay(String collectedPay) {
		this.collectedPay = collectedPay;
	}



}
