package com.visionet.platform.cooperation.dto;

import java.util.List;

public class FeeInfoDTO {

	// 
	private String actualPayAmount;
	// 
	private String aliPay;
	// 基础价格
	private String basePrice;
	// 优惠金额
	private String couponAmount;
	// 折扣券抵扣金额
	private String couponSettleAmout;
	// 
	private String customerPayPrice;
	// 
	private String decimalsFees;
	// 
	private String designatedDriverFee;
	// 下车时间
	private String endDate;
	// 下车地点
	private String endPlace;
	// 车型名称
	private String groupName;
	// 高峰时长
	private String hotDuration;
	// 高峰时长费用
	private String hotDurationFees;
	// 高峰里程
	private String hotMileage;
	// 高峰里程费用
	private String hotMileageFees;
	// 基础价包含公里(单位,公里)
	private String includeMileage;
	// 基础价包含时长(单位,分钟)
	private String includeMinute;
	// 空驶里程
	private String longDistanceNum;
	// 空驶费
	private String longDistancePrice;
	// 空驶单价(里程)
	private String longPrice;
	// 订单总里程
	private String mileage;
	// 每公里单价
	private String mileagePrice;
	// 订单总时长
	private String min;
	// 夜间时长
	private String nighitDuration;
	// 夜间时长费用
	private String nighitDurationFees;
	// 夜间服务里程
	private String nightDistanceNum;
	// 夜间服务费
	private String nightDistancePrice;
	// 夜间单价(里程)
	private String nightPrice;
	// 夜间服务费(时长)
	private String nightPriceTime;
	// 超里程数
	private String overMilageNum;
	// 里程小计
	private String overMilageNumTotal;
	// 超里程费用
	private String overMilagePrice;
	// 超时长数
	private String overTimeNum;
	// 时长小计
	private String overTimeNumTotal;
	// 超时长费用
	private String overTimePrice;
	// 高峰单价(里程)
	private String peakPrice;
	// 高峰单价(时长)
	private String peakPriceTime;
	// 乘车人姓名
	private String riderName;
	// 订单服务类型
	private String serviceType;
	// 上车时间
	private String startDate;
	// 上车地点
    private String startPlace;
    // 
    private String status;
    // 每分钟单价
    private String timePrice;
    // 订单总额
    private String total;
    // 等待费用
    private String waitingFee;
    // 等待时常
    private String waitingMinutes;
    // 
    private String wxPay;
	// 其他费用
	private List<OtherCostDTO> otherCost;
	
