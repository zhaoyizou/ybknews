package com.visionet.platform.cooperation.dto;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class PeekDTO {

    private String from;// 必填 string 加价开始时间
    private String to;// 必填 string 加价结束时间
    private String rate;// 必填 string 当前时间段加价比例
    private String des;// 必填 string 高低峰描述

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
