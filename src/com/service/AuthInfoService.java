package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.AuthInfo;
import com.mapper.AuthInfoMapper;
import com.service.impl.IAuthInfoService;
/**
 * 
 *@filename AuthInfoService.java
 * @author hm
 * @date 2018年7月4日上午11:24:03
 * @version 1.0
 */
@Service
public class AuthInfoService implements IAuthInfoService {

	//注入Mapper层
	@Autowired
	AuthInfoMapper authInfoMapper;

	/**
	 * 
	 *@返回类型：List<AuthInfo>
	 *@方法功能：查询所有权限
	 */
	@Override
	public List<AuthInfo> selectAuth() {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuth();
	}
	
	/**
	 * 
	 *@comment 新增权限 
	 *@author HM
	 *@resultType int
	 */
	@Override
	public int addAuth(HashMap map) {
		// TODO Auto-generated method stub
		return authInfoMapper.addAuth(map);
	}

	/**
	 * 
	 *@comment 根据传进来的权限ID判断权限code和权限名字是不是唯一
	 *@author HM
	 *@resultType List<AuthInfo>
	 */
	@Override
	public List<AuthInfo> selectAuthById(HashMap map) {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuthById(map);
	}

	/**
	 * 查询系统中所有有效的权限
	@Override
	 */
	public List<AuthInfo> selectAuthAll() {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuthAll();
	}

	/**
	 * 根据权限等级传进来的数据对数据库进行更改
	 */
	@Override
	public Integer updateAuthInfo(HashMap map) {
		// TODO Auto-generated method stub
		return authInfoMapper.updateAuthInfo(map);
	}

	/**
	 * 根据ID查询所有权限
	 */
	@Override
	public List<AuthInfo> selectAuthsById(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuthsById(authInfo);
	}

	/**
	 * 根据ID删除权限
	 */
	@Override
	public Integer deleAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoMapper.deleAuth(authInfo);
	}

	/**
	 * 根据传进来的ID查询当前权限是否可用(状态),用于删除的权限红字显示
	 */
	@Override
	public List<AuthInfo> selectAuthByIds(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuthByIds(authInfo);
	}

	/**
	 * 根据传进来的authID查询用户所有信息
	 */
	@Override
	public List<AuthInfo> selectAuthByAuthId(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuthByAuthId(authInfo);
	}

	/**
	 * 根据查询的父ID作为权限ID进行查询上级有没有权限
	 */
	@Override
	public List<AuthInfo> selectAuthByAuthIds(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoMapper.selectAuthByAuthIds(authInfo);
	}

	/**
	 * 恢复权限
	 */
	@Override
	public Integer resumeAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoMapper.resumeAuth(authInfo);
	}

}
