package com.visionet.platform.cooperation.dto;

import java.util.List;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class PeekFareIncreaseDTO {

    private List<PeekFareIncreaseDayDTO> peekFareIncreaseDays;// 必填 每天的高峰加价实体，参见2.1.4

    public List<PeekFareIncreaseDayDTO> getPeekFareIncreaseDays() {
        return peekFareIncreaseDays;
    }

    public void setPeekFareIncreaseDays(List<PeekFareIncreaseDayDTO> peekFareIncreaseDays) {
        this.peekFareIncreaseDays = peekFareIncreaseDays;
    }

}
