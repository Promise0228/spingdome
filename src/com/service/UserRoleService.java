package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Role;
import com.entity.UserRole;
import com.mapper.UserRoleMapper;
import com.service.impl.IUserRoleService;
/**
 * 
 *@filename RoleService.java
 * @author hm
 * @date 2018年7月3日下午4:03:20
 * @version 1.0
 */
@Service
public class UserRoleService implements IUserRoleService {

	@Autowired
	UserRoleMapper roleMapper;
	
	/**
	 * 
	 * @comment 查询全部角色roleName
	 * @author HM
	 */
	@Override
	public List<Role> selectRoleName() {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleName();
	}

	/**
	 * 
	 * @comment 根据前台传的ID查询用户角色
	 * @author HM
	 */
	@Override
	public String selectRoleNameById(UserRole userRole) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleNameById(userRole);
	}

	/**
	 * 
	 * @comment 根据前台传来的用户ID删除角色ID
	 * @author HM
	 */
	@Override
	public int deleUserRoleId(HashMap map) {
		return roleMapper.deleUserRoleId(map);
		// TODO Auto-generated method stub		
	}

	/**
	 * 
	 * @comment 根据后台传来的用户ID给角色ID重新赋值
	 * @author HM
	 */
	@Override
	public int addUserRoleId(HashMap map) {
		// TODO Auto-generated method stub
		return roleMapper.addUserRoleId(map);
	}

}
