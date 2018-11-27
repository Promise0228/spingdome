/**
 * 
 */
package com.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.ProjectInfo;
import com.entity.ProjDynamic;
import com.entity.SysClass;
import com.entity.UserInfo;
import com.util.PageBean;

/**
 *@comment 项目业务接口
 *@author Administrator
 *@date 2018年7月19日 下午5:12:50
 *@modifyUser Administrator
 *@modifyDate 2018年7月19日 下午5:12:50
 *@modifyDesc  TODO
 *@version 1.0
 */

public interface IProjectInfoService {
	/**
	 * 
	 *@comment 根据用户及项目输入条件查询所有项目
	 *@author Administrator
	 *@date 2018年7月19日 下午5:14:23
	 *@param user
	 *@return
	 *List<Proj>
	 *@version 1.0
	 */
	List<ProjectInfo> findProjs(HashMap map);
	/**
	 * 
	 *@comment 根据用户及项目输入条件查询项目总条数
	 *@author Administrator
	 *@date 2018年7月19日 下午6:18:24
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	int getProjsNumber(HashMap map);
	
	/**
	 * 
	 *@comment 查询所有项目经理
	 *@author Administrator
	 *@date 2018年7月20日 上午10:47:49
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	List<UserInfo> findProjManagers();
	
	/**
	 * 
	 *@comment 添加项目
	 *@author Administrator
	 *@date 2018年7月20日 下午11:11:50
	 *@param request
	 *@return
	 *int
	 *@version 1.0
	 */
	int addProj(HttpServletRequest request,ProjectInfo proj);

	/**
	 * 
	 *@comment 对项目名及项目编码查重
	 *@author Administrator
	 *@date 2018年7月20日 下午11:32:01
	 *@param proj
	 *@return
	 *int
	 *@version 1.0
	 */
	int checkProj(ProjectInfo proj);
	
	/**
	 *@comment 添加白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午9:15:49
	 *@param userIds
	 *@return
	 *int
	 *@version 1.0
	 */
	public int addWhiteSheet(String userIds,String projId);

	/**
	 *@comment 通过项目Id获取项目
	 *@author Administrator
	 *@date 2018年7月24日 上午10:31:52
	 *@param proj
	 *@return
	 *Proj
	 *@version 1.0
	 */
	public ProjectInfo getProjById(ProjectInfo proj);
	
	/**
	 *@comment 修改指定id的项目
	 *@author Administrator
	 *@date 2018年7月24日 下午7:10:51
	 *@param proj
	 *@return
	 *int
	 *@version 1.0
	 */
	public int updateProj(ProjectInfo proj);

	/**
	 *@comment 分页查询项目动态
	 *@author Administrator
	 *@date 2018年7月25日 下午3:06:19
	 *@param projId
	 *@param page
	 *@return List<ProjDyna>
	 *@version 1.0
	 */
	public List<ProjDynamic> findProjDynas(ProjDynamic projDyna,PageBean page);

	/**
	 * 
	 *@comment 删除项目白名单
	 *@author Administrator
	 *@date 2018年7月26日 下午3:20:18
	 *@param proj
	 *void
	 *@version 1.0
	 */
	public int delWhiteSheet(ProjectInfo proj);
	
	/**
	 *@comment 查询项目动态数量
	 *@author Administrator
	 *@date 2018年7月25日 下午6:32:50
	 *@param projId
	 *@param projDyna
	 *@return int
	 *@version 1.0
	 */
	public int getProjDynasNum(ProjDynamic projDyna);
	/**
	 * 
	 *@comment 根据项目动态或回复Id查询项目动态回复
	 *@author Administrator
	 *@date 2018年7月25日 下午10:49:59
	 *@param parentId
	 *@return
	 *List<ProjDyna>
	 *@version 1.0
	 */
	public List<ProjDynamic> findProjDynaComments(int parentId);

	/**
	 *@comment 添加项目动态或回复
	 *@author Administrator
	 *@date 2018年7月26日 上午9:52:19
	 *@param user
	 *@param projDyna
	 *@return int
	 *@version 1.0
	 */
	public int addProjDynamic(UserInfo user,ProjDynamic projDyna);
	
	/**
	 *@comment 查询项目已消耗时间
	 *@author Administrator
	 *@date 2018年7月26日 下午12:10:05
	 *@param proj
	 *@return int
	 *@version 1.0
	 */
	public int getProjExpend(ProjectInfo proj);
	/**
	 * @comment: 查询项目基本信息列表
	 * @return   
	 * List<ProjectInfo>  
	 * @author changjiaqi
	 * @date 2018年7月24日
	 */
	public List<ProjectInfo> findprojectInfo();
	
	
	/**
	 * 
	 *@comment 添加项目时加入系统分类
	 *@author Administrator
	 *@date 2018年7月29日 下午2:34:27
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	public int addSysClass(HashMap map);

	/**
	 * 
	 *@comment 根据项目编码查询系统分类
	 *@author Administrator
	 *@date 2018年7月29日 下午2:34:27
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	public SysClass getSysClassByProj(ProjectInfo proj);
}
