package com.visionet.platform.cooperation.dto;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class CarInfoDTO {

    private String city;// 必填 string 城市名称
    private String type;// 必填 string 车型(舒适型 商务型 豪华型)
    private String carType;// 必填 string
    private String startPrice;// 必填 string 起步价
    private String unitPrice;// 必填 string 每公里单价
    private String minitePrice;// 必填 string 每秒单价
    private String minitePriceNight;// 必填 string 夜间每秒单价
    private String maxMiniteNight;// 必填 string
    private String nightTimeFrom;// 必填 string 夜间出车开始时间
    private String nightTimeTo;// 必填 string 夜间出车结束时间
    private String freeWaitTime;// 必填 string 等待时间
    private String level;// 必填 string

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFreeWaitTime() {
        return freeWaitTime;
    }

    public void setFreeWaitTime(String freeWaitTime) {
        this.freeWaitTime = freeWaitTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMaxMiniteNight() {
        return maxMiniteNight;
    }

    public void setMaxMiniteNight(String maxMiniteNight) {
        this.maxMiniteNight = maxMiniteNight;
    }

    public String getMinitePrice() {
        return minitePrice;
    }

    public void setMinitePrice(String minitePrice) {
        this.minitePrice = minitePrice;
    }

    public String getMinitePriceNight() {
        return minitePriceNight;
    }

    public void setMinitePriceNight(String minitePriceNight) {
        this.minitePriceNight = minitePriceNight;
    }

    public String getNightTimeFrom() {
        return nightTimeFrom;
    }

    public void setNightTimeFrom(String nightTimeFrom) {
        this.nightTimeFrom = nightTimeFrom;
    }

    public String getNightTimeTo() {
        return nightTimeTo;
    }

    public void setNightTimeTo(String nightTimeTo) {
        this.nightTimeTo = nightTimeTo;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

}
