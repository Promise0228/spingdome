package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.entity.BugInfo;
import com.entity.BugReply;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.SysClass;
import com.entity.TeamMember;
import com.entity.UserInfo;
import com.service.BugInfoService;
import com.service.impl.IBugInfoService;
import com.util.PageBean;

@Controller
@RequestMapping("/bug")
public class BugInfoController {
	@Autowired
	IBugInfoService bugInfoService;

	/**
	 * 
	 * @comment
	 * @param request
	 * @return
	 * @version 1.0 ModelAndView
	 */
	@RequestMapping("/bugList")
	public ModelAndView findBug(HttpServletRequest request, BugInfo buginfo) {
		if(buginfo!=null&&buginfo+""!=""){
			request.getSession().setAttribute("listss", buginfo);
		}
		
		// 分页
		// 当前页码
		String currNo1 = request.getParameter("currNo");
		// 每页显示条数
		String page1 = request.getParameter("pageNum");
		// 三元运算符 每页显示条数有值就取其值，没有赋1
		int currNo = (currNo1 != null) ? Integer.parseInt(currNo1) : 1;
		// 三元运算符 当前页有值就取其值，没有赋5
		int page = (page1 != null && !page1.equals("")) ? Integer
				.parseInt(page1) : 5;
		// 下标=每页显示条数*（当前页码-1）
		PageBean pageBean = new PageBean(page, currNo);
		// 时间
		String createTime1 = request.getParameter("createTime1");
		String imports = request.getParameter("improt");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bug", buginfo);
		map.put("pageBean", pageBean);
		map.put("createTime1", createTime1);
		map.put("imports", imports);
		if (buginfo.getProjNum() != null && buginfo.getProjNum()!="") {
			int projId = bugInfoService.getProjIdByNum(buginfo.getProjNum());
			map.put("projids", projId);
			System.out.println("项目id："+projId);
		}
		if (buginfo.getUpdateBys() != null && buginfo.getUpdateBys()!="") {
			String UpdateBys = bugInfoService.getUserIdByName(buginfo
					.getUpdateBys());
			if(UpdateBys!=null&&UpdateBys!=""){
			map.put("updateBys1", UpdateBys);
			}else{
				map.put("updateBys1", -1);
			}
		}
		if (buginfo.getClassIds() != null && buginfo.getClassIds()!="") {
			String ClassIds = bugInfoService.getBugIdByNum(buginfo
					.getClassIds());
			map.put("classids", ClassIds);
			System.out.println("模块id："+ClassIds);
		}
//--------------------------------------------------------------------
		//根据执行人名字查id
				if(buginfo.getUpdateBys()!=null && buginfo.getUpdateBys()!=""){
					map.put("updateBys", bugInfoService.getUserIdByName(buginfo.getUpdateBys()));
				}
				//根据项目名字查id
				if(buginfo.getProjIds()!=null && buginfo.getProjIds()!=""){
					map.put("projIds", bugInfoService.getProjIdByNum(buginfo.getProjIds()));
				}
				//根据模块名字查id
				if(buginfo.getClassIds()!=null && buginfo.getClassIds()!=""){
					map.put("classIds", bugInfoService.getBugIdByNum(buginfo.getClassIds()));
				}
		// -------------------------------------------------------------------------------------------
		
		// 查询所有系统
		List<ProjectInfo> sysList = bugInfoService.selectProjectName();
		request.getSession().setAttribute("sysList", sysList);
		// 查询所有模块
		List<SysClass> classIds = bugInfoService.selectClassId();
		request.getSession().setAttribute("classIds", classIds);
		// 查询所有测试组
		String test = bugInfoService.findTest();
		System.out.println(test);
		// 查角色
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("UserInfo");
		Role role = bugInfoService.getRoleIdByUserInfo(user);
		request.getSession().setAttribute("role", role);
		if (role != null) {
			//String test1 = "";
			map.put("roleCodes", role);
			map.put("user", user);
			// 1.查询总条数
			int totalNum = bugInfoService.findBugSum(map);
			// 查bug
			List<BugInfo> list = bugInfoService.findBug(map);
			request.getSession().setAttribute("bugList", list);
			// 查系统
			// }

			StringBuffer params = new StringBuffer();
			String url = "/bug/bugList.action";
			params.append("");
			// 判断用户名是否为空

			if (buginfo.getBugTitle() != null && StringUtils.isNotBlank(buginfo.getBugTitle()+"")) {
				params.append("&BugTitle=").append(buginfo.getBugTitle());
			}
			if (buginfo.getUpdateBys() != null && StringUtils.isNotBlank(buginfo.getUpdateBys()+"")) {
				params.append("&UpdateBys=").append(buginfo.getUpdateBys());
			}
			if (buginfo.getCreateTime() != null && StringUtils.isNotBlank(buginfo.getCreateTime()+"")) {
				params.append("&CreateTime=").append(buginfo.getCreateTime());
			}
			if (buginfo.getCreateTime1() != null && StringUtils.isNotBlank(buginfo.getCreateTime1()+"")) {
				params.append("&CreateTime1=").append(buginfo.getCreateTime1());
			}
			if (buginfo.getBugState()!=null && buginfo.getBugState()+""!="") {
				params.append("&BugState=").append(buginfo.getBugState());
			}
			if (buginfo.getBugLevel()!=null && buginfo.getBugLevel()+""!="" ) {
				params.append("&BugLevel=").append(buginfo.getBugLevel());
			}
			if (buginfo.getProjIds() != null && StringUtils.isNotBlank(buginfo.getProjIds()+"")) {
				params.append("&ProjIds=").append(buginfo.getProjIds());
			}
			if (buginfo.getClassIds() != null && StringUtils.isNotBlank(buginfo.getClassIds()+"")) {
				params.append("&ClassIds=").append(buginfo.getClassIds());
			}

			PageBean pageBeans = new PageBean(page, totalNum, currNo, list,
					url, params.toString());
			request.getSession().setAttribute("page", pageBeans);
		}
		// 查询所有项目
		List<ProjectInfo> proj = bugInfoService.getProjByUser(map);
		request.getSession().setAttribute("proj", proj);
		ModelAndView mav = new ModelAndView("bug");
		return mav;

	}

