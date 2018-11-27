package com.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.AuthInfo;
import com.entity.ProjectInfo;
import com.entity.UserInfo;
import com.util.PageBean;

/**
 * 
 * @filename IUserInfoService.java
 * @author hm
 * @date 2018年7月3日下午3:16:15
 */
public interface IUserInfoService {
	/**
	 * 查询用户信息
	 *@返回类型：UserInfo
	 *@方法功能：
	 */
	public UserInfo selectAll();

	/**
	 * 
	 * 登录方法，传入的用户名和密码与数据库的进行对比
	 *@返回类型：UserInfo
	 *@方法功能：
	 */
	public UserInfo login(UserInfo userInfo);

	/**
	 * 查询用户权限
	 *@返回类型：List<AuthInfo>
	 *@方法功能：
	 */
	public List<AuthInfo> selectUserAuth(HashMap map);


	/**
	 * 查询用户列表（传入集合，返回对象）
	 *@返回类型：List<UserInfo>
	 *@方法功能：
	 */
	public List<UserInfo> selectUserInfo(HashMap map);

	/**
	 *  
	 *@返回类型：Integer
	 *@方法功能：查询用户的总条数
	 */
	public Integer getUserCount(UserInfo userInfo);

	/**
	 * 
	 *@返回类型：int
	 *@方法功能：根据前台传过来的用户ID修改用户状态
	 */
	public int updateStatic(Long userId);

	/**
	 * 
	 *@返回类型：int
	 *@方法功能：根据前台传来的用户ID修改用户状态
	 */
	public int updateStatics(UserInfo userInfo);

	
	/**
	 * 
	 *@返回类型：int
	 *@方法功能： 新增用户
	 */
	public int addUserInfo(UserInfo userInfo);

	/**
	 * 
	 *@返回类型：List<UserInfo>
	 *@方法功能：新增用户的同时根据用户名比对数据库是否存在此用户名
	 */
	public List<UserInfo> selectUserByName(UserInfo userInfo);

	/**
	 * 
	 *@返回类型：int
	 *@方法功能：重置密码
	 */
	public int updatapswd(UserInfo userInfo);

	/**
	 * 
	 *@返回类型：int
	 *@方法功能：启用禁用
	 */
	public int updataStaticById(HashMap map);
	
	/**
	 * 
	 *@返回类型：int
	 *@方法功能：根据用户名修改密码和昵称
	 */
	public int updatapswds(UserInfo userInfo);

	public void logout();

	/**
	 * 查询研发部人员
	 * @return
	 */
	public List<UserInfo> findUserByGroupId();
	/**
	 * 
	 *@comment 查找要添加的用户
	 *@author Administrator
	 *@date 2018年7月23日 上午8:42:50
	 *@param map
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findUserAdd(ProjectInfo proj,UserInfo user,PageBean page);
	
	/**
	 * 
	 *@comment 查看要添加用户数量
	 *@author Administrator
	 *@date 2018年7月23日 上午8:43:19
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	public int getUserAddNumber(ProjectInfo proj,UserInfo user);

	/**
	 *@comment 根据用户字符串来查询字符串
	 *@author  Administrator
	 *@date    2018年7月23日 下午7:17:31
	 *@param   userIds
	 *@return  List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findUsersByIds(String userIds);
	/**
	 *@comment 查询项目组成员
	 *@author Administrator
	 *@date 2018年7月24日 上午11:25:16
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findProjUsers(ProjectInfo proj);
	
	/**
	 *@comment 查询指定项目白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午11:41:00
	 *@param proj
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findProjWhite(ProjectInfo proj);

	/**
	 *@comment 根据Id查询指定用户
	 *@author Administrator
	 *@date 2018年7月24日 下午12:11:45
	 *@param user
	 *@return
	 *UserInfo
	 *@version 1.0
	 */
	public UserInfo getUserById(UserInfo user);

	/**
	 * @Description: TODO
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月30日
	 */
	public List<UserInfo> findAllUsers();
}
