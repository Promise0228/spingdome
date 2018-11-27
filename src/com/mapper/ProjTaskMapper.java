/**  
 * @Title: ProjTaskMapper.java
 * @Package com.item.mapper
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月25日
 */
package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.ProjTask;
import com.entity.Role;
import com.entity.SysClass;
import com.entity.UserInfo;

/**
 * ClassName: ProjTaskMapper 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月25日
 */
public interface ProjTaskMapper {

	/**
	 * @Description: 获取任务条数
	 * @param @param hashMap
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月25日
	 */
	public int getCount(HashMap<String, Object> hashMap);

	/**
	 * @Description: 分页,条件查询任务信息[数据权限]
	 * @param @param hashMap
	 * @param @return   
	 * @return List<UserInfo>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月25日
	 */
	public List<ProjTask> getAllProjTaskByCon(HashMap<String, Object> hashMap);

	/**
	 * @Description: 获取任务详情
	 * @param @param projId   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public ProjTask getProjMess(String projId);

	/**
	 * @Description: 添加任务
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public int addProjTask(HashMap<String, Object> map);

	/**
	 * @Description: 根据任务id获取修改任务信息
	 * @param @param projTask
	 * @param @return   
	 * @return ProjTask  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public ProjTask getUpdProjTask(ProjTask projTask);



	/**
	 * @Description: 获取任务状态
	 * @param @return   
	 * @return SysClass  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public SysClass getAlltaskState(int id);

	/**
	 * @Description: 修改任务信息
	 * @param @param projTask
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public int updateProjTask(ProjTask projTask);

	/**
	 * @Description: 根据任务id获取修改任务信息
	 * @param @param projTask
	 * @param @return   
	 * @return ProjTask  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public ProjTask getDealProjTask(ProjTask projTask);

	/**
	 * @Description: 删除任务
	 * @param @param projTask
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public int delProjTask(ProjTask projTask);

	/**
	 * @Description: 查询所以项目类型 项目
	 * @param @return   
	 * @return ProjInfo  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public List<SysClass> findAllproj();

	/**
	 * @Description: 查询所有模块类型的模块
	 * @param @return   
	 * @return List<SysClass>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	public List<SysClass> findAllprojMK();

	/**
	 * @Description:查询所有用户信息
	 * @param @return   
	 * @return List<UserInfo>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月27日
	 */
	public List<UserInfo> findAllUser();

	/**
	 * @Description: 获取快过期任务
	 * @param @param hashMap
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月28日
	 */
	public int getOutTimeCount(HashMap<String, Object> hashMap);

	/**
	 * @Description: 获取当前用户的身份
	 * @param @param userId   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月28日
	 */
	public String getRoleCode(int userId);

	/**
	 * @Description: TODO
	 * @param @param usercode   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	public Integer getUid(String usercode);

}
