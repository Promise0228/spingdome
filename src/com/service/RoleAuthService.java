package com.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.RoleAuthMapper;
import com.service.impl.IRoleAuthService;
/**
 * 角色权限表
 *@filename RoleAuthService.java
 * @author hm
 * @date 2018年7月7日下午12:13:07
 * @version 1.0
 */
@Service
public class RoleAuthService implements IRoleAuthService {

	@Autowired
	RoleAuthMapper roleAuthMapper;
	
	/**
	 * 
	 * @comment 根据传来的角色ID删除权限ID
	 * @author HM
	 */
	@Override
	public Integer deleRoleAuth(HashMap map) {
		// TODO Auto-generated method stub
		return roleAuthMapper.deleRoleAuth(map);
	}

	/**
	 * 
	 * @comment 根据传进来的角色ID和权限ID新增
	 * @author HM
	 */
	@Override
	public Integer addRoleAuth(HashMap map) {
		// TODO Auto-generated method stub
		return roleAuthMapper.addRoleAuth(map);
	}

}
