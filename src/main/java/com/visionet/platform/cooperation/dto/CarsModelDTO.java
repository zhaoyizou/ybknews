package com.visionet.platform.cooperation.dto;

import java.util.List;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class CarsModelDTO {

    private List<CarInfoDTO> carInfos;// 必填 车型信息集合的实体，参见2.1.7

    public List<CarInfoDTO> getCarInfos() {
        return carInfos;
    }

    public void setCarInfos(List<CarInfoDTO> carInfos) {
        this.carInfos = carInfos;
    }

}
