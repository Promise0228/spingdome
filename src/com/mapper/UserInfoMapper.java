package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.AuthInfo;
import com.entity.ProjectInfo;
import com.entity.UserInfo;
/**
 * 
 * @filename UserInfoMapper.java
 * @author hm
 * @date 2018年7月3日下午3:22:20
 */
public interface UserInfoMapper {
	/**
	 * 
	 *@返回类型：UserInfo
	 *@方法功能：测试查询用户列表
	 */
	public UserInfo selectAll();

	/**
	 * 
	 *@返回类型：UserInfo
	 *@方法功能：用户登录，传入对象，比对数据库的用户名和密码
	 */
	public UserInfo login(UserInfo userInfo);

	/**
	 * 
	 *@返回类型：List<AuthInfo>
	 *@方法功能： 查询用户权限
	 */
	public List<AuthInfo> selectUserAuth(HashMap map);

	/**
	 * 
	 *@返回类型：List<UserInfo>
	 *@方法功能：查询用户列表（传入集合，返回对象）
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
	 *@方法功能：根据前台传过来的用户ID修改用户删除状态
	 */
	public int updateStatic(Long userInfo);


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

	/**
	 * 查询研发部人员
	 * @return
	 */
	public List<UserInfo> findUserByGroupId();
	
	/**
	 * 麻斌
	 */
	
	/**
	 * 
	 *@comment 根据userIds字符串查询用户
	 *@author Administrator
	 *@date 2018年7月23日 下午7:14:14
	 *@param userIds
	 *@return List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findUsersByIds(String userIds);
	
	/**
	 *@comment 查询所有项目经理
	 *@author Administrator
	 *@date 2018年7月20日 上午10:36:05
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findProjManagers();

	/**
	 * 
	 *@comment 查看用户白名单或按用户名查看用户
	 *@author Administrator
	 *@date 2018年7月21日 下午2:36:56
	 *@param proj
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findWhiteSheet(HashMap map);

	/**
	 * 
	 *@comment 
	 *@author Administrator
	 *@date 2018年7月21日 下午3:59:32
	 *@param map
	 *@return int
	 *@version 1.0
	 */
	public int getWhiteNumber(ProjectInfo proj);
	
	/**
	 * 
	 *@comment 查找要添加人员
	 *@author Administrator
	 *@date 2018年7月23日 上午8:40:02
	 *@param map
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findUserAdd(HashMap map);
	
	/**
	 * 
	 *@comment 查找要添加人员数量
	 *@author Administrator
	 *@date 2018年7月23日 上午8:40:29
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	public int  getUserAddNumber(HashMap map);

	/**
	 *@comment 查询项目组成员
	 *@author Administrator
	 *@date 2018年7月24日 上午11:21:15
	 *@param proj
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findProjUsers(ProjectInfo proj);

	/**
	 *@comment 查询指定项目白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午11:39:57
	 *@param proj
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findProjWhite(ProjectInfo proj);

	/**
	 *@comment 查询项目
	 *@author Administrator
	 *@date 2018年7月24日 下午12:06:15
	 *@param user
	 *@return UserInfo
	 *@version 1.0
	 */
	public UserInfo getUserById(UserInfo user);

	/**
	 * @Description: TODO
	 * @param @return   
	 * @return List<UserInfo>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月30日
	 */
	public List<UserInfo> findAllUsers();
}
