package com.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.LogInfo;
import com.entity.UserInfo;
import com.service.impl.ILogInfoService;
import com.service.impl.IProjectInfoService;

/**
 *@comment 日志管理控制层
 *@author changjiaqi
 *@date 2018年7月16日 下午6:08:00
 *@version TODO
 */
@Controller
public class LogController {
	@Autowired
	ILogInfoService logInfoService;
	@Autowired
	IProjectInfoService projectInfoService;
	/**
	 * @comment: 分页查询日志列表
	 * @return ModelAndView
	 * @author changjiaqi
	 * @date 2018年7月16日
	 */
	@RequestMapping("/loglist")
	public ModelAndView logList(HttpServletRequest request, LogInfo logInfo) {

		ModelAndView mod = new ModelAndView("log-list");
		//获取session中的值
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserInfo");
		mod.addObject("userName",userInfo.getUserName());
		mod.addObject("projectInfoList", projectInfoService.findprojectInfo());
		System.out.println(projectInfoService.findprojectInfo().toString());
		request.getSession().setAttribute("page", logInfoService.findLogInfo(request, logInfo));
		//数据导出
		if (request.getParameter("export")!= null) {
			mod.setViewName("log-list-download");
		}
		
		return mod;
	}
	/**
	 * @comment: 删除日志信息
	 * @param userIds
	 * @return JSONObject  
	 * @author changjiaqi
	 * @date 2018年7月12日
	 */
	@RequestMapping("/deleteLogInfo")
	@ResponseBody
	public JSONObject deleteUserInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("re", logInfoService.deleteLogInfo(request));
		return json;
	}
}
