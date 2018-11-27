package com.service.impl;

import java.util.HashMap;
import java.util.List;

import com.entity.Role;
import com.entity.UserRole;
/**
 * 
 *@filename IRoleService.java
 * @author hm
 * @date 2018年7月3日下午4:03:32
 * @version 1.0
 */
public interface IUserRoleService {

	/**
	 * 
	 *@返回类型：List<Role>
	 *@方法功能：查询全部角色roleName
	 */
	public List<Role> selectRoleName();
	
	/**
	 * 
	 *@返回类型：List<Role>
	 *@方法功能：根据前台传的ID查询用户角色
	 */
	public String selectRoleNameById(UserRole userRole);
	
	/**
	 * 
	 *@返回类型：void
	 *@方法功能：根据前台传来的用户ID删除角色ID
	 */
	public int deleUserRoleId(HashMap map);
	
	/**
	 * 
	 *@返回类型：int
	 *@方法功能：根据后台传来的用户ID给角色ID重新赋值
	 */
	public int addUserRoleId(HashMap map);
}
