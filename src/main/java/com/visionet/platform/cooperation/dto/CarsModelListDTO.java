package com.visionet.platform.cooperation.dto;

import java.util.List;

/**
 * Created by ZagHe on 2016/11/23.
 */
public class CarsModelListDTO {

    private PeekFareIncreaseDTO peekFareIncreases;// 必填 高峰加价实体类，参见2.1.2
    private CarsModelDTO carsModels;// 必填 车型价位实体类，参见2.1.3

    public CarsModelDTO getCarsModels() {
        return carsModels;
    }

    public void setCarsModels(CarsModelDTO carsModels) {
        this.carsModels = carsModels;
    }

    public PeekFareIncreaseDTO getPeekFareIncreases() {
        return peekFareIncreases;
    }

    public void setPeekFareIncreases(PeekFareIncreaseDTO peekFareIncreases) {
        this.peekFareIncreases = peekFareIncreases;
    }

}
