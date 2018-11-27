package com.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.entity.LogInfo;
import com.entity.UserInfo;
import com.util.PageBean;

/**
 * @comment 日志列表业务层接口
 * @author changjiaqi
 * @date 2018年7月17日 上午10:50:50
 * @version TODO
 */
public interface ILogInfoService {

	/**
	 * @comment:查询日志列表 分页查询
	 * @param map
	 * @return List<Role>
	 * @author changjiaqi
	 * @date 2018年7月12日
	 */
	public PageBean findLogInfo(HttpServletRequest request, LogInfo logInfo);

	/**
	 * @comment: 根据用户Id查询用户名称
	 * @param logInfo
	 * @return String
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */
	//public String findUserNameById(UserInfo userInfo);

	/**
	 * @comment: 添加日志信息
	 * @param map
	 *            void
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */
	public void insertLogInfo(Map map);
	/**
	 * @comment: 根据用户名查询用户Id;
	 * @param userInfo
	 * @return   
	 * int  
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */
	public int getUserIdByName(UserInfo userInfo);
    /**
     * @comment: 删除日志信息
     * @param logIds
     * @return   
     * Object  
     * @author changjiaqi
     * @date 2018年7月19日
     */
	public  int deleteLogInfo(HttpServletRequest request);

	/**
	 * @comment: 通过用户code查询项目主键ID
	 * @param userInfo
	 * @return   
	 * Object  
	 * @author changjiaqi
	 * @date 2018年7月21日
	 */
	public String getProjIdByUserCode(UserInfo userInfo);
	
}
