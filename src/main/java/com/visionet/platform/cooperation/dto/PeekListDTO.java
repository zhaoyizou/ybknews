package com.visionet.platform.cooperation.dto;

import java.util.List;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class PeekListDTO {

    private List<PeekDTO> peek;// 必填 每天的每个时间段的高峰加价实体类，参见2.1.6

    public List<PeekDTO> getPeek() {
        return peek;
    }

    public void setPeek(List<PeekDTO> peek) {
        this.peek = peek;
    }

}
