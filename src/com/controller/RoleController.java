package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.AuthInfo;
import com.entity.Role;
import com.service.AuthInfoService;
import com.service.RoleService;
import com.service.impl.IAuthInfoService;
import com.service.impl.IRoleService;
import com.util.PageBean;

/**
 * 角色表
 *@filename RoleController.java
 * @author hm
 * @date 2018年7月5日下午6:49:48
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	IRoleService roleService;

	@Autowired
	IAuthInfoService authInfoService;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date birthday; 
	
	/**
	 *  时间格式转换
	 *@comment 
	 *@author HM
	 *@resultType void
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		    dateFormat.setLenient(false);  
		    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

	
	/**
	 * 角色跳转+分页
	 *@comment 
	 *@author HM
	 *@resultType ModelAndView
	 */
	@RequestMapping("/rolelist")
	public ModelAndView roleList(HttpServletRequest request,@ModelAttribute("role")Role role){
		HashMap map=new HashMap();
		String page1=(request.getParameter("page")==null?"5":request.getParameter("page"));
		String currPage1=(request.getParameter("currNo")==null?"1":request.getParameter("currNo"));
	
		// 每页显示条数
		Integer page=Integer.parseInt(page1);
		// 当前页码
		Integer currNo=Integer.parseInt(currPage1);
		//下标=每页显示条数*（当前页码-1）
		PageBean pageBean=new PageBean(page,currNo);
		ModelAndView mo=new ModelAndView("role-list");
		map.put("role", role);
		String createTime2=request.getParameter("createTime2")+"";
		map.put("pageBean", pageBean);
		map.put("createTime2", createTime2);
		List<Role> list=roleService.findRoleAll(map);
		Integer totalNum=0;
		// 总条数
		totalNum=roleService.countRoleAll(map);
		String url="/role/rolelist.action";
		String params="";
		if(role.getRoleName()!=null){
			params=params+"&roleName="+role.getRoleName();
		}else if(role.getRoleState()!=null){
			params=params+"&roleState="+role.getRoleState();
		}else if(role.getCreateTime()!=null && role.getCreateTime().equals("")){
			params=params+"&createTime="+role.getCreateTime();
		}else if(createTime2!=null && createTime2.equals("")){
			params=params+"&createTime2="+createTime2;
		}
		
		PageBean roles=new PageBean(page,totalNum,currNo,list,url,params.toString());
		
		request.getSession().setAttribute("page", roles);
		return mo;
	}
	
	/**
	 * 
	 *@comment 根据角色ID查询角色信息
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/findRoleById")
	public JSONObject findRoleById(Role role){
		JSONObject json=new JSONObject();
		Role rolelist=roleService.findRoleById(role);
		if(rolelist!=null){
		json.put("roleName", rolelist.getRoleName());
		json.put("roleDesc", rolelist.getRoleDesc());
		json.put("roleCode", rolelist.getRoleCode());
		}else{
			json.put("roleName", "权限名称");
			json.put("roleDesc", "权限描述");
			json.put("roleCode", "权限code");
		}
		return json;
	}
	
	
	/**
	 * 
	 *@comment 修改角色信息
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/refushRole")
	public JSONObject refushRole(Role role){
		JSONObject json = new JSONObject();
		int x=0;
		x=roleService.refushRole(role);//更新角色信息
		return json;
	}
	
	
	/**
	 * 
	 *@comment 新增角色 
	 *@author HM
	 *@resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addRole")
	public JSONObject addRole(Role role){
		JSONObject json=new JSONObject();
		List<Role> rolelist=roleService.findRoleByCode(role);//根据角色code和name查询是否有重复角色
		int addmsg=0;
		if(rolelist.size()==0){
			addmsg=roleService.addRole(role);//返回结果是0则插入失败，1插入成功
			json.put("addmsg", addmsg);
		}else{
			json.put("addmsg", "-1");//用户名重复
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
	@RequestMapping("/restartRole")
	public JSONObject restart(Role role){
		JSONObject json=new JSONObject();		
		//System.out.println(role.getRoleState()+";"+role.getRoleId());
		if(role.getRoleState().equals("启用")){
			role.setRoleState("0");
			roleService.restart(role);
			json.put("remsg", "0");//禁用成功
		}else if(role.getRoleState().equals("禁用")){
			role.setRoleState("1");
			roleService.restart(role);
			json.put("remsg", "1");//启用成功
		}else{
			json.put("remsg", "-1");//传值错误
		}
		return json;
	}
	
	/**
	 * 
	 *@comment 把角色权限展示到前端
	 *@author HM
	 *@resultType JSONObject
	 */
	@RequestMapping("/updateRole")
	public String updateRole(HttpServletRequest request,Role role){
		//根据角色id查询当前角色绑定权限 (1,2,3,4)
		String roleIds1=roleService.selectRoleById(role);
		
		request.setAttribute("roleid", role.getRoleId());
		
		String roleIds=","+roleIds1+",";
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
 			json.put("roleId", role.getRoleId());
 			json.put("authUrl",authInfo.getAuthUrl() );
 			json.put("authCode",authInfo.getAuthCode());
 			//如果有给传来的用户ID赋权限，则返回的权限ID不为null
 			
 				//authId需要转成字符串，用户ID绑定的权限不包含在总权限内，则不打勾
 				if(roleIds.indexOf(","+authId+",")==-1){	
 	 				json.put("checked", false);
 	 			}else{
 	 				json.put("checked", true);//包含，打钩
 	 			}
 			jsonArr.add(json);
 			//返回给前端
 			request.setAttribute("jsonArr", jsonArr);
		}
 			return "roleAuthTree";
 		}


	
	
}
