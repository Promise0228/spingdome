package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.Role;

/**
 * 角色表
 * 
 * @filename RoleMapper.java
 * @author hm
 * @date 2018年7月4日下午2:27:09
 * @version 1.0
 */
public interface RoleMapper {

	
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
	//根据角色id查询当前角色绑定权限
	public String selectRoleById(Role role);

	
}
