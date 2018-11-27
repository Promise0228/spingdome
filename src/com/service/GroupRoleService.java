package com.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.GroupRole;
import com.mapper.GroupRoleMapper;
import com.service.impl.IGroupRoleService;

/**
 * 用户组角色类
 * 
 * @filename GroupRoleService.java
 * @author hm
 * @date 2018年7月11日上午11:41:09
 * @version 1.0
 */
@Service
public class GroupRoleService implements IGroupRoleService {

	@Autowired
	GroupRoleMapper groupRoleMapper;

	/**
	 * 
	 * @comment 根据组权限ID查询组信息
	 * @author HM
	 */
	@Override
	public String selectGroupRoleById(GroupRole groupRole) {
		// TODO Auto-generated method stub
		return groupRoleMapper.selectGroupRoleById(groupRole);
	}

	/**
	 * 
	 * @comment 根据传进来的ID删除组角色
	 * @author HM
	 */
	@Override
	public int deleGroupRoleId(HashMap map) {
		// TODO Auto-generated method stub
		return groupRoleMapper.deleGroupRoleId(map);
	}

	/**
	 * 
	 * @返回类型：int
	 * @方法功能：根据后台传来的用户ID给用户角色ID重新赋值
	 */
	@Override
	public int addGroupRoleId(HashMap map) {
		// TODO Auto-generated method stub
		return groupRoleMapper.addGroupRoleId(map);
	}

}
