package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.ProjectInfo;
import com.entity.Recycle;
import com.entity.TestExamp;
import com.entity.UserInfo;

public interface TestExampMapper {
	/**
	 * 查询测试用例列表
	 * 
	 * @return
	 * @version 1.0
	 */
	public List<TestExamp> getTestExamps(HashMap<String, Object> hashMap);

	/**
	 * 查询测试用例总条数
	 * 
	 * @return
	 * @version 1.0
	 */
	public int getCount(HashMap<String, Object> hashMap);
	/**
	 * 修改测试用例
	 * @param testExamp
	 * @return
	 * @version 1.0
	 */
	public int updateTestExamp(TestExamp testExamp);

	/**
	 * 
	 *@comment 根据项目Id查找测试组人员
	 *@author Administrator
	 *@date 2018年7月27日 上午10:02:24
	 *@return
	 *UserInfo
	 *@version 1.0
	 */
	public List<UserInfo> findTestUser(ProjectInfo proj);

	/**
	 *@comment 添加测试用例
	 *@author Administrator
	 *@date 2018年7月27日 上午11:49:43
	 *@param testExamp
	 *@return int
	 *@version 1.0
	 */
	public int addTestExamp(HashMap map);
	
	/**
	 * 
	 *@comment 查询测试用例执行人
	 *@author Administrator
	 *@date 2018年7月28日 下午1:02:51
	 *@param map
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	public List<UserInfo> findExecutors(HashMap map);

	/**
	 *@comment 根据用户名查询用户
	 *@author Administrator
	 *@date 2018年7月28日 下午4:48:54
	 *@param user
	 *@return
	 *UserInfo
	 *@version 1.0
	 */
	public UserInfo getUserByName(UserInfo user);
	
	/**
	 * 
	 *@comment 设置执行人
	 *@author Administrator
	 *@date 2018年7月28日 下午4:56:23
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	public int updateExecutor(HashMap map);
}
