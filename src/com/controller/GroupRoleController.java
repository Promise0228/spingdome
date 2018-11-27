package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.GroupRole;
import com.entity.Role;
import com.service.GroupRoleService;
import com.service.UserRoleService;
import com.service.impl.IGroupRoleService;
import com.service.impl.IUserRoleService;

/**
 * 组角色
 *@filename GroupRoleController.java
 * @author hm
 * @date 2018年7月11日上午8:14:51
 * @version 1.0
 */
@Controller
@RequestMapping("/usergroup-list")
public class GroupRoleController {

	@Autowired
	IGroupRoleService groupRoleService;
	@Autowired
	IUserRoleService roleService;
	
	/**
	 * 
	 *@返回类型：JSONObject
	 *@方法功能：根据前台传来的用户ID查询角色ID并返回给前台
	 */
	@ResponseBody
	@RequestMapping("/selectGroupRole")
	public JSONObject selectRoleName(HttpServletRequest request,GroupRole groupRole){
		JSONObject json=new JSONObject();		
		//根据前台传的ID查询用户角色id
		String roleIdS="";
		roleIdS=groupRoleService.selectGroupRoleById(groupRole);
		if(roleIdS!=null){
			json.put("roleid", roleIdS);			
		}
		return json;
	}
	
	/**
	 * 
	 *@返回类型：JSONObject
	 *@方法功能：根据前台传来的组ID 删除用户角色并重新新增角色
	 */
	@ResponseBody
	@RequestMapping("/insertRoleId")
	public JSONObject selectRoleName(HttpServletRequest request,Long...roleIds){
		String groupId1=request.getParameter("groupId");
		int groupId=Integer.parseInt(groupId1);
		HashMap map=new HashMap();
		map.put("groupId", groupId);
		
		JSONObject json=new JSONObject();
		groupRoleService.deleGroupRoleId(map);
		int count=0;
		//遍历数组
		for (Long long1 : roleIds) {
			map.put("roleId", long1);		
		 count=count+groupRoleService.addGroupRoleId(map);
		}			
		if(count>0){
			json.put("assign", "1");
		}else{
			json.put("assign", "2");
		}
		return json;	
	}
	
	
}
