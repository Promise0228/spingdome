/**
 * 
 */
package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.ProjectInfo;
import com.entity.ProjDynamic;
import com.entity.SysClass;
import com.entity.UserInfo;

/**
 *@comment 项目crud接口
 *@author Administrator
 *@date 2018年7月19日 下午4:47:02
 *@modifyUser Administrator
 *@modifyDate 2018年7月19日 下午4:47:02
 *@modifyDesc  TODO
 *@version 1.0
 */

public interface ProjectInfoMapper {
   /**
	* 
	*@comment 通过用户及项目输入条件查询项目集合
	*@author Administrator
	*@date 2018年7月19日 下午4:50:03
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
	 *@date 2018年7月19日 下午6:16:28
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	int getProjsNumber(HashMap map);
	
	/**
	 * 
	 *@comment 添加项目
	 *@author Administrator
	 *@date 2018年7月20日 下午3:42:18
	 *@param proj
	 *@return
	 *int
	 *@version 1.0
	 */
	int addProj(HashMap map);
	
	/**
	 * 
	 *@comment 对项目名称及项目编码查重
	 *@author Administrator
	 *@date 2018年7月20日 下午11:24:21
	 *@param proj
	 *@return
	 *int
	 *@version 1.0
	 */
	int checkProj(ProjectInfo proj);
	
	/**
	 *@comment 添加白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午9:20:50
	 *@param user
	 *@return
	 *int
	 *@version 1.0
	 */
	public int addWhiteSheet(HashMap map);

	/**
	 *@comment 通过项目Id获取单个项目信息
	 *@author Administrator
	 *@date 2018年7月24日 上午10:30:24
	 *@param proj
	 *@return
	 *Proj
	 *@version 1.0
	 */
	public ProjectInfo getProjById(ProjectInfo proj);

	/**
	 *@comment 修改项目
	 *@author Administrator
	 *@date 2018年7月24日 下午7:09:16
	 *@param proj
	 *@return int
	 *@version 1.0
	 */
	public int updateProj(ProjectInfo proj);
	
	/**
	 *@comment 查询指定项目Id及别的条件的项目动态,显示最新
	 *@author Administrator
	 *@date 2018年7月25日 上午9:11:42
	 *@param map
	 *@return
	 *ProjDyna
	 *@version 1.0
	 */
	public List<ProjDynamic> findProjDynas(HashMap map);
	
	/**
	 *@comment 查询指定项目的项目动态条数
	 *@author Administrator
	 *@date 2018年7月25日 上午10:25:05
	 *@param projId
	 *@return int
	 *@version 1.0
	 */
	public int getProjDynasNum(HashMap map);
	
	/**
	 *@comment 查询指定项目动态评论
	 *@author Administrator
	 *@date 2018年7月25日 下午3:37:57
	 *@param projId
	 *@param parentId
	 *@return List<ProjDyna>
	 *@version 1.0
	 */
	public List<ProjDynamic> findProjDynaComments(int parentId);

	/**
	 *@comment 添加项目动态或回复
	 *@author Administrator
	 *@date 2018年7月26日 上午9:55:14
	 *@param map
	 *@return int
	 *@version 1.0
	 */
	public int addProjDynamic(HashMap map);

	/**
	 *@comment 查询项目的已消耗时间
	 *@author Administrator
	 *@date 2018年7月26日 下午12:06:58
	 *@param proj
	 *@return int
	 *@version 1.0
	 */
	public int getProjExpend(ProjectInfo proj);

	/**
	 *@comment 删除白名单
	 *@author Administrator
	 *@date 2018年7月26日 下午3:17:41
	 *@param proj
	 *@return
	 *int
	 *@version 1.0
	 */
	public int delWhiteSheet(ProjectInfo proj);
	/**
	 * @comment: 查询项目基本信息列表
	 * @return   
	 * List<ProjectInfo>  
	 * @author changjiaqi
	 * @date 2018年7月24日
	 */
	public List<ProjectInfo> findprojectInfo();
	
	/**
	 *@comment 添加项目时加入系统分类表
	 *@author Administrator
	 *@date 2018年7月29日 下午2:32:51
	 *@param map
	 *@return int
	 *@version 1.0
	 */
	public int addSysClass(HashMap map);
	
	/**
	 *@comment 根据项目编码查询系统分类
	 *@author Administrator
	 *@date 2018年7月29日 下午2:32:51
	 *@param map
	 *@return int
	 *@version 1.0
	 */
	public SysClass getSysClassByProj(ProjectInfo proj);
}
