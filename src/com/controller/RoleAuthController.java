package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.RoleAuthService;
import com.service.impl.IRoleAuthService;

/**
 * 角色权限表
 *@filename RoleAuthController.java
 * @author hm
 * @date 2018年7月7日下午12:12:39
 * @version 1.0
 */

@Controller
@RequestMapping("/role-Auth")
public class RoleAuthController {

	@Autowired
	IRoleAuthService  roleAuthService;
	
	
	/**
	 * 
	 *@comment 根据前端传来的角色ID删除权限ID，然后重新增加
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addRoleAuth")
	public JSONObject addRoleAuth(HttpServletRequest request){
		//获取前端传来的角色ID和权限ID
		String roleIds=request.getParameter("roleId");
		int roleId=Integer.parseInt(roleIds);
		HashMap map=new HashMap();
		JSONObject json=new JSONObject();
		String authIds=request.getParameter("authId");
		map.put("roleId", roleId);
		//根据传来的角色ID删除对应权限
		roleAuthService.deleRoleAuth(map);
		//把string转成数组类型
		String []authId= authIds.split(",");
		if(!"".equals(authIds)){
		int count=0;
		for (String string : authId) {
			map.put("authId", Integer.parseInt(string));
			//给角色新增权限
			count=roleAuthService.addRoleAuth(map);
		}
		if(count>0){
			json.put("msg", "1");
		}else{
			json.put("msg", "2");
		}
		}else{
			json.put("msg", "1");
		}
		return json;
	
	}
	
	
	
	
	
}
