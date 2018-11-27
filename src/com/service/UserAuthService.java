package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.UserAuth;
import com.mapper.UserAuthMapper;
import com.service.impl.IUserAuthService;
/**
 * 
 *@filename UserAuthService.java
 * @author hm
 * @date 2018年7月7日下午8:13:03
 * @version 1.0
 */

@Service
public class UserAuthService implements IUserAuthService {

	@Autowired
	UserAuthMapper userAuthMapper;
	
	/**
	 * 根据传进来的用户ID查询用户所有权限
	 *@comment 
	 *@author HM
	 *@resultType List<UserAuth>
	 */
	@Override
	public String selectUserAuth(UserAuth userAuth) {
		// TODO Auto-generated method stub
		return userAuthMapper.selectUserAuth(userAuth);
	}

	/**
	 * 
	 *@comment  根据传来的用户ID删除权限ID
	 *@author HM
	 *@resultType Integer
	 */
	@Override
	public Integer deleUserAuth(HashMap map) {
		// TODO Auto-generated method stub
		return userAuthMapper.deleUserAuth(map);
	}


	/**
	 * 
	 *@comment 根据传进来的用户ID和权限ID新增
	 *@author HM
	 *@resultType Integer
	 */
	@Override
	public Integer addUserAuth(HashMap map) {
		// TODO Auto-generated method stub
		return userAuthMapper.addUserAuth(map);
	}

}
