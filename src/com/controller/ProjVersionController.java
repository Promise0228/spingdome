package com.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.LogInfo;
import com.entity.ProjVersion;
import com.entity.UserInfo;
import com.service.impl.IProjVersionService;

/**
 * @comment 版本信息控制层
 * @author changjiaqi
 * @date 2018年7月24日 下午10:55:30
 * @version TODO
 */
@Controller
public class ProjVersionController {

	// 注入业务层接口
	@Autowired
	IProjVersionService projVersionService;

	/**
	 * @comment: 分页查询日志列表
	 * @return ModelAndView
	 * @author changjiaqi
	 * @date 2018年7月16日
	 */
	@RequestMapping("/projlist")
	public ModelAndView findProjVersion(HttpServletRequest request,
			ProjVersion projVersion) {
		int projId = Integer.parseInt(request.getParameter("projId"));
		request.setAttribute("projId", projId);
		ModelAndView mod = new ModelAndView("project-list");
		// 获取session中的值
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserInfo");
		mod.addObject("userName", userInfo.getUserName());
		request.getSession().setAttribute("page",
				projVersionService.findProjVersion(request, projVersion));
		return mod;
	}

	/**
	 * @comment: 添加版本信息
	 * @param projVersion
	 * @return JSONObject
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	@RequestMapping("/addProjVersion")
	@ResponseBody
	public JSONObject addProjVersion(HttpServletRequest request,
			ProjVersion projVersion) {
		JSONObject json = new JSONObject();
		json.put("msg", projVersionService.addProjVersion(request, projVersion));
		return json;
	}

	/**
	 * @comment: 修改版本信息
	 * @param projVersion
	 * @return JSONObject
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	@RequestMapping("/updateProjVersion")
	@ResponseBody
	public JSONObject updateProjVersion(ProjVersion projVersion) {
		JSONObject json = new JSONObject();
		json.put("msg", projVersionService.updateProjVersion(projVersion));
		return json;
	}

	/**
	 * @comment: 删除版本信息
	 * @param projVersion
	 * @return JSONObject
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	@RequestMapping("/deleteProjVersion")
	@ResponseBody
	public JSONObject deleteProjVersion(ProjVersion projVersion) {
		JSONObject json = new JSONObject();
		json.put("msg", projVersionService.deleteProjVersion(projVersion));
		return json;
	}

}
