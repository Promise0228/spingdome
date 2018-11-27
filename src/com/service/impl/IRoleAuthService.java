package com.service.impl;

import java.util.HashMap;
/**
 * 角色权限表
 *@filename IRoleAuthService.java
 * @author hm
 * @date 2018年7月7日下午12:09:46
 * @version 1.0
 */
public interface IRoleAuthService {

	
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
