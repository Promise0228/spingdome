package com.service.impl;

import java.util.HashMap;
import java.util.List;

import com.entity.Role;
/**
 * 
 *@filename IRoleService.java
 * @author hm
 * @date 2018年7月4日下午2:29:22
 * @version 1.0
 */
public interface IRoleService {
	

	//查询所有角色
	public List<Role> findRoleAll(HashMap map);
	//查询所有角色的条数
	public Integer countRoleAll(HashMap map);
	//根据角色ID查询对应角色信息
	public Role findRoleById(Role role);
	//更新角色信息
	public Integer refushRole(Role role);
	//新增角色
	public Integer addRole(Role role);
	//根据roleCode查询角色表是否有重复的
	public List<Role> findRoleByCode(Role role);
	//禁用/启用角色
	public Integer restart(Role role);
	/**
	 * 
	 * @comment 根据角色id查询当前角色绑定权限
	 * @author HM
	 * @resultType List<Role>
	 */
	public String selectRoleById(Role role);

}
