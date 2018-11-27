package com.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.ProjectInfo;
import com.entity.Recycle;
import com.entity.Role;
import com.entity.SysClass;
import com.util.PageBean;

/**
 * 回收站接口
 * 
 * @comment
 * @author GaoErJi
 * @date 2018年7月17日 下午12:04:37
 * @modifyUser GaoErJi
 * @modifyDate 2018年7月17日 下午12:04:37
 * @modifyDesc TODO
 * @version TODO
 */
public interface IRecycleService {
	/**
	 * 分页查询回收站信息
	 * 
	 * @param request
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public PageBean getPageBean(HttpServletRequest request, Recycle recycle);

	/**
	 * 公用删除接口（描述、模块id、表名称、主键key、主键value）
	 * 
	 * @return back=1,回收站添加成功, back=2,删除状态成功, back=-1;执行错误;
	 * @version 1.0
	 */
	public int delToRecycle(Recycle recycle);

	/**
	 * 回收站还原,同时删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int rollbackRecycle(Recycle recycle);
	
	/**
	 * 数据的删除(物理删除)，删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int delRecycleById(HttpServletRequest request,Recycle recycle);

}
