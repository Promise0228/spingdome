package com.service.impl;

import java.util.HashMap;
import java.util.List;

import com.entity.SysClass;

public interface ISysClassService {
	/**
	 * 查询所有系统分类
	 * 
	 * @filename SysClassMapper.java
	 * @author FXY
	 * @date 2018年7月17日 下午3:19:45
	 * @version 1.0 Copyright (C) 2018 祥云科技
	 */
	public List<SysClass> selectSysClass();
	/**
	 * 查询分类列表中，名称是否已存在
	 * @filename SysClassMapper.java
	 * @author FXY
	 * @date 2018年7月18日 上午8:54:23
	 * @version 1.0
	 * Copyright (C) 2018 祥云科技
	 */
	public List<SysClass> findSysClass(HashMap map);
	/**
	 * 添加新的项目
	 * @filename SysClassMapper.java
	 * @author FXY
	 * @date 2018年7月18日 上午9:18:42
	 * @version 1.0
	 * Copyright (C) 2018 祥云科技
	 */
	public Integer addClass(HashMap map);
	/**
	 * 修改项目
	 * @filename SysClassMapper.java
	 * @author FXY
	 * @date 2018年7月19日 上午9:59:53
	 * @version 1.0
	 * Copyright (C) 2018 祥云科技
	 */
	public Integer updateSysClass(SysClass sysClass);
}
