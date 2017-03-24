package com.visionet.platform.cooperation.dto;

import java.math.BigDecimal;

public class ChargeInfoDTO {

	// 用户线下付款金额，单位（分）
	private BigDecimal offlinePayAmount;

	public BigDecimal getOfflinePayAmount() {
		return offlinePayAmount;
	}

	public void setOfflinePayAmount(BigDecimal offlinePayAmount) {
		this.offlinePayAmount = offlinePayAmount;
	}
}
