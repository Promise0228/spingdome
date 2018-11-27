package com.service.impl;

import java.util.HashMap;

import com.entity.GroupRole;
/**
 * 组角色类
 *@filename IGroupRole.java
 * @author hm
 * @date 2018年7月10日下午8:18:20
 * @version 1.0
 */
public interface IGroupRoleService {

	/**
	 * 
	 *@comment 根据前端传来的组ID查询角色ID,返回字符串
	 *@author HM
	 *@resultType 
	 */
	public String selectGroupRoleById(GroupRole groupRole);

	/**
	 * 
	 *@返回类型：void
	 *@方法功能：根据前台传来的用户ID删除用户角色ID
	 */
	public int deleGroupRoleId(HashMap map);
	
	/**
	 * 
	 *@返回类型：int
	 *@方法功能：根据后台传来的用户ID给用户角色ID重新赋值
	 */
	public int addGroupRoleId(HashMap map);

}
