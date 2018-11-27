package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Role;
import com.entity.UserRole;
import com.service.UserRoleService;
import com.service.impl.IUserRoleService;

/**
 * 用户角色类
 * 
 * @filename RoleController.java
 * @author hm
 * @date 2018年7月3日下午4:05:24
 * @version 1.0
 */

@Controller
@RequestMapping("/userRole")
public class UserRoleController {

	@Autowired
	IUserRoleService roleService;

	/**
	 * 
	 * @返回类型：JSONObject
	 * @方法功能：根据前台传来的用户ID查询角色ID并返回给前台
	 */
	@ResponseBody
	@RequestMapping("/selectRoleName")
	public JSONObject selectRoleName(HttpServletRequest request,
			UserRole userRole) {
		JSONObject json = new JSONObject();
		// 根据前台传的ID查询用户角色
		String roleIdS = "";
		roleIdS = roleService.selectRoleNameById(userRole);
		if (roleIdS != null) {
			json.put("roleid", roleIdS);
		}
		return json;
	}

	/**
	 * 
	 * @返回类型：JSONObject
	 * @方法功能：根据前台传来的用户ID 删除用户角色并重新新增角色
	 */
	@ResponseBody
	@RequestMapping("/insertRoleId")
	public JSONObject selectRoleName(HttpServletRequest request,
			Long... roleIds) {
		String UserId = request.getParameter("userId");
		int userId = Integer.parseInt(UserId);
		HashMap map = new HashMap();
		map.put("userId", userId);

		JSONObject json = new JSONObject();
		roleService.deleUserRoleId(map);
		int count = 0;
		// 遍历数组
		for (Long long1 : roleIds) {
			map.put("roleId", long1);
			count = count + roleService.addUserRoleId(map);
		}
		if (count > 0) {
			json.put("assign", "1");
		} else {
			json.put("assign", "2");
		}
		return json;
	}

}
