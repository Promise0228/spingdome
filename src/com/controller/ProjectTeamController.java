package com.controller;
/**
 * 项目组管理
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.ProjTask;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.TeamMember;
import com.entity.UserInfo;
import com.service.impl.IProjectTeamService;
import com.util.PageBean;


@Controller
@RequestMapping("projectTeam")
public class ProjectTeamController {

	@Autowired IProjectTeamService iProjectTeamService;
	
	//页面跳转
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,ProjTeam projTeam){
		ModelAndView mov = new ModelAndView("projectTeam");
		Map map=new HashMap();
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("UserInfo");
		//用户ID
		int userId=userInfo.getUserId();
		map.put("userId", userId);
		List<Role> list1=iProjectTeamService.getRole(userId);
		for (Role role : list1) {
			//角色id，当角色为项目经理或项目组长时使用
			map.put("roleId", role.getRoleId());
		}
		//根据角色id及用户id查询用户所在的项目
		List<ProjectInfo> list3=iProjectTeamService.findProjInfo(map);
		String page1=(request.getParameter("page")==null?"5":request.getParameter("page"));
		String currPage1=(request.getParameter("currNo")==null?"1":request.getParameter("currNo"));
		// 每页显示条数
		Integer page=Integer.parseInt(page1);
		// 当前页码
		Integer currNo=Integer.parseInt(currPage1);
		//下标=每页显示条数*（当前页码-1）
		PageBean pageBean=new PageBean(page,currNo);
		map.put("projTeam", projTeam);
		map.put("pageBean", pageBean);
		//查询项目组信息
		List<ProjTeam> list=iProjectTeamService.findProjTeamAll(map);
		System.out.println(list.toString());
		Integer totalNum=0;
		// 总条数
		if(iProjectTeamService.findCountProjTeamAll(map)!=null){
		totalNum=iProjectTeamService.findCountProjTeamAll(map);
		}
		String url="/projectTeam/list.action";
		StringBuffer params=new StringBuffer();
		if(projTeam.getProjId()!=null && projTeam.getProjId().equals("")){
			//项目
			params.append("&projId="+projTeam.getProjId());
		}else if(projTeam.getTeamName()!=null){
			//项目组名称
			params.append("&teamName="+projTeam.getTeamName());
		}else if(projTeam.getProjChief()!=null){
			//负责人
			params.append("&projChief="+projTeam.getProjChief());
		}
		PageBean pages=new PageBean(page,totalNum,currNo,list,url,params.toString());
		
		request.getSession().setAttribute("page", pages);
		mov.addObject("projInfo",list3);
		return mov;
	}
	/**
	 * 获取项目信息
	 * @return
	 */
	@RequestMapping("findProjectInfo")
	public JSONObject findProjectInfo(HttpServletRequest request,ProjTeam projTeam){
		JSONObject json = new JSONObject();
		//根据用户id查询用户角色
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("userInfo");
		int userId=userInfo.getUserId();
		Map map = new HashMap();
		map.put("userId", userId);
		//查询用户角色
		List<Role> list=iProjectTeamService.getRole(userId);
		String code="";
		for (Role role : list) {
			code= role.getRoleCode();
			//将角色id传入map
			map.put("roleId", role.getRoleId());
		}
		
		return json;
	}
	
	
	@RequestMapping("findUserByProjId")
	@ResponseBody
	public JSONObject findUserByProjId(HttpServletRequest request,ProjectInfo projectInfo){
		JSONObject json = new JSONObject();
		System.out.println(projectInfo.getProjId());
		//查询项目下所有人员
		List<UserInfo> list=iProjectTeamService.findUserByProjId(projectInfo);
		json.put("users", list);
		return json;
	}
	
	
	/**
	 * 创建项目组
	 * @param request
	 * @param projTeam
	 * @return
	 */
	@RequestMapping("insertProjTeam")
	@ResponseBody
	public JSONObject insertProjTeam(HttpServletRequest request,ProjTeam projTeam){
		JSONObject json = new JSONObject();
		//查询项目组名称是否存在
		int i=iProjectTeamService.getCountTeam(projTeam);
		if(i>0){
			//用户名已存在
			json.put("res", "2");
		}else{
		//创建项目组
		int a = iProjectTeamService.insertProjTeam(projTeam);
		if(a>0){
			//创建成功
			json.put("res", "1");
		}else{
			//创建失败
			json.put("res", "0");
		}
	}
		return json;
	}
	
	/**
	 * 查询项目组成员
	 * @param request
	 * @param projTeam
	 * @return
	 */
	@RequestMapping("findTeamUser")
	public ModelAndView findTeamUser(HttpServletRequest request,ProjTeam projTeam){
		ModelAndView mov = new ModelAndView("teamUser");
		UserInfo userInfo=new UserInfo();
		if(request.getParameter("userName")!=null && request.getParameter("userName")!=""){
		userInfo.setUserName(request.getParameter("userName"));
		}
		//查询开发部成员（非项目组长，非项目经理）
		List<UserInfo> development=iProjectTeamService.findUserBy();
		Map map=new HashMap();
		map.put("userInfo", userInfo);
		if(request.getParameter("teamId")!=""&&request.getParameter("teamId")!=null){
			int teamId =Integer.parseInt(request.getParameter("teamId"));
			mov.addObject("teamId",teamId);
		}
		
		String page1=(request.getParameter("page")==null?"5":request.getParameter("page"));
		String currPage1=(request.getParameter("currNo")==null?"1":request.getParameter("currNo"));
		// 每页显示条数
		Integer page=Integer.parseInt(page1);
		// 当前页码
		Integer currNo=Integer.parseInt(currPage1);
		//下标=每页显示条数*（当前页码-1）
		PageBean pageBean=new PageBean(page,currNo);
		map.put("projTeam", projTeam);
		map.put("pageBean", pageBean);
		//查询用户组成员
		List<UserInfo> findUser=iProjectTeamService.findUserByProjTeamId(map);
		mov.addObject("projTeam", findUser);
		mov.addObject("groupUser",development);
		Integer totalNum=0;
		// 总条数
		totalNum=iProjectTeamService.findCountTeamAll(map);
		String url="/projectTeam/list.action";
		StringBuffer params=new StringBuffer();
		if(userInfo.getUserName()!=null&&userInfo.getUserName()!=""){
			params.append(userInfo.getUserName());
		}
		if(projTeam.getTeamId()!=null){
			params.append(projTeam.getTeamId());
		}
		PageBean pageBeans=new PageBean(page,totalNum,currNo,findUser,url,params.toString());
		request.getSession().setAttribute("page", pageBeans);
		return mov;
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	/**
	 * 添加用户组成员
	 * @param request
	 * @param teamMember
	 * @return
	 */
	@RequestMapping("addTeamUser")
	@ResponseBody
	public JSONObject addTeamUser(HttpServletRequest request,TeamMember teamMember ){
		JSONObject json = new JSONObject();
		int i=iProjectTeamService.addTeamUser(teamMember);
		System.out.println("i"+i);
		if(i>0){
			//添加成功
			json.put("res", "1");
		}else{
			//添加失败
			json.put("res", "0");
		}
		return json;
	}
	
	
	
	@RequestMapping("deleteTeamMember")
	@ResponseBody
	public JSONObject deleteTeamMember(HttpServletRequest request,TeamMember teamMember ){
		JSONObject json = new JSONObject();
		int i=iProjectTeamService.deleteTeamUser(teamMember);
		System.out.println("i"+i);
		if(i>0){
			//删除成功
			json.put("res", "1");
		}else{
			//删除失败
			json.put("res", "0");
		}
		return json;
	}
	
	
	/**
	 * 查询分配人员所在用户组之外的用户组
	 */
	@RequestMapping("selectProjTask")
	@ResponseBody
	public JSONObject selectProjTask(HttpServletRequest request,UserInfo userInfo ){
		JSONObject json = new JSONObject();
		List<ProjTeam> list=iProjectTeamService.selectProjTask(userInfo);
		json.put("projTeams", list);
		return json;
	}
	
	
	
	/**
	 * 人员分配
	 */
	@RequestMapping("findTaskId")
	@ResponseBody
	public JSONObject findTaskId(HttpServletRequest request,UserInfo userInfo ){
		JSONObject json = new JSONObject();
		//被调配人员id
		int userIds=Integer.parseInt(request.getParameter("userIds"));
		//调配项目组
		int teamIds=Integer.parseInt(request.getParameter("teamIds"));
		//项目经理id
		int userId=userInfo.getUserId();
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("excutor", userIds);
		map.put("teamIds", teamIds);
		
		//查询成员执行任务状态
		List<ProjTask> list=iProjectTeamService.findTaskId(map);
		if(list.size()>0){
		System.out.println("list+"+list.toString());
		//遍历成员项目任务
		for (ProjTask projTask : list) {
			//获得任务id
			int taskId=projTask.getTaskId();
			//获得任务状态
			String taskState=projTask.getTaskState();
			//获得任务进度
			String process=projTask.getProcess()+"";
			//获得实际消耗时间
			String finishTime=projTask.getFinishTime()+"";
			//判断任务状态是否为进行中，如果是，执行改变状态操作
			if(taskState.equals('2')){
				//判断任务进度和实际消耗时间是否为空
				if(process!=null&&process!=""  &&  finishTime!=null&&finishTime!=""){
					//任务进度和实际消耗时间已更新
					//修改状态
					if(iProjectTeamService.updateTaskState(taskId)>0){
						//将成员任务执行人修改为空
						if(iProjectTeamService.updateExcutor(taskId)>0){
							//修改执行人成功、修改项目成员表
							if(iProjectTeamService.deleteTeamMember(userIds)>0){
								if(iProjectTeamService.insertTeamMember(map)>0){
									json.put("res", '1');
								}else{
									json.put("res", '0');
								}
							}else{
								json.put("res", '0');
							}
							
						}else{
							//修改执行人失败
							json.put("res", '0');
						}
					}else{
						//修改状态失败
						json.put("res", '0');
					}
				}else{
					//任务进度和实际消耗时间未更新
					json.put("res", '2');
				}
			}else{
				//将成员任务执行人修改为空
				if(iProjectTeamService.updateExcutor(taskId)>0){
					//修改执行人成功
					json.put("res", '1');
				}else{
					//修改执行人失败
					json.put("res", '0');
				}
			}
		}
		}else{
			//该成员没有任务、直接分配
			//修改项目成员表
			if(iProjectTeamService.deleteTeamMember(userIds)>0){
				
				if(iProjectTeamService.insertTeamMember(map)>0){
					json.put("res", '1');
				}else{
					json.put("res", '0');
				}
			}else{
				json.put("res", '0');
			}
		}
		System.out.println("res"+json.get("res"));
		return json;
	}
}
