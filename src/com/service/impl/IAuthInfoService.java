package com.service.impl;

import java.util.HashMap;
import java.util.List;

import com.entity.AuthInfo;

/**
 * 权限表的接口
 *@filename IAuthInfoService.java
 * @author hm
 * @date 2018年7月4日上午11:22:02
 * @version 1.0
 */
public interface IAuthInfoService {

	/**
	 * 
	 *@返回类型：List<AuthInfo>
	 *@方法功能：查询所有权限
	 */
	public List<AuthInfo> selectAuth();
	
	/**
	 * 
	 *@comment 新增权限 
	 *@author HM
	 *@resultType int
	 */
	public int addAuth(HashMap map);
	
	/**
	 * 
	 *@comment 根据传进来的权限ID判断权限code和权限名字是不是唯一
	 *@author HM
	 *@resultType List<AuthInfo>
	 */
	public List<AuthInfo> selectAuthById(HashMap map);
	
	/**
	 * 
	 *@comment 查询系统中所有有效的权限
	 *@author HM
	 *@resultType List<Role>
	 */
	public List<AuthInfo> selectAuthAll();
	
	/**
	 * 
	 *@comment 根据权限等级传进来的数据对数据库进行更改
	 *@author HM
	 *@resultType Integer
	 */
	public Integer updateAuthInfo(HashMap map);
	
	/**
	 * 
	 *@comment  删除权限之前根据传来的权限ID查询有无数据
	 *@author HM
	 *@resultType List<AuthInfo>
	 */
	public List<AuthInfo> selectAuthsById(AuthInfo authInfo);
	
	/**
	 * 
	 *@comment 删除权限
	 *@author HM
	 *@resultType Integer
	 */
	public Integer deleAuth(AuthInfo authInfo);
	
	/**
	 * 
	 *@comment 根据传进来的ID查询当前权限是否可用(状态),用于删除的权限红字显示
	 *@author HM
	 *@resultType List<AuthInfo>
	 */
	public List<AuthInfo> selectAuthByIds(AuthInfo authInfo);
	
	/**
	 * 
	 *@comment 根据传进来的authID查询用户所有信息
	 *@author HM
	 *@resultType List<AuthInfo>
	 */
	public List<AuthInfo> selectAuthByAuthId(AuthInfo authInfo);
	
	
	/**
	 * 
	 *@comment 根据查询的父ID作为权限ID进行查询上级有没有权限
	 *@author HM
	 *@resultType List<AuthInfo>
	 */
	public  List<AuthInfo> selectAuthByAuthIds(AuthInfo authInfo);
	/**
	 * 
	 *@comment 恢复权限
	 *@author HM
	 *@resultType Integer
	 */
	public Integer resumeAuth(AuthInfo authInfo);
}
