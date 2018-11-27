package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.SysClass;
import com.mapper.SysClassMapper;
import com.service.impl.ISysClassService;

@Service
public class SysClassService implements ISysClassService {

	@Autowired
	SysClassMapper sysClassMapper;

	/**
	 * 查询所有系统分类
	 */
	@Override
	public List<SysClass> selectSysClass() {
		// TODO Auto-generated method stub
		return sysClassMapper.selectSysClass();
	}

	/**
	 * 查询分类列表中，名称是否已存在
	 */
	@Override
	public List<SysClass> findSysClass(HashMap map) {
		// TODO Auto-generated method stub
		return sysClassMapper.findSysClass(map);
	}

	/**
	 * 添加新的项目
	 */
	@Override
	public Integer addClass(HashMap map) {
		// TODO Auto-generated method stub
		return sysClassMapper.addClass(map);
	}

	@Override
	public Integer updateSysClass(SysClass sysClass) {
		// TODO Auto-generated method stub
		return sysClassMapper.updateSysClass(sysClass);
	}

}
