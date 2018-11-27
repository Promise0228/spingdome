package com.service.impl;

import java.util.HashMap;
import java.util.List;

import com.entity.UserGroup;
/**
 * 用户组管理
 *@filename IUserGroupService.java
 * @author hm
 * @date 2018年7月9日下午5:43:23
 * @version 1.0
 */
public interface IUserGroupService {

	/**
	 * 查询所有用户组信息
	 *@comment 
	 *@author HM
	 *@resultType List<UserGroup>
	 */
	public List<UserGroup> selectUserGroup();
	
	/**
	 * 根据传进来的组名字和code查询有无重复
	 *@comment 
	 *@author HM
	 *@resultType List<UserGroup>
	 */
	public List<UserGroup> selectUserGroupByCode(UserGroup userGroup);
	
	/**
	 * 新增用户组
	 *@comment 
	 *@author HM
	 *@resultType Integer
	 */
	public Integer addUserGroup(UserGroup userGroup);
	
	/**
	 * 
	 *@comment  查询用户组条数
	 *@author HM
	 *@resultType Integer
	 */
	public Integer countUserGroup(HashMap map);
	
	/**
	 * 
	 *@comment 启用/禁用组
	 *@author HM
	 *@resultType Integer
	 */
	public Integer restart(UserGroup userGroup);
}
