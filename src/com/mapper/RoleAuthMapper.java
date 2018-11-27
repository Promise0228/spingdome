package com.mapper;

import java.util.HashMap;

/**
 * 角色权限表
 *@filename RoleAuthMapper.java
 * @author hm
 * @date 2018年7月7日上午11:55:32
 * @version 1.0
 */
public interface RoleAuthMapper {

	/**
	 * 
	 *@comment  根据传来的角色ID删除权限ID
	 *@author HM
	 *@resultType Integer
	 */
	public Integer deleRoleAuth(HashMap map);
	
	/**
	 * 
	 *@comment 根据传进来的角色ID和权限ID新增
	 *@author HM
	 *@resultType Integer
	 */
	public Integer addRoleAuth(HashMap map);
	
}
