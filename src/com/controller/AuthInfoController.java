package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.AuthInfo;
import com.entity.UserInfo;
import com.service.impl.IAuthInfoService;
/**
 * 
 *@filename AuthInfoController.java
 * @author hm
 * @date 2018年7月4日上午11:25:48
 * @version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthInfoController {

	@Autowired
	IAuthInfoService authInfoService;
	
	/**
	 * 
	 *@comment 权限的展示
	 *@author HM
	 *@resultType String
	 */
	@RequestMapping("/permission-list")
	public String showAuth(HttpServletRequest request){
		//1.定义JSON数组对象
		JSONArray jsonArr=new JSONArray();
		//2.查询当前系统中所有权限
		List<AuthInfo> list=authInfoService.selectAuth();
		//3.循环遍历数组
		for (AuthInfo authInfo : list) {
			JSONObject json=new JSONObject();
			  //id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中      
			json.put("id", authInfo.getAuthId());
			json.put("pId", authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			json.put("open", false);
			json.put("authGrade", authInfo.getAuthGrade());
			json.put("authDesc", authInfo.getAuthDesc());
			json.put("authUrl", authInfo.getAuthUrl());
			json.put("authCode", authInfo.getAuthCode());
			List<AuthInfo> listAuth=authInfoService.selectAuthByIds(authInfo);
			if(listAuth.size()>0){
				json.put("font", "{'color':'red'}");
			}
			jsonArr.add(json);		
		}
		request.setAttribute("authArrays", jsonArr);
		return "authTree";		
	    }

	/**
	 * 
	 *@comment 新增权限
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addAuth")
	public JSONObject  addRole(HttpServletRequest request,AuthInfo authInnfo){
		//Session中获取用户ID作为权限创建人
		UserInfo userInfo=new UserInfo();
		userInfo=(UserInfo) request.getSession().getAttribute("UserInfo");
		JSONObject json=new JSONObject();
		HashMap<String, Object> map=new HashMap<String, Object>();
		//获取权限级别，如果为一级权限，传进来的空需要赋1，否则+1
		String authGrades=request.getParameter("authGrade");		
		int authGrade=0;
		if(authGrades.equals("")){
			authGrade=1;
		}else{
			authGrade=Integer.parseInt(authGrades);
			authGrade+=1;
		}
		//获取的权限级别放入对象
		authInnfo.setAuthGrade(authGrade);
		//把传进来的对象和登录用户的ID放入map，用来新增权限
		map.put("authInnfo", authInnfo);
		map.put("userInfo", userInfo);
		//查询权限名或code有无重复
		List<AuthInfo> list=authInfoService.selectAuthById(map);
		if(list.size()==0){//名字或CODE不重复
			//添加权限
			int count=authInfoService.addAuth(map);
			if(count>0){
				json.put("msg", "1");//添加成功
			}else{
				json.put("msg", "2");//添加失败
			}			
			
	}else{
		json.put("msg", "3");//名字或CODE重复
	}	
		
		return json;
	
	}
	/**
	 * 
	 *@comment 根据传来的数据修改权限
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updateAuth")
	public JSONObject updateAuth(HttpServletRequest request,AuthInfo authInfo){
		JSONObject json=new JSONObject();
		HashMap<String, AuthInfo> map=new HashMap<String, AuthInfo>();
		map.put("authInfo", authInfo);
		//根据传进来的数据修改数据库
		int count=authInfoService.updateAuthInfo(map);
		if(count>0){
			json.put("msg", "1");
		}else{
			json.put("msg", "0");
		}
		return json;
	}
	
	/**
	 * 
	 *@comment 删除权限
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/deleteAuth")
	public JSONObject deleteAuth(HttpServletRequest request,AuthInfo authInfo){
		JSONObject json=new JSONObject();
		//根据传进来的权限ID查询有无数据
		List<AuthInfo> list=authInfoService.selectAuthsById(authInfo);
		if(list.size()<1){
			//根据权限ID删除权限信息
			int count=authInfoService.deleAuth(authInfo);
			if(count>0){
				json.put("delmsg", "1");//删除成功
			}else{
				json.put("delmsg", "2");//删除失败
			}
		}else{
			json.put("delmsg", "3");//传过来的权限下边有权限，不能删除
		}
		return json;
	}
	
	//恢复权限
	@ResponseBody
	@RequestMapping("/resumeAuth")
	public JSONObject resumeAuth(HttpServletRequest request,AuthInfo authInfo){
		JSONObject json=new JSONObject();
		int count=0;
		//根据传来的权限ID查询全部权限信息，需要父ID
		List<AuthInfo> list=authInfoService.selectAuthByAuthId(authInfo);
		AuthInfo authInfo2=new AuthInfo();
		int authGrade=0;
		//遍历根据传过来的ID查询的全部权限
		for (AuthInfo authInfo1 : list) {
			authInfo2=authInfo1;
			//获取权限级别
			authGrade=authInfo1.getAuthGrade();
		}
			//如果是一级权限，则直接恢复
			if(authGrade==1){
				count=authInfoService.resumeAuth(authInfo);
				json.put("msg", "1");//恢复成功
			}else{
			List<AuthInfo> list1=authInfoService.selectAuthByAuthIds(authInfo2);
			if(list1==null || list1.size()==0){//父级无权限
				count=authInfoService.resumeAuth(authInfo);
				if(count>0){
					json.put("msg", "1");//恢复成功
				}else{
					json.put("msg", "2");//恢复失败
				}
			}else{
				json.put("msg", "3");//父级有未恢复权限
			}
			}
		return json;
	}
	
	
}
