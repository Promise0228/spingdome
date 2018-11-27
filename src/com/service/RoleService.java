package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Role;
import com.mapper.RoleMapper;
import com.service.impl.IRoleService;

@Service
public class RoleService implements IRoleService {

	//注入mapper层
	@Autowired
	RoleMapper roleMapper;

	/**
	 * 
	 * @comment 查询所有角色
	 * @author HM
	 */
	@Override
	public List<Role> findRoleAll(HashMap map) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleAll(map);
	}

	/**
	 * 
	 * @comment 查询所有角色的条数
	 * @author HM
	 */
	@Override
	public Integer countRoleAll(HashMap map) {
		// TODO Auto-generated method stub
		return roleMapper.countRoleAll(map);
	}

	/**
	 * 
	 * @comment 根据角色ID查询对应角色信息
	 * @author HM
	 */
	@Override
	public Role findRoleById(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleById(role);
	}

	/**
	 * 
	 * @comment 更新角色信息
	 * @author HM
	 */
	@Override
	public Integer refushRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.refushRole(role);
	}

	/**
	 * 
	 * @comment 新增角色
	 * @author HM
	 */
	@Override
	public Integer addRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.addRole(role);
	}

	/**
	 * 
	 * @comment 根据roleCode查询角色表是否有重复的
	 * @author HM
	 */
	@Override
	public List<Role> findRoleByCode(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleByCode(role);
	}

	/**
	 * 
	 * @comment 禁用/启用角色
	 * @author HM
	 */
	@Override
	public Integer restart(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.restart(role);
	}

	/**
	 * 
	 * @comment 根据角色ID查询角色信心
	 * @author HM
	 */
	@Override
	public String selectRoleById(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleById(role);
	}
	
	

}
