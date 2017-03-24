package com.visionet.platform.thirdpartydata.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.thirdpartydata.model.ThirdPartyCity;
import org.apache.ibatis.annotations.Param;

public interface ThirdPartyCityMapper extends BaseMapper<ThirdPartyCity> {

    ThirdPartyCity selectByDzcxCityId(@Param("dzcxCityId") Integer dzcxCityId, @Param("merchantId") Integer merchantId);

}