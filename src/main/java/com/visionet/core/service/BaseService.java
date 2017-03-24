package com.visionet.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.visionet.core.mapper.BaseMapper;


public abstract class BaseService<M> {

	@Autowired
	protected BaseMapper<M> baseMapper;

	public int deleteByPrimaryKey(Object id) {
		if (id != null && !"".equals(id)) {
			return baseMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}

	public int insert(M record) {
		if (record != null && !"".equals(record)) {
			return baseMapper.insert(record);
		}
		return 0;
	}

	public int insertSelective(M record) {
		if (record != null && !"".equals(record)) {
			return baseMapper.insertSelective(record);
		}
		return 0;
	}

	public M selectByPrimaryKey(Object id) {
		if (id != null && !"".equals(id)) {
			return baseMapper.selectByPrimaryKey(id);
		}
		return null;
	}

	public int updateByPrimaryKeySelective(M record) {
		if (record != null && !"".equals(record)) {
			return baseMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	public int updateByPrimaryKey(M record) {
		if (record != null && !"".equals(record)) {
			return baseMapper.updateByPrimaryKey(record);
		}
		return 0;
	}

	public List<M> selectAll() {
		return baseMapper.selectAll();
	}


}
