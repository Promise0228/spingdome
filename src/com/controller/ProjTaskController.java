/**  
 * @Title: ProjTaskController.java
 * @Package com.item.controller
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月25日
 */
package com.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.ProjTask;
import com.entity.UserInfo;
import com.service.impl.IProjTaskService;

/**
 * ClassName: ProjTaskController 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月25日
 */
@Controller
@RequestMapping("projtask")
public class ProjTaskController {
	@Autowired
	IProjTaskService projTaskService;
	/**
	 * 
	 * @Description: 项目任务页面跳转
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("projtasklist")
	public ModelAndView showProjTaskList(HttpServletRequest request,ProjTask projTask ) {
		ModelAndView mav = new ModelAndView("projtask-list");
		mav.addObject("page", projTaskService.getPageBean(request,projTask));
		mav.addObject("sysc", projTaskService.findAllproj(request,projTask));
		mav.addObject("syscmk", projTaskService.findAllprojMK(request,projTask));
		mav.addObject("puser", projTaskService.findAllUser());
		if ("out".equals(request.getParameter("outtimemark"))) {
			mav.addObject("outtimemark", "out");
		}
		mav.addObject("outtime", projTaskService.getOutTask(request));
		int outTask = projTaskService.getOutTask(request);
		request.getSession().setAttribute("nearOutTask", outTask);
		return mav;
		
	}
	/**
	 * 
	 * @Description: 删除项目任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("delprojtask")
	public String delProjTask(HttpServletRequest request,ProjTask projTask) {
		projTaskService.delProjTask(request,projTask);
		return "redirect:projtasklist.action";
	}
	
	/**
	 * 
	 * @Description: 添加项目任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("addprojtask")
	public String addProjTask(HttpServletRequest request,ProjTask projTask) {
		System.out.println("调教我呀");
		projTaskService.addProjTask(request,projTask);
		return "redirect:projtasklist.action";
	}
	/**
	 * 
	 * @Description: 修改项目任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("updateprojtask")
	public String updateProjTask(HttpServletRequest request,ProjTask projTask) {
		projTaskService.updateProjTask(request,projTask);
		return "redirect:projtasklist.action";
	}
	/**
	 * 
	 * @Description: 修改项目任务回写信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return JSONObject  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("getupdprojtask")
	@ResponseBody
	public JSONObject getUpdProjTask(HttpServletRequest request,ProjTask projTask) {
		JSONObject json = new JSONObject();
		json.put("msg", projTaskService.getUpdProjTask(request,projTask));
		return json;
	}
	/**
	 * 
	 * @Description: 根据id查询项目信息 回写任务详情
	 * @param @param request
	 * @param @param projTask
	 * @param @return   
	 * @return JSONObject  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("getdealprojtask")
	@ResponseBody
	public JSONObject getDealProjTask(HttpServletRequest request,ProjTask projTask) {
		JSONObject json = new JSONObject();
		json.put("msg", projTaskService.getDealProjTask(request,projTask));
		json.put("sysmk", projTaskService.findAllprojMK(request,projTask));
		//json.put("duser", projTaskService.findAllUser());
		return json;
	}
	@RequestMapping("getuseridbyusercode")
	@ResponseBody
	public JSONObject getUserIdByUserCode(HttpServletRequest request,UserInfo userInfo) {
		JSONObject json = new JSONObject();
		json.put("msg", projTaskService.getUserIdByUserCode(request,userInfo));
		return json;
	}

}
