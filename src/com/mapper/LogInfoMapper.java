package com.mapper;

import java.util.List;
import java.util.Map;

import com.entity.LogInfo;
import com.entity.UserInfo;

/**
 *@comment 日志mapper接口
 *@author changjiaqi
 *@date 2018年7月16日 下午7:03:51
 *@version TODO
 */
public interface LogInfoMapper {
	/**
	 * @comment: 查询日志列表总条数
	 * @return int  
	 * @author changjiaqi
	 * @date 2018年7月16日
	 */
	public int findLogInfoCount(Map map);
	/**
	 * @comment: 分页查询日志列表
	 * @return   
	 * List<LogInfo>  
	 * @author changjiaqi
	 * @date 2018年7月16日
	 */
	public List<LogInfo> findLogInfo(Map map);
    /**
     * @comment: 添加日志信息
     * @param map   
     * void  
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
	public Integer getUserIdByName(UserInfo userInfo);
	public String findLogIdBy(Map map);
	/**
	 * @comment: 删除用户信息
	 * @param string
	 * @return int  
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */
	public int deleteLogInfo(String string);
	/**
	 * @comment: 通过用户code查询项目主键ID
	 * @param userInfo
	 * @return   
	 * int  
	 * @author changjiaqi
	 * @date 2018年7月21日
	 */
	public String getProjIdByUserCode(UserInfo userInfo);	
	

}
