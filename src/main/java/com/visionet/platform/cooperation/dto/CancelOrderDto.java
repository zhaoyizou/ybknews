package com.visionet.platform.cooperation.dto;

public class CancelOrderDto {
	private String orderNo;// 必填 String 大众出行订单号
	private String partnerOrderNo;//必填 String 合作方订单号
	private int cancelType;// 必填 int 1 乘客取消 2司机取消 3 其他
	private String reason;// 必填 String 取消原因
	private String sign;// 必填 String 参见1.8 sign生成算法
	private String channel;// 必填 String 渠道名称，由大众出行给定。
	
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
	public int getCancelType() {
		return cancelType;
	}
	public void setCancelType(int cancelType) {
		this.cancelType = cancelType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	
}
