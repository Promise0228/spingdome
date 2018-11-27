/**
 * 
 */
package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.ProjectInfo;
import com.entity.ProjDynamic;
import com.entity.SysClass;
import com.entity.UserInfo;
import com.service.ProjectInfoService;
import com.service.UserInfoService;
import com.service.impl.IProjectInfoService;
import com.service.impl.IUserInfoService;
import com.util.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *@comment 项目列表
 *@author Administrator
 *@date 2018年7月19日 上午11:10:09
 *@modifyUser Administrator
 *@modifyDate 2018年7月19日 上午11:10:09
 *@modifyDesc  TODO
 *@version 1.0
 */
@Controller
@RequestMapping("/proj")
public class projectController {
    @Autowired
	IProjectInfoService projService;
    @Autowired
    IUserInfoService iUserInfoService; 
    /**
	* 
	*@comment 展示项目列表主页面 
	*@author Administrator
	*@date 2018年7月19日 上午11:13:46
	*@return ModelAndView
	*@version 1.0
	*/
	@RequestMapping("/proj-list")
	public ModelAndView showProList(HttpServletRequest request,ProjectInfo proj,PageBean page){
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
		map.put("userId",user.getUserId());
		map.put("proj", proj);
		StringBuffer sb = new StringBuffer();
		sb.append("&projName="+(StringUtils.isNotBlank(proj.getProjName())?proj.getProjName():""));
		sb.append("&projState="+(StringUtils.isNotBlank(proj.getProjState())?proj.getProjState():""));
		sb.append("&createTime="+(StringUtils.isNotBlank(proj.getCreateTime())?proj.getCreateTime():""));
		page.setUrl("/proj/proj-list.action");
		page.setParams(sb.toString());
		page.setTotalNum(projService.getProjsNumber(map));
		map.put("page",page);
		page.setResultList(projService.findProjs(map));
		ModelAndView mv = new ModelAndView("proj-list");
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 *@comment ajax返回查询出的项目经理
	 *@author Administrator
	 *@date 2018年7月20日 下午3:34:13
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("/findProjManagers")
	@ResponseBody
	public JSONObject findProjManagers(){
		List<UserInfo> projManagers = projService.findProjManagers();
		StringBuffer sbName = new StringBuffer();
		StringBuffer sbId = new StringBuffer();
		for(UserInfo projManager :projManagers){
			sbName.append(projManager.getUserName()+",");
			sbId.append(projManager.getUserId()+",");
		}
		JSONObject json = new JSONObject();
		json.put("userName", sbName.toString());
		json.put("userId", sbId.toString());
		return json;	
	}
	
	/** 
	 *@comment 添加项目
	 *@author Administrator
	 *@date 2018年7月20日 下午11:18:34
	 *@param request
	 *@param proj
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("/addProj")
	@ResponseBody
	public JSONObject addProj(HttpServletRequest request,ProjectInfo proj){
		
		HashMap map = new HashMap();
		map.put("projName", proj.getProjName());
		map.put("projNum", proj.getProjNum());
		projService.addSysClass(map);
		SysClass sysClass= projService.getSysClassByProj(proj);
		proj.setClassId(sysClass.getClassId());
		projService.addProj(request, proj);
		JSONObject json = new JSONObject();
		json.put("res", "ok");
		return json;
	}
	
	/**
	 * 
	 *@comment 项目名及项目编码查重
	 *@author Administrator
	 *@date 2018年7月21日 上午9:28:39
	 *@param proj
	 *@return JSONObject
	 *@version 1.0
	 */
	@RequestMapping("/checkProj")
	@ResponseBody
	public JSONObject checkProj(ProjectInfo proj){
		JSONObject json = new JSONObject();
		int num = projService.checkProj(proj);
		if(num>0){
			json.put("res", false);
		}else{
			json.put("res", true);
		}
		return json;
	}
	
	/**
	 *@comment 展示项目详情
	 *@author Administrator
	 *@date 2018年7月21日 上午9:30:38
	 *@param proj
	 *@return
	 *ModelAndView
	 *@version 1.0
	 */
	@RequestMapping("/projDetail")
	public ModelAndView projDetail(ProjectInfo proj){
		//当前项目信息
		ProjectInfo projGet= projService.getProjById(proj);
		//当前项目对应项目组信息
		List<UserInfo> projUsers=iUserInfoService.findProjUsers(proj);
		//当前项目对应项目白名单信息
		List<UserInfo> projWhite=iUserInfoService.findProjWhite(proj);
		//查询项目创建人
		UserInfo user = new UserInfo();
		user.setUserId(projGet.getCreateBy());
		UserInfo projCreater=iUserInfoService.getUserById(user); 
		//查询项目负责人
		user.setUserId(projGet.getProjChief());
		UserInfo projChief=iUserInfoService.getUserById(user);
		//查询项目已消耗时间
		int expendTime = projService.getProjExpend(projGet);
		//计算项目可用工时
		float usableTime = (float) (projGet.getAbleDay()-expendTime);
		//计算项目进度（百分比）
		int process = (int) (expendTime/(projGet.getAbleDay())*100);
		ModelAndView mv = new ModelAndView("proj-detail");
		mv.addObject("proj",projGet);
		mv.addObject("projUsers",projUsers);
		mv.addObject("projWhite",projWhite);
		mv.addObject("projCreater",projCreater);
		mv.addObject("projChief",projChief);
		mv.addObject("expendTime",expendTime);
		mv.addObject("usableTime",usableTime);
		mv.addObject("process",process);
		return mv;
	}
	
	/**
	 * 
	 *@comment 白名单页面
	 *@author Administrator
	 *@date 2018年7月21日 下午3:02:25
	 *@param proj
	 *@param user
	 *@return
	 *ModelAndView
	 *@version 1.0
	 */
	@RequestMapping("/whiteSheet")
	public ModelAndView WhiteSheet(ProjectInfo proj,UserInfo user,PageBean page){
		StringBuffer sb = new StringBuffer();
		sb.append("&projId="+proj.getProjId());
		sb.append("&userName="+(StringUtils.isNotBlank(user.getUserName())?user.getUserName():""));
		page.setUrl("/proj/whiteSheet.action");
		page.setParams(sb.toString());
		page.setTotalNum(iUserInfoService.getUserAddNumber(proj, user));
		page.setResultList(iUserInfoService.findUserAdd(proj, user,page));
		ModelAndView mv = new ModelAndView("white-sheet");
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 *@comment 查看白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午11:29:01
	 *@param proj
	 *@param page
	 *@return ModelAndView
	 *@version 1.0
	 */
	@RequestMapping("/selectWhiteSheet")
	@ResponseBody
	public JSONArray selectWhiteSheet(ProjectInfo proj){
		List<UserInfo> users = iUserInfoService.findProjWhite(proj);
		JSONArray jsonArr = new JSONArray();
		for(UserInfo user :users){
			JSONObject json = new JSONObject();
			json.put("userId", user.getUserId());
			json.put("userName", user.getUserName());
			jsonArr.add(json);
		}
		return jsonArr;
	}
	
	@RequestMapping("/updateWhiteSheet")
	@ResponseBody
	public JSONObject updateWhiteSheet(HttpServletRequest request){
		String userIds= request.getParameter("userIds");
		String projId=request.getParameter("projId");
		ProjectInfo proj = new ProjectInfo();
		proj.setProjId(Integer.parseInt(projId));
		projService.delWhiteSheet(proj);
		projService.addWhiteSheet(userIds, projId);
		return new JSONObject();
	}
	/**
	 *@comment 添加白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午11:28:11
	 *@param request
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("/addWhiteSheet")
	@ResponseBody
	public JSONObject addWhiteSheet(HttpServletRequest request){
		String userIds= request.getParameter("userIds");
		String projId=request.getParameter("projId");
		projService.addWhiteSheet(userIds, projId);
		return new JSONObject();
	}
	
	/**
	 *@comment 修改时回显
	 *@author Administrator
	 *@date 2018年7月24日 下午5:54:16
	 *@param proj
	 *@return JSONObject
	 *@version 1.0
	 */
	@RequestMapping("/updateProj")
	@ResponseBody
	public JSONObject updateProj(ProjectInfo proj){
		ProjectInfo projGet=projService.getProjById(proj);
		JSONObject json = JSONObject.fromObject(projGet);
		return json;
	}
	
	/**
	 *@comment 提交修改项目信息
	 *@author Administrator
	 *@date 2018年7月24日 下午10:51:00
	 *@param proj
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("/confirmUpdateProj")
	@ResponseBody
	public JSONObject confirmUpdateProj(ProjectInfo proj){
		projService.updateProj(proj);
		return new JSONObject();
	}
	
	/**
	 *@comment 展示项目动态
	 *@author Administrator
	 *@date 2018年7月24日 下午10:51:45
	 *@return
	 *ModelAndView
	 *@version 1.0
	 */
	@RequestMapping("/dynamicList")
	public ModelAndView dynamic(ProjDynamic projDyna,PageBean page){
		page.setUrl("/proj/dynamicList.action");
		StringBuffer sb = new StringBuffer();
		sb.append("&createTime="+(StringUtils.isNotBlank(projDyna.getCreateTime())?projDyna.getCreateTime():""));
		sb.append("&userName="+(StringUtils.isNotBlank(projDyna.getUserName())?projDyna.getUserName():""));
		page.setParams(sb.toString());
		page.setTotalNum(projService.getProjDynasNum(projDyna));
		List<ProjDynamic> projDynas=projService.findProjDynas(projDyna, page);
		page.setResultList(projDynas);
		ModelAndView mv = new ModelAndView("dynamic");
		mv.addObject("page",page);
		return mv;
	}
	
	@RequestMapping("/addReply")
	@ResponseBody
	public JSONObject addReply(HttpServletRequest request,ProjDynamic projDyna){
		UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
		projService.addProjDynamic(user, projDyna);
		return new JSONObject();
		
	}
}