	/**
	 * bug编码唯一效验
	 * 
	 * @comment
	 * @param buginfo
	 * @return
	 * @version 1.0 JSONObject
	 */
	@RequestMapping("/bugNumber")
	@ResponseBody
	public JSONObject findBugInfoByBugNumber(BugInfo buginfo) {
		BugInfo buginfo1 = bugInfoService.findBugInfoByBugNumber(buginfo);
		JSONObject json = new JSONObject();
		if (buginfo1 == null) {
			json.put("bugNumber", "1");
		} else {
			json.put("bugNumber", "2");
		}
		return json;
	}

	/**
	 * 添加bug
	 * 
	 * @comment
	 * @param request
	 * @param buginfo
	 * @return
	 * @version 1.0 JSONObject
	 */
	@RequestMapping("addbug")
	@ResponseBody
	public JSONObject addbug(HttpServletRequest request, BugInfo buginfo) {
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("UserInfo");
		int userId = user.getUserId();
		buginfo.setCreateBy(userId);
		String str=buginfo.getProjNum();
		buginfo.setProjId(bugInfoService.getProjIdByNum(str));
		int i = bugInfoService.addBug(buginfo);
		JSONObject json = new JSONObject();
		if (i > 0) {
			json.put("add", "1");
		} else {
			json.put("add", "2");
		}
		return json;

	}

	/**
	 * 删除bug
	 * 
	 * @comment
	 * @param buginfo
	 * @return
	 * @version 1.0 JSONObject
	 */
	@RequestMapping("delbug")
	@ResponseBody
	public JSONObject delBug(BugInfo buginfo) {
		int i = bugInfoService.delBug(buginfo);
		JSONObject json = new JSONObject();
		if (i > 0) {
			json.put("delbug", "1");
		} else {
			json.put("delbug", "2");
		}
		return json;
	}

	/**
	 * 禁用bug
	 * 
	 * @comment
	 * @param buginfo
	 * @return
	 * @version 1.0 JSONObject
	 */
	@RequestMapping("forbidbug")
	@ResponseBody
	public JSONObject updateForbidBug(BugInfo buginfo) {
		int i = bugInfoService.updateForbidBug(buginfo);
		JSONObject json = new JSONObject();
		if (i > 0) {
			json.put("forbidbug", "1");
		} else {
			json.put("forbidbug", "2");
		}
		return json;
	}

