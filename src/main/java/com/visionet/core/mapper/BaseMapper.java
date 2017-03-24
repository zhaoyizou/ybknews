package com.visionet.core.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper<M> extends Mapper<M>,MySqlMapper<M> {



}
