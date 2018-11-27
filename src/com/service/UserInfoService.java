package com.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.AuthInfo;
import com.entity.ProjectInfo;
import com.entity.UserInfo;
import com.mapper.UserInfoMapper;
import com.service.impl.IUserInfoService;
import com.util.PageBean;

/**
 * 
 * @filename UserInfoService.java
 * @author hm
 * @date 2018年7月3日下午3:21:30
 */
@Service
public class UserInfoService implements IUserInfoService {

	@Autowired
	UserInfoMapper userInfoMapper;

	/**
	 * 
	 * @comment 查询用户信息
	 * @author HM
	 */
	@Override
	public UserInfo selectAll() {
		// TODO Auto-generated method stub
		return userInfoMapper.selectAll();
	}

	/**
	 * 用户登录
	 * @comment 
	 * @author HM
	 */
	@Override
	public UserInfo login(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.login(userInfo);
	}

	/**
	 * 
	 * @comment 查询用户权限
	 * @author HM
	 */
	@Override
	public List<AuthInfo> selectUserAuth(HashMap map) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUserAuth(map);
	}

	/**
	 * 
	 * @comment 查询用户信息的总条数
	 * @author HM
	 */
	@Override
	public Integer getUserCount(UserInfo userInfo) {
		int a = userInfoMapper.getUserCount(userInfo);
		return a;
	}

	/**
	 * 
	 * @comment 查询用户列表（传入集合，返回对象）
	 * @author HM
	 */
	@Override
	public List<UserInfo> selectUserInfo(HashMap map) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUserInfo(map);
	}

	/**
	 * 
	 * @comment 根据前台传过来的用户ID修改用户删除状态
	 * @author HM
	 */
	@Override
	public int updateStatic(Long userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateStatic(userId);
	}

	/**
	 * 
	 * @comment 根据前台传来的用户ID修改用户状态
	 * @author HM
	 */
	@Override
	public int updateStatics(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateStatics(userInfo);
	}

	/**
	 * 
	 * @comment 新增用户
	 * @author HM
	 */
	@Override
	public int addUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.addUserInfo(userInfo);
	}

	/**
	 * 
	 * @comment 重置密码
	 * @author HM
	 */
	@Override
	public int updatapswd(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.updatapswd(userInfo);
	}

	/**
	 * 
	 * @comment 新增用户的同时根据用户名比对数据库是否存在此用户名
	 * @author HM
	 */
	@Override
	public List<UserInfo> selectUserByName(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUserByName(userInfo);
	}

	/**
	 * 
	 * @comment 启用禁用
	 * @author HM
	 */
	@Override
	public int updataStaticById(HashMap map) {
		return userInfoMapper.updataStaticById(map);
	}

	/**
	 * 
	 * @comment 根据用户名修改密码和昵称
	 * @author HM
	 */
	@Override
	public int updatapswds(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.updatapswds(userInfo);
	}

	public void logout() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 查询研发部人员
	 */
	@Override
	public List<UserInfo> findUserByGroupId() {
		// TODO Auto-generated method stub
		return userInfoMapper.findUserByGroupId();
	}

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
	@Override
	public List<UserInfo> findUserAdd(ProjectInfo proj,UserInfo user,PageBean page) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("proj", proj);
		map.put("user", user);
		map.put("page", page);
		return userInfoMapper.findUserAdd(map);
	}

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
	@Override
	public int getUserAddNumber(ProjectInfo proj,UserInfo user) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("proj", proj);
		map.put("user", user);
		return userInfoMapper.getUserAddNumber(map);
	}

	/**
	 *@comment 根据用户字符串来查询字符串
	 *@author  Administrator
	 *@date    2018年7月23日 下午7:17:31
	 *@param   userIds
	 *@return  List<UserInfo>
	 *@version 1.0
	 */
	@Override
	public List<UserInfo> findUsersByIds(String userIds) {
		// TODO Auto-generated method stub
		return userInfoMapper.findUsersByIds(userIds);
	}

	
	@Override
	public List<UserInfo> findProjUsers(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return userInfoMapper.findProjUsers(proj);
	}

	/**
	 *@comment 查询指定项目白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午11:41:00
	 *@param proj
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	@Override
	public List<UserInfo> findProjWhite(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return userInfoMapper.findProjWhite(proj);
	}

	
	@Override
	public UserInfo getUserById(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoMapper.getUserById(user);
	}

	/**
	 * @Description: TODO
	 * @param @return   
	 * @return   
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月30日
	 */
	@Override
	public List<UserInfo> findAllUsers() {
		// TODO Auto-generated method stub
		return userInfoMapper.findAllUsers();
	}
}