	/**
	 * 启动bug
	 * 
	 * @comment
	 * @param buginfo
	 * @return
	 * @version 1.0 JSONObject
	 */
	@RequestMapping("startBug")
	@ResponseBody
	public JSONObject updateStartBug(BugInfo buginfo) {
		int i = bugInfoService.updateStartBug(buginfo);
		JSONObject json = new JSONObject();
		if (i > 0) {
			json.put("startBug", "1");
		} else {
			json.put("startBug", "2");
		}
		return json;
	}

	/**
	 * 查询项目组
	 * 
	 * @comment
	 * @param request
	 * @param buginfo
	 * @version 1.0 void
	 */
	@RequestMapping("/findProjTeam")
	@ResponseBody
	public JSONArray findProjTeam(HttpServletRequest request, BugInfo buginfo) {
		int projId = buginfo.getProjId();
		List<ProjTeam> ProjTeam = bugInfoService.findTeamIdByProjId(projId);
		JSONArray json = new JSONArray();
		for (ProjTeam projTeam2 : ProjTeam) {
			JSONObject jsonb = new JSONObject();
			jsonb.put("projId", projTeam2.getTeamId());
			jsonb.put("teamname", projTeam2.getTeamName());
			json.add(jsonb);
		}

		return json;

	}

	/**
	 * 查询项目组成员
	 * 
	 * @comment
	 * @param request
	 * @param buginfo
	 * @version 1.0 void
	 */
	@RequestMapping("/findProjTeamUser")
	@ResponseBody
	public JSONArray findProjTeamUser(HttpServletRequest request,
			TeamMember teamMember) {
		JSONArray jsona = new JSONArray();
		int i = 0;
		if (teamMember != null) {
			i = teamMember.getTeamId();
		}
		List<UserInfo> teammember = bugInfoService.findUserIdByTeamId(i);
		for (UserInfo userInfo : teammember) {
			JSONObject json = new JSONObject();
			json.put("userIds", userInfo.getUserId());
			json.put("userNames", userInfo.getUserName());
			jsona.add(json);
		}

		return jsona;

	}

	@RequestMapping("/allocationbug")
	@ResponseBody
	public JSONObject updateAllocationBug(BugInfo bugInfo) {
		int i = bugInfoService.updateAllocationBug(bugInfo);
		JSONObject json = new JSONObject();
		if (i > 0) {
			json.put("allocationbug", "1");
		} else {
			json.put("allocationbug", "2");
		}
		return json;
	}

	/**
	 * 图片文件上传
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload( MultipartFile file,MultipartFile file1,MultipartFile file2,BugInfo bugInfo, HttpServletRequest request) {
		System.out.println(file);
		System.out.println(file1);
		System.out.println(file2);
		// 判断文件是否为空
		String fi = "";
		if (!file.isEmpty()) {
			// 文件保存路径
			// file.getOriginalFilename()获取上传文件原始文件名
			String filePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload/" + file.getOriginalFilename();
			// 转存文件
			fi = "/" + "upload/" + file.getOriginalFilename() + ",";
			try {
				file.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!file1.isEmpty()) {
				// 文件保存路径
				// file.getOriginalFilename()获取上传文件原始文件名
				String filePath1 = request.getSession().getServletContext()
						.getRealPath("/")
						+ "upload/" + file1.getOriginalFilename();
				// 转存文件
				fi += "/" + "upload/" + file1.getOriginalFilename() + ",";
				try {
					file1.transferTo(new File(filePath1));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!file2.isEmpty()) {
					// 文件保存路径
					// file.getOriginalFilename()获取上传文件原始文件名
					String filePath2 = request.getSession().getServletContext()
							.getRealPath("/")
							+ "upload/" + file2.getOriginalFilename();
					// 转存文件
					fi += "/" + "upload/" + file2.getOriginalFilename() + ",";
					try {
						file2.transferTo(new File(filePath2));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			bugInfo.setFileUrl(fi);
		}
		int i = bugInfoService.updateBugByNum(bugInfo);
		System.out.println(i);
		return "redirect:bugList.action";
	}

	/**
	 * 项目与模块的二级联动
	 * 
	 * @comment
	 * @param request
	 * @param projectInfo
	 * @return
	 * @version 1.0 JSONArray
	 */
	@RequestMapping("findClassByProj")
	@ResponseBody
	public JSONArray findClassByProj(HttpServletRequest request,
			ProjectInfo projectInfo) {
		JSONArray jsona = new JSONArray();
		List<SysClass> projectinfo = bugInfoService
				.findClassByProj(projectInfo);
		for (SysClass sysClass : projectinfo) {
			JSONObject json = new JSONObject();
			json.put("classids", sysClass.getClassId());
			json.put("classNames", sysClass.getClassName());
			jsona.add(json);
		}

		return jsona;

	}

