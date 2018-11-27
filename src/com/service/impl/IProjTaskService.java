/**  
 * @Title: IProjTaskService.java
 * @Package com.item.service
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月25日
 */
package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.ProjTask;
import com.entity.SysClass;
import com.entity.UserInfo;
import com.util.PageBean;

/**
 * ClassName: IProjTaskService 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月25日
 */
public interface IProjTaskService {

	/**
	 * @Description: 任务列表分页展示信息
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月25日
	 */
	public PageBean getPageBean(HttpServletRequest request,ProjTask projTask );

	/**
	 * @Description: 添加任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public int addProjTask(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 修改任务回写信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public ProjTask getUpdProjTask(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 获取所有任务状态
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public SysClass getAlltaskState(HttpServletRequest request,ProjTask projTask);

	/**
	 * @Description: 修改任务信息
	 * @param @param request
	 * @param @param projTask   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public int updateProjTask(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 获取任务信息详情
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public ProjTask getDealProjTask(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 删除任务
	 * @param @param request
	 * @param @param projTask   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public int delProjTask(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 获取所有任务信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public List<SysClass> findAllproj(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 获取所有模块信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public List<SysClass> findAllprojMK(HttpServletRequest request, ProjTask projTask);

	/**
	 * @Description: 查询所有用户
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月27日
	 */
	public List<UserInfo> findAllUser();

	/**
	 * @Description: 获取快过期任务条数
	 * @param @param request
	 * @param @param projTask   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月28日
	 */
	public int getOutTask(HttpServletRequest request);

	/**
	 * @Description: TODO
	 * @param @param request
	 * @param @param userInfo
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	public Integer getUserIdByUserCode(HttpServletRequest request,
			UserInfo userInfo); 

}
