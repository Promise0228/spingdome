package com.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.ProjectInfo;
import com.entity.Recycle;
import com.entity.TestExamp;
import com.entity.UserInfo;
import com.util.PageBean;

public interface ITestExampService {
	/**
	 * 分页查询测试用例信息
	 * 
	 * @param request
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public PageBean getPageBean(HttpServletRequest request, TestExamp testExamp);
	
	/**
	 * 修改测试用例
	 * @param testExamp
	 * @return
	 * @version 1.0
	 */
	public int updateTestExamp(TestExamp testExamp);

	/**
	 *@comment 根据项目查询项目组人员
	 *@author Administrator
	 *@date 2018年7月27日 下午1:48:16
	 *@param proj
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findTestUserInfo(ProjectInfo proj);

	/**
	 *@comment 添加测试用例
	 *@author Administrator
	 *@date 2018年7月27日 下午1:49:38
	 *@param testExamp
	 *@return
	 *int
	 *@version 1.0
	 */
	public int addTestExamp(HashMap map);
	
	/**
	 *@comment 查询测试用例可执行人员
	 *@author Administrator
	 *@date 2018年7月28日 下午1:05:17
	 *@param proj
	 *@param user
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findExecutors(ProjectInfo proj,UserInfo user);

	/**
	 *@comment 根据用户名查询用户
	 *@author Administrator
	 *@date 2018年7月28日 下午4:57:30
	 *@param user
	 *@return
	 *UserInfo
	 *@version 1.0
	 */
	public UserInfo getUserByName(UserInfo user);

	/**
	 *@comment 设置执行人
	 *@author Administrator
	 *@date 2018年7月28日 下午4:58:26
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	public int updateExecutor(HashMap map);

}
