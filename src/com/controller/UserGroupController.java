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
import com.entity.UserGroup;
import com.service.GroupRoleService;
import com.service.UserGroupService;
import com.service.UserRoleService;
import com.service.impl.IUserGroupService;
import com.service.impl.IUserRoleService;
import com.util.PageBean;

/**
 * 用户组管理
 *@filename UserGroupController.java
 * @author hm
 * @date 2018年7月9日下午5:13:42
 * @version 1.0
 */
@RequestMapping("/usergroup-list")
@Controller
public class UserGroupController {

	@Autowired
	IUserGroupService userGroupService;
	@Autowired
	IUserRoleService roleService;
	
	/**
	 * 用户组跳转+分页
	 *@comment 
	 *@author HM
	 *@resultType ModelAndView
	 */
	@RequestMapping("/list")
	public ModelAndView roleList(HttpServletRequest request,UserGroup userGroup){
		HashMap map=new HashMap();
		String page1=(request.getParameter("page")==null?"5":request.getParameter("page"));
		String currPage1=(request.getParameter("currNo")==null?"1":request.getParameter("currNo"));
		Integer page=Integer.parseInt(page1);
		Integer currNo=Integer.parseInt(currPage1);
		PageBean pageBean=new PageBean(page,currNo);
		ModelAndView mo=new ModelAndView("/usergroup-list");
		map.put("userGroup", userGroup);
		map.put("pageBean", pageBean);
		//查询组的所有信息，返回集合resultList
		List<UserGroup> list=userGroupService.selectUserGroup();
		Integer totalNum=0;
		//用户组总条数
		totalNum=userGroupService.countUserGroup(map);
		String url="/usergroup-list/list.action";
		String params="";
		if(userGroup.getGroupName()!=null){
			params=params+"&groupName="+userGroup.getGroupName();
		}else if(userGroup.getGroupState()!=null){
			params=params+"&groupState="+userGroup.getGroupState();
		}
	
		PageBean userGroups=new PageBean(page,totalNum,currNo,list,url,params.toString());
		
		List<Role> listRole=roleService.selectRoleName();
		
		request.getSession().setAttribute("listRole", listRole);
		request.getSession().setAttribute("page", userGroups);
		return mo;
	}

	/**
	 * 
	 *@comment 新增用户组
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addUserGroup")
	public JSONObject addRole(UserGroup userGroup){
		JSONObject json=new JSONObject();
		//根据传入的组名字查询组code和name是否有重复组
		List<UserGroup> rolelist=userGroupService.selectUserGroupByCode(userGroup);
		int count=0;
		if(rolelist.size()==0){
			count=userGroupService.addUserGroup(userGroup);//返回结果是0则插入失败，1插入成功
		if(count>0){
			json.put("msg", "1");//添加成功
		}else{
			json.put("msg","2" );//添加失败
		}
		}else{
			json.put("msg", "3");//用户名或code重复
		}
		return json;
	}
	
	/**
	 * 
	 *@comment 启用禁用
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/startuserGroup")
	public JSONObject restart(UserGroup userGroup){
		JSONObject json=new JSONObject();		
		if("启用".equals(userGroup.getGroupState())){
			userGroup.setGroupState("0");
			userGroupService.restart(userGroup);
			json.put("remsg", "0");//禁用成功
		}else if("禁用".equals(userGroup.getGroupState())){
			userGroup.setGroupState("1");
			userGroupService.restart(userGroup);
			json.put("remsg", "1");//启用成功
		}else{
			json.put("remsg", "-1");//传值错误
		}
		return json;
	}
	
	
	
}
