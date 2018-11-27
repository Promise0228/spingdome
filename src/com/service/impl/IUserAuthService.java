package com.service.impl;

import java.util.HashMap;
import java.util.List;

import com.entity.UserAuth;

public interface IUserAuthService {

	/**
	 * 根据传进来的用户ID查询用户所有权限
	 *@comment 
	 *@author HM
	 *@resultType List<UserAuth>
	 */
	public String selectUserAuth(UserAuth userAuth);
	
	/**
	 * 
	 *@comment  根据传来的用户ID删除权限ID
	 *@author HM
	 *@resultType Integer
	 */
	public Integer deleUserAuth(HashMap map);
	
	/**
	 * 
	 *@comment 根据传进来的用户ID和权限ID新增
	 *@author HM
	 *@resultType Integer
	 */
	public Integer addUserAuth(HashMap map);
}
