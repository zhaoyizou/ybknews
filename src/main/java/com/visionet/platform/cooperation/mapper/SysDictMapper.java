package com.visionet.platform.cooperation.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.cooperation.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 根据dictType、cityId查询
     *
     * @param dictType
     * @param cityId
     * @return
     * @author ZagHe 2016/11/23
     */
    List<SysDict> selectByDictTypeAndCityId(@Param("dictType") String dictType,@Param("cityId") Integer cityId);


}