	public String getActualPayAmount() {
		return actualPayAmount;
	}
	public void setActualPayAmount(String actualPayAmount) {
		this.actualPayAmount = actualPayAmount;
	}
	public String getAliPay() {
		return aliPay;
	}
	public void setAliPay(String aliPay) {
		this.aliPay = aliPay;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getCouponSettleAmout() {
		return couponSettleAmout;
	}
	public void setCouponSettleAmout(String couponSettleAmout) {
		this.couponSettleAmout = couponSettleAmout;
	}
	public String getCustomerPayPrice() {
		return customerPayPrice;
	}
	public void setCustomerPayPrice(String customerPayPrice) {
		this.customerPayPrice = customerPayPrice;
	}
	public String getDecimalsFees() {
		return decimalsFees;
	}
	public void setDecimalsFees(String decimalsFees) {
		this.decimalsFees = decimalsFees;
	}
	public String getDesignatedDriverFee() {
		return designatedDriverFee;
	}
	public void setDesignatedDriverFee(String designatedDriverFee) {
		this.designatedDriverFee = designatedDriverFee;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getHotDuration() {
		return hotDuration;
	}
	public void setHotDuration(String hotDuration) {
		this.hotDuration = hotDuration;
	}
	public String getHotDurationFees() {
		return hotDurationFees;
	}
	public void setHotDurationFees(String hotDurationFees) {
		this.hotDurationFees = hotDurationFees;
	}
	public String getHotMileage() {
		return hotMileage;
	}
	public void setHotMileage(String hotMileage) {
		this.hotMileage = hotMileage;
	}
	public String getHotMileageFees() {
		return hotMileageFees;
	}
	public void setHotMileageFees(String hotMileageFees) {
		this.hotMileageFees = hotMileageFees;
	}
	public String getIncludeMileage() {
		return includeMileage;
	}
	public void setIncludeMileage(String includeMileage) {
		this.includeMileage = includeMileage;
	}
	public String getIncludeMinute() {
		return includeMinute;
	}
	public void setIncludeMinute(String includeMinute) {
		this.includeMinute = includeMinute;
	}
	public String getLongDistanceNum() {
		return longDistanceNum;
	}
	public void setLongDistanceNum(String longDistanceNum) {
		this.longDistanceNum = longDistanceNum;
	}
	public String getLongDistancePrice() {
		return longDistancePrice;
	}
	public void setLongDistancePrice(String longDistancePrice) {
		this.longDistancePrice = longDistancePrice;
	}
	public String getLongPrice() {
		return longPrice;
	}
	public void setLongPrice(String longPrice) {
		this.longPrice = longPrice;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getMileagePrice() {
		return mileagePrice;
	}
	public void setMileagePrice(String mileagePrice) {
		this.mileagePrice = mileagePrice;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getNighitDuration() {
		return nighitDuration;
	}
	public void setNighitDuration(String nighitDuration) {
		this.nighitDuration = nighitDuration;
	}
	public String getNighitDurationFees() {
		return nighitDurationFees;
	}
	public void setNighitDurationFees(String nighitDurationFees) {
		this.nighitDurationFees = nighitDurationFees;
	}
	public String getNightDistanceNum() {
		return nightDistanceNum;
	}
	public void setNightDistanceNum(String nightDistanceNum) {
		this.nightDistanceNum = nightDistanceNum;
	}
	public String getNightDistancePrice() {
		return nightDistancePrice;
	}
	public void setNightDistancePrice(String nightDistancePrice) {
		this.nightDistancePrice = nightDistancePrice;
	}
	public String getNightPrice() {
		return nightPrice;
	}
	public void setNightPrice(String nightPrice) {
		this.nightPrice = nightPrice;
	}
	public String getNightPriceTime() {
		return nightPriceTime;
	}
	public void setNightPriceTime(String nightPriceTime) {
		this.nightPriceTime = nightPriceTime;
	}
	public String getOverMilageNum() {
		return overMilageNum;
	}
	public void setOverMilageNum(String overMilageNum) {
		this.overMilageNum = overMilageNum;
	}
	public String getOverMilageNumTotal() {
		return overMilageNumTotal;
	}
	public void setOverMilageNumTotal(String overMilageNumTotal) {
		this.overMilageNumTotal = overMilageNumTotal;
	}
	public String getOverMilagePrice() {
		return overMilagePrice;
	}
	public void setOverMilagePrice(String overMilagePrice) {
		this.overMilagePrice = overMilagePrice;
	}
	public String getOverTimeNum() {
		return overTimeNum;
	}
	public void setOverTimeNum(String overTimeNum) {
		this.overTimeNum = overTimeNum;
	}
	public String getOverTimeNumTotal() {
		return overTimeNumTotal;
	}
	public void setOverTimeNumTotal(String overTimeNumTotal) {
		this.overTimeNumTotal = overTimeNumTotal;
	}
	public String getOverTimePrice() {
		return overTimePrice;
	}
	public void setOverTimePrice(String overTimePrice) {
		this.overTimePrice = overTimePrice;
	}
	public String getPeakPrice() {
		return peakPrice;
	}
	public void setPeakPrice(String peakPrice) {
		this.peakPrice = peakPrice;
	}
	public String getPeakPriceTime() {
		return peakPriceTime;
	}
	public void setPeakPriceTime(String peakPriceTime) {
		this.peakPriceTime = peakPriceTime;
	}
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimePrice() {
		return timePrice;
	}
	public void setTimePrice(String timePrice) {
		this.timePrice = timePrice;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getWaitingFee() {
		return waitingFee;
	}
	public void setWaitingFee(String waitingFee) {
		this.waitingFee = waitingFee;
	}
	public String getWaitingMinutes() {
		return waitingMinutes;
	}
	public void setWaitingMinutes(String waitingMinutes) {
		this.waitingMinutes = waitingMinutes;
	}
	public String getWxPay() {
		return wxPay;
	}
	public void setWxPay(String wxPay) {
		this.wxPay = wxPay;
	}
	public List<OtherCostDTO> getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(List<OtherCostDTO> otherCost) {
		this.otherCost = otherCost;
	}
}
