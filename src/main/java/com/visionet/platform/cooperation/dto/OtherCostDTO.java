package com.visionet.platform.cooperation.dto;

import java.math.BigDecimal;

public class OtherCostDTO {

	// 费用名称
	private String typeName;
	// 费用(单位,元)
	private BigDecimal cost;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
