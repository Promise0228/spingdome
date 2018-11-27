package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.UserAuth;

/**
 * 用户权限表
 *@filename UserAuthMapper.java
 * @author hm
 * @date 2018年7月7日下午8:02:35
 * @version 1.0
 */
public interface UserAuthMapper {

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