	/**
	 * bug详情
	 * 
	 * @comment
	 * @return
	 * @version 1.0 ModelAndView
	 */
	@RequestMapping("/bugdetails")
	public ModelAndView getBug(HttpServletRequest request,BugInfo bugInfo) {
		List<BugInfo> list=bugInfoService.getBugByNum(bugInfo);
		List<BugReply> listReply=bugInfoService.findReplyByNum(bugInfo);
		request.getSession().setAttribute("bugdetails", list);
		request.getSession().setAttribute("listReply", listReply);
        ModelAndView mav = new ModelAndView("bugdetails");
        String str="";
        for (BugInfo bugInfo2 : list) {
        	str+=bugInfo2.getFileUrl();
		}
        String [] str1=str.split(",");
        request.getSession().setAttribute("img", str1);
        return mav;
	}
	
	@RequestMapping("/leave")
	@ResponseBody
	public JSONObject leave(HttpServletRequest request,BugReply bugReply){
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("UserInfo");
		bugReply.setCreateBy(userInfo.getUserId());
		int i=bugInfoService.addReply(bugReply);
		JSONObject json=new JSONObject();
		if(i>0){
			json.put("leave", 1);
			return json;
		}else{
			json.put("leave", 2);
			return json;
		}
		
	}
	/**
	 * 删除
	 * @comment 
	 * @param bugReply
	 * @version 1.0
	 * void
	 */
	@RequestMapping("/delReply")
	public String delReply(BugReply bugReply){
		int i=bugInfoService.delReply(bugReply);
		System.out.println(i);
		int c=bugReply.getBugId();
		return "redirect:bugdetails.action?bugId="+c;
	}
	/**
	 * 我的bug
	 * @comment 
	 * @param request
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * ModelAndView
	 */
	@RequestMapping("/myBug")
	public ModelAndView myBug(HttpServletRequest request, BugInfo buginfo) {
		// 分页
		// 当前页码
		String currNo1 = request.getParameter("currNo");
		// 每页显示条数
		String page1 = request.getParameter("pageNum");
		// 三元运算符 每页显示条数有值就取其值，没有赋1
		int currNo = (currNo1 != null) ? Integer.parseInt(currNo1) : 1;
		// 三元运算符 当前页有值就取其值，没有赋5
		int page = (page1 != null && !page1.equals("")) ? Integer
				.parseInt(page1) : 5;
		// 下标=每页显示条数*（当前页码-1）
		PageBean pageBean = new PageBean(page, currNo);
		// 时间
		String createTime1 = request.getParameter("createTime1");
		String imports = request.getParameter("improt");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bug", buginfo);
		map.put("pageBean", pageBean);
		map.put("createTime1", createTime1);
		map.put("imports", imports);
		if (buginfo.getProjNum() != null && buginfo.getProjNum()!="") {
			int projId = bugInfoService.getProjIdByNum(buginfo.getProjNum());
			map.put("projids", projId);
		}
		if (buginfo.getUpdateBys() != null && buginfo.getUpdateBys()!="") {
			String UpdateBys = bugInfoService.getUserIdByName(buginfo
					.getUpdateBys());
			map.put("updatebys", UpdateBys);
		}
		if (buginfo.getClassIds() != null && buginfo.getClassIds()!="") {
			String ClassIds = bugInfoService.getBugIdByNum(buginfo
					.getClassIds());
			map.put("classids", ClassIds);
		}
//--------------------------------------------------------------------
		//根据执行人名字查id
				if(buginfo.getUpdateBys()!=null){
					map.put("updateBys", bugInfoService.getUserIdByName(buginfo.getUpdateBys()));
				}
				//根据项目名字查id
				if(buginfo.getProjIds()!=null){
					map.put("projIds", bugInfoService.getProjIdByNum(buginfo.getProjIds()));
				}
				//根据模块名字查id
				if(buginfo.getClassIds()!=null){
					map.put("classIds", bugInfoService.getBugIdByNum(buginfo.getClassIds()));
				}
		// -------------------------------------------------------------------------------------------
		
		// 查询所有系统
		List<ProjectInfo> sysList = bugInfoService.selectProjectName();
		request.getSession().setAttribute("sysList", sysList);
		// 查询所有模块
		List<SysClass> classIds = bugInfoService.selectClassId();
		request.getSession().setAttribute("classIds", classIds);
		// 查询所有测试组
		String test = bugInfoService.findTest();
		System.out.println(test);
		// 查角色
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("UserInfo");
		Role role = bugInfoService.getRoleIdByUserInfo(user);
		request.getSession().setAttribute("role", role);
		if (role != null) {
			//String test1 = "";
			map.put("roleCodes", role);
			map.put("user", user);
			// 1.查询总条数
			int totalNum = bugInfoService.findBugSum(map);
			// 查bug
			List<BugInfo> list = bugInfoService.findBug(map);
			request.getSession().setAttribute("bugList", list);
			// 查系统
			// }

			StringBuffer params = new StringBuffer();
			String url = "/bug/bugList.action";
			params.append("");
			// 判断用户名是否为空

			if (buginfo.getBugTitle() != null && StringUtils.isNotBlank(buginfo.getBugTitle()+"")) {
				params.append("&BugTitle=").append(buginfo.getBugTitle());
			}
			if (buginfo.getUpdateBys() != null && StringUtils.isNotBlank(buginfo.getUpdateBys()+"")) {
				params.append("&UpdateBys=").append(buginfo.getUpdateBys());
			}
			if (buginfo.getCreateTime() != null && StringUtils.isNotBlank(buginfo.getCreateTime()+"")) {
				params.append("&CreateTime=").append(buginfo.getCreateTime());
			}
			if (buginfo.getCreateTime1() != null && StringUtils.isNotBlank(buginfo.getCreateTime1()+"")) {
				params.append("&CreateTime1=").append(buginfo.getCreateTime1());
			}
			if (buginfo.getBugState()!=null && buginfo.getBugState()+""!="") {
				params.append("&BugState=").append(buginfo.getBugState());
			}
			if (buginfo.getBugLevel()!=null && buginfo.getBugLevel()+""!="" ) {
				params.append("&BugLevel=").append(buginfo.getBugLevel());
			}
			if (buginfo.getProjIds() != null && StringUtils.isNotBlank(buginfo.getProjIds()+"")) {
				params.append("&ProjIds=").append(buginfo.getProjIds());
			}
			if (buginfo.getClassIds() != null && StringUtils.isNotBlank(buginfo.getClassIds()+"")) {
				params.append("&ClassIds=").append(buginfo.getClassIds());
			}

			PageBean pageBeans = new PageBean(page, totalNum, currNo, list,
					url, params.toString());
			request.getSession().setAttribute("page", pageBeans);
		}
		// 查询所有项目
		List<ProjectInfo> proj = bugInfoService.getProjByUser(map);
		request.getSession().setAttribute("proj", proj);
		ModelAndView mav = new ModelAndView("my-bug");
		return mav;

	}
	@RequestMapping("noBug")
	public ModelAndView findNoBug(HttpServletRequest request,BugInfo buginfo){
		// 分页
				// 当前页码
				String currNo1 = request.getParameter("currNo");
				// 每页显示条数
				String page1 = request.getParameter("pageNum");
				// 三元运算符 每页显示条数有值就取其值，没有赋1
				int currNo = (currNo1 != null) ? Integer.parseInt(currNo1) : 1;
				// 三元运算符 当前页有值就取其值，没有赋5
				int page = (page1 != null && !page1.equals("")) ? Integer
						.parseInt(page1) : 5;
				// 下标=每页显示条数*（当前页码-1）
				PageBean pageBean = new PageBean(page, currNo);
				// 时间
				String createTime1 = request.getParameter("createTime1");
				String imports = request.getParameter("improt");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bug", buginfo);
				map.put("pageBean", pageBean);
				map.put("createTime1", createTime1);
				map.put("imports", imports);
				if (buginfo.getProjNum() != null && buginfo.getProjNum()!="") {
					int projId = bugInfoService.getProjIdByNum(buginfo.getProjNum());
					map.put("projids", projId);
				}
				if (buginfo.getUpdateBys() != null && buginfo.getUpdateBys()!="") {
					String UpdateBys = bugInfoService.getUserIdByName(buginfo
							.getUpdateBys());
					map.put("updatebys", UpdateBys);
				}
				if (buginfo.getClassIds() != null && buginfo.getClassIds()!="") {
					String ClassIds = bugInfoService.getBugIdByNum(buginfo
							.getClassIds());
					map.put("classids", ClassIds);
				}
		//--------------------------------------------------------------------
				//根据执行人名字查id
						if(buginfo.getUpdateBys()!=null){
							map.put("updateBys", bugInfoService.getUserIdByName(buginfo.getUpdateBys()));
						}
						//根据项目名字查id
						if(buginfo.getProjIds()!=null){
							map.put("projIds", bugInfoService.getProjIdByNum(buginfo.getProjIds()));
						}
						//根据模块名字查id
						if(buginfo.getClassIds()!=null){
							map.put("classIds", bugInfoService.getBugIdByNum(buginfo.getClassIds()));
						}
				// -------------------------------------------------------------------------------------------
				
				// 查询所有系统
				List<ProjectInfo> sysList = bugInfoService.selectProjectName();
				
				// 查询所有模块
				List<SysClass> classIds = bugInfoService.selectClassId();
				
				// 查询所有测试组
				String test = bugInfoService.findTest();
				// 查角色
				UserInfo user = (UserInfo) request.getSession()
						.getAttribute("UserInfo");
				Role role = bugInfoService.getRoleIdByUserInfo(user);
				
				if (role != null) {
					String test1 = "";
					map.put("roleCodes", role);
					map.put("user", user);
					// 1.查询总条数
					int totalNum = bugInfoService.findMyBugSum(map);
					// 查bug
					List<BugInfo> list = bugInfoService.findMyBug(map);
					request.getSession().setAttribute("totalNum", totalNum);
					request.getSession().setAttribute("noBugList", list);
					// 查系统
					StringBuffer params = new StringBuffer();
					String url = "/bug/noBug.action";
					params.append("");
					// 判断用户名是否为空

					if (buginfo.getBugTitle() != null && StringUtils.isNotBlank(buginfo.getBugTitle()+"")) {
						params.append("&BugTitle=").append(buginfo.getBugTitle());
					}
					if (buginfo.getUpdateBys() != null && StringUtils.isNotBlank(buginfo.getUpdateBys()+"")) {
						params.append("&UpdateBys=").append(buginfo.getUpdateBys());
					}
					if (buginfo.getCreateTime() != null && StringUtils.isNotBlank(buginfo.getCreateTime()+"")) {
						params.append("&CreateTime=").append(buginfo.getCreateTime());
					}
					if (buginfo.getCreateTime1() != null && StringUtils.isNotBlank(buginfo.getCreateTime1()+"")) {
						params.append("&CreateTime1=").append(buginfo.getCreateTime1());
					}
					if (buginfo.getBugState()!=null && buginfo.getBugState()+""!="") {
						params.append("&BugState=").append(buginfo.getBugState());
					}
					if (buginfo.getBugLevel()!=null && buginfo.getBugLevel()+""!="" ) {
						params.append("&BugLevel=").append(buginfo.getBugLevel());
					}
					if (buginfo.getProjIds() != null && StringUtils.isNotBlank(buginfo.getProjIds()+"")) {
						params.append("&ProjIds=").append(buginfo.getProjIds());
					}
					if (buginfo.getClassIds() != null && StringUtils.isNotBlank(buginfo.getClassIds()+"")) {
						params.append("&ClassIds=").append(buginfo.getClassIds());
					}

					PageBean pageBeans = new PageBean(page, totalNum, currNo, list,
							url, params.toString());
					request.getSession().setAttribute("page", pageBeans);
				}
				
				ModelAndView mav=new ModelAndView("nobug");
				return mav;
	}
	/**
	 * 自动补全
	 * @comment 
	 * @param bugInfo
	 * @return
	 * @version 1.0
	 * JSONObject
	 */
	@RequestMapping("auto-completion")
	@ResponseBody
	public JSONArray autoCompletion(HttpServletRequest request,BugInfo bugInfo){
		List<BugInfo> list=bugInfoService.findBugNameByBugName(bugInfo);
		JSONObject json=new JSONObject();
		json.put("auto", list);
		JSONArray ja=new JSONArray();
		ja.add(json);
		return ja;
	}
}
