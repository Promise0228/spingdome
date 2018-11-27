package com.controller;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.SysClass;
import com.service.impl.ISysClassService;

@Controller
@RequestMapping("/sysclass")
public class SysClassController {

	@Autowired
	ISysClassService sysClassService;

	/**
	 * @comment 系统分类的展示
	 * @author HM
	 * @resultType String
	 */
	@RequestMapping("/sysClassList")
	public String sysClass(HttpServletRequest request, @ModelAttribute("sysclass") SysClass sysclass) {
		// 1.定义JSON数组对象
		JSONArray jsonArr = new JSONArray();
		// 2.查询当前系统中所有分类
		List<SysClass> list = sysClassService.selectSysClass();
		// 3.循环遍历数组
		for (SysClass sysClass : list) {
			JSONObject json = new JSONObject();
			// id 标识 ，pId 父id，name 名称，open 是否展开节点， checked 是否选中
			json.put("id", sysClass.getClassId());
			json.put("pId", sysClass.getParentId());
			json.put("name", sysClass.getClassName());
			json.put("classDesc", sysClass.getClassDesc());
			json.put("sysType", sysClass.getSysType());
			json.put("grade", sysClass.getGrade());
			json.put("classCode", sysClass.getClassCode());
			json.put("open", false);
			jsonArr.add(json);
		}
		request.setAttribute("sysClass", jsonArr);
		return "system-Tree";
	}

	/**
	 * 
	 * @comment 新增分类系统
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addClass")
	public JSONObject addClass(HttpServletRequest request, SysClass sysClass) {

		JSONObject json = new JSONObject();
		HashMap map = new HashMap();
		// 获取权限级别，如果为一级权限，传进来的空需要赋1，否则+1
		String grade = request.getParameter("grade");
		int sysGrade = 0;
		if ("".equals(grade)) {
			sysGrade = 1;
		} else {
			sysGrade = Integer.parseInt(grade) + 1;
		}
		// 获取的权限级别放入对象

		// 把传进来的对象和登录用户的ID放入map，用来新增权限
		map.put("sysClass", sysClass);
		map.put("grade", sysGrade);
		// 查询权限名或code有无重复
		List<SysClass> list = sysClassService.findSysClass(map);
		if (list.size() == 0) {// 名字或CODE不重复
			// 添加权限
			int count = sysClassService.addClass(map);
			if (count > 0) {
				json.put("msg", "1");// 添加成功
			} else {
				json.put("msg", "2");// 添加失败
			}

		} else {
			json.put("msg", "3");// 名字或CODE重复
		}

		return json;

	}

	/**
	 * 
	 * @comment 根据传来的数据修改项目
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updateClass")
	public JSONObject updateAuth(HttpServletRequest request, SysClass sysClass) {
		JSONObject json = new JSONObject();

		// 根据传进来的数据修改数据库
		int count = sysClassService.updateSysClass(sysClass);
		if (count > 0) {
			json.put("msg", "1");
		} else {
			json.put("msg", "0");
		}
		return json;
	}

}
