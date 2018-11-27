package com.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.entity.ProjVersion;
import com.util.PageBean;

/**
 *@comment 版本信息业务层接口
 *@author changjiaqi
 *@date 2018年7月24日 下午10:46:51
 *@version TODO
 */
public interface IProjVersionService {
	
	/**
	 * @comment:查询日志列表 分页查询
	 * @param map
	 * @return List<Role>
	 * @author changjiaqi
	 * @date 2018年7月12日
	 */
	public PageBean findProjVersion(HttpServletRequest request, ProjVersion projVersion);

	/**
	 * @comment: 添加版本信息
	 * @param projVersion
	 * @return   
	 * int  
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int addProjVersion(HttpServletRequest request,ProjVersion projVersion);

	/**
	 * @comment: 修改版本信息
	 * @param projVersion
	 * @return   
	 * Object  
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int updateProjVersion(ProjVersion projVersion);

	/**
	 * @comment:删除版本信息
	 * @param projVersion
	 * @return int  
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int deleteProjVersion(ProjVersion projVersion);

}
