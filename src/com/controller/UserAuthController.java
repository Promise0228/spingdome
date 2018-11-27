package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.AuthInfo;
import com.entity.Role;
import com.entity.UserAuth;
import com.service.AuthInfoService;
import com.service.UserAuthService;
import com.service.impl.IAuthInfoService;
import com.service.impl.IUserAuthService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户权限表
 *@filename UserAuthController.java
 * @author hm
 * @date 2018年7月7日下午5:24:21
 * @version 1.0
 */
@Controller
@RequestMapping("/user-auth")
public class UserAuthController {

	//注入用户权限类的实现类
	@Autowired
	IUserAuthService userAuthService;
	//注入权限类的实现类
	@Autowired
	IAuthInfoService authInfoService;
	
	/**
	 * 
	 *@comment 把角色权限展示到前端
	 *@author HM
	 *@resultType JSONObject
	 */
	@RequestMapping("/showUserAuth")
	public String updateRole(HttpServletRequest request,UserAuth userAuth){
		
		//根据用户id查询当前用户绑定权限 (1,2,3,4)
		String auths1=userAuthService.selectUserAuth(userAuth);
		String authIds=","+auths1+",";
		
		request.setAttribute("userId22", userAuth.getUserId());
		//查询系统中所有有效的权限
		List<AuthInfo> listAuth=authInfoService.selectAuthAll();

		JSONArray jsonArr=new JSONArray();
		//循环遍历权限信息
		for (AuthInfo authInfo : listAuth) {
			JSONObject json = new JSONObject();
			//authId=权限表内的所有权限ID
 			int authId=authInfo.getAuthId();
 			json.put("id",authId);
 			json.put("pId", authInfo.getParentId());
 			json.put("name", authInfo.getAuthName());
 			json.put("open", false);
 			json.put("userId", userAuth.getUserId());
 			json.put("authUrl",authInfo.getAuthUrl() );
 			json.put("authCode",authInfo.getAuthCode());
 			
 				//authId需要转成字符串，用户ID绑定的权限不包含在总权限内，则不打勾
 				if(authIds.indexOf(","+authId+",")==-1){	
 	 				json.put("checked", false);
 	 			}else{
 	 				json.put("checked", true);//包含，打钩
 	 			}
 			jsonArr.add(json);
 			//返回给前端
 			request.setAttribute("jsonArr", jsonArr);
		}
 			return "userAuthTree";
 		}

	/**
	 * 
	 *@comment 根据前端传来的用户ID删除权限ID，然后重新增加
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addUserAuth")
	public JSONObject addRoleAuth(HttpServletRequest request){
		//获取前端传来的角色ID和权限ID
		String userIds=request.getParameter("userId");
		int userId=Integer.parseInt(userIds);
		String authIds=request.getParameter("authId");
		JSONObject json=new JSONObject();
		
		//把string转成数组类型
		String []authId= authIds.split(",");
		HashMap map=new HashMap();
		map.put("userId", userId);
		//根据传来的角色ID删除对应权限
		userAuthService.deleUserAuth(map);
		if(!authIds.equals("")){
		int count=0;
		
		for (String string : authId) {
			map.put("authId", string);
			//给角色新增权限
			count=userAuthService.addUserAuth(map);
		}
		if(count>0){
			json.put("msg", "1");
		}else{
			json.put("msg", "2");
		}
		
		}else{
			json.put("msg", "-1");
		}
		return json;
		
	}
}
