package com.visionet.platform.cooperation.dto;

import java.util.List;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class PeekFareIncreaseDayDTO {

    private String day;// 必填 string 星期一到星期日
    private PeekListDTO peekList;// 必填 每天高峰加价的实体类，参见2.1.5

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public PeekListDTO getPeekList() {
        return peekList;
    }

    public void setPeekList(PeekListDTO peekList) {
        this.peekList = peekList;
    }

}
