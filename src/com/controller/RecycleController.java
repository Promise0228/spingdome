package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Recycle;
import com.service.impl.IRecycleService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("recycle")
public class RecycleController {
	@Autowired
	IRecycleService recycleService;

	/**
	 * 回收站列表分页信息
	 * 
	 * @param request
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("recyclelist")
	public ModelAndView toRecycle(HttpServletRequest request, Recycle recycle) {
		ModelAndView mav = new ModelAndView("recycle-list");
		System.out.println("----------"+recycleService.getPageBean(request,recycle).toString());
		mav.addObject("page", recycleService.getPageBean(request,recycle));
		return mav;
	}
	/**
	 * 回收站还原,同时删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("rollbackRecycle")
	@ResponseBody
	public JSONObject rollbackRecycle(HttpServletRequest request, Recycle recycle) {
		JSONObject json = new JSONObject();
		json.put("roll", recycleService.rollbackRecycle(recycle));
		return json;
	}
	/**
	 * 数据的删除(物理删除)（表名称、主键key、主键value），同时删除回收站中数据
	 * 
	 * @param request
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("delRecycleById")
	@ResponseBody
	public JSONObject delRecycleById(HttpServletRequest request, Recycle recycle) {
		JSONObject json = new JSONObject();
		int i=recycleService.delRecycleById(request,recycle);
		//i=1:成功
		//i=2:表删除失败
		//i=3:文件删除失败
		//i=4:文件不存在
		json.put("del", i);
		return json;
	} 
}
