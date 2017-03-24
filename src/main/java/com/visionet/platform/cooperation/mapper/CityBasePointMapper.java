package com.visionet.platform.cooperation.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.domain.CityBasePoint;

public interface CityBasePointMapper extends BaseMapper<CityBasePoint> {
    CityBasePoint selectByCityId(Integer cityId);
}