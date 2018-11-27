package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.UserGroup;
import com.mapper.UserGroupMapper;
import com.service.impl.IUserGroupService;
/**
 * 用户组信息
 *@filename UserGroupService.java
 * @author hm
 * @date 2018年7月9日下午5:44:55
 * @version 1.0
 */
@Service
public class UserGroupService implements IUserGroupService {

	@Autowired
	UserGroupMapper userGroupMapper;
	
	/**
	 * 
	 * @comment 查询所有用户组信息
	 * @author HM
	 */
	@Override
	public List<UserGroup> selectUserGroup() {
		// TODO Auto-generated method stub
		return userGroupMapper.selectUserGroup();
	}
	
	/**
	 * 
	 * @comment 根据传过来的用户组code查询用户组信息
	 * @author HM
	 */
	@Override
	public List<UserGroup> selectUserGroupByCode(UserGroup userGroup) {
		// TODO Auto-generated method stub
		return userGroupMapper.selectUserGroupByCode(userGroup);
	}
	
	/**
	 * 
	 * @comment 新增用户组
	 * @author HM
	 */
	@Override
	public Integer addUserGroup(UserGroup userGroup) {
		// TODO Auto-generated method stub
		return userGroupMapper.addUserGroup(userGroup);
	}
	
	/**
	 * 
	 * @comment 查询用户组条数
	 * @author HM
	 */
	@Override
	public Integer countUserGroup(HashMap map) {
		// TODO Auto-generated method stub
		return userGroupMapper.countUserGroup(map);
	}
	
	/**
	 * 
	 * @comment 启用禁用组
	 * @author HM
	 */
	@Override
	public Integer restart(UserGroup userGroup) {
		// TODO Auto-generated method stub
		return userGroupMapper.restart(userGroup);
	}

}
