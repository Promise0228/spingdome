package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.AuthInfo;
import com.entity.Role;
import com.entity.UserGroup;
import com.entity.UserInfo;
import com.service.impl.IBugInfoService;
import com.service.impl.IPlatfromService;
import com.service.impl.IProjTaskService;
import com.service.impl.IProjectTeamService;
import com.service.impl.IUserGroupService;
import com.service.impl.IUserInfoService;
import com.service.impl.IUserRoleService;
import com.util.PageBean;

/**
 * 
 * @filename UserInfoController.java
 * @author hm
 * @date 2018年7月3日下午3:40:41
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	// 注入service实现类
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IUserRoleService roleService;
	@Autowired
	IUserGroupService  userGroupService;
	@Autowired
	IBugInfoService  bugInfoService;
	@Autowired
	IProjTaskService projTaskService;
	@Autowired
	IPlatfromService platfromService;
	@Autowired 
	IProjectTeamService iProjectTeamService;

	String authcodes;

	// 查询当前用户对应的权限
	public List<AuthInfo> getAuthUser(UserInfo userInfo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("authId", '0');
		map.put("UserInfo", userInfo);
		// 查询当前用户对应的一级权限
		List<AuthInfo> firstList = userInfoService.selectUserAuth(map);
		// 循环遍历一级权限
		for (AuthInfo auth : firstList) {
			int authId = auth.getAuthId();
			// 根据一级权限ID查询二级权限
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("authId", authId);
			map1.put("UserInfo", userInfo);
			List<AuthInfo> secondList = userInfoService.selectUserAuth(map1);
			for (AuthInfo authInfo : secondList) {
				int authId1 = authInfo.getAuthId();
				// 根据二级权限ID查询三级权限
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("authId", authId1);
				map2.put("UserInfo", userInfo);
				List<AuthInfo> thirdList = userInfoService.selectUserAuth(map2);
				for (AuthInfo authInfo2 : thirdList) {
					authcodes += "," + authInfo2.getAuthCode() + ",";

				}
			}
			// 设置父子关系,把二级权限查出的集合放到一级权限集合中
			auth.setChildList(secondList);
		}
		return firstList;
	}

	/**
	 * 
	 * @comment 测试数据库连接，查询所有用户信息
	 * @author HM
	 * @resultType ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView login(HttpServletRequest request, UserInfo user,
			Map<String, UserInfo> map) {
		ModelAndView mod = new ModelAndView("index");
		return mod;
	}
	
	/**
	 * 验证码校验
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validCode")
	public JSONObject validCode(HttpServletRequest request) {
		// 获取前段自动生成的验证码
		String vcode = (String) request.getSession().getAttribute("rand01");
		// 获取用户输入的验证码
		String code = request.getParameter("vCode");
		JSONObject json = new JSONObject();
		if (vcode != null && code != null && vcode.equals(code)) {
			json.put("msg", "1");
		} else {
			json.put("msg", "0");
		}
		return json;
	}

	/**
	 * 
	 * @comment 登录
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/login")
	public JSONObject login(HttpServletRequest request, UserInfo userInfo) {
		String vcode = (String) request.getSession().getAttribute("rand01");
		String code = request.getParameter("vCode");
		List<UserInfo> list = userInfoService.findUserByGroupId();
		JSONObject mod = new JSONObject();
		// 判断验证码是否正确
		if (vcode != null && vcode.equals(code)) {
			UserInfo user = userInfoService.login(userInfo);
			// 比对数据库用户信息，正确返回1，错误返回-1
			if (user != null) {
				Role role=bugInfoService.getRoleIdByUserInfo(user);
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("roleCodes", role);
				map.put("user", user);
				//获取快过期任务数量
				int a=0;
				if(role!=null){
					a=bugInfoService.findNoBugSums(map);
				}
				request.getSession().setAttribute("noBug", a);
				request.getSession().setAttribute("UserInfo", user);
				int userId=user.getUserId();
				//查询用户角色
						List<Role> list1=iProjectTeamService.getRole(userId);
						for (Role role1 : list1) {
							//角色id，当角色为项目经理或项目组长时使用
							map.put("roleId", role1.getRoleId());
						}
						request.getSession().setAttribute("roleId", map.get("roleId"));
				request.getSession().setAttribute("firstList",
						getAuthUser(user));
				request.getSession().setAttribute("authcodes", authcodes);
				request.getSession().setAttribute("projUser", list);
				int nearOutTask = projTaskService.getOutTask(request);
				request.getSession().setAttribute("nearOutTask", nearOutTask);
				int nearOutMessage = platfromService.getCount(request);
				request.getSession().setAttribute("nearOutMessage", nearOutMessage);
				request.getSession().setAttribute("allUsers", userInfoService.findAllUsers());
				mod.put("res", "1");//登录成功
			} else {
				mod.put("res", "-1");//用户不存在
			}
		} else {// 验证码错误返回0
			mod.put("res", "0");
		}
		return mod;
	}

	/**
	 * 
	 * @comment 首页退出登录，销毁session
	 * @author HM
	 * @resultType ModelAndView
	 */
	@RequestMapping("/logout")
	public String tuichu(HttpServletRequest request) {
		userInfoService.logout();
		request.getSession().invalidate();
		authcodes = "";
		return "redirect:/pages/login.jsp";
	}

	/**
	 * 
	 * @comment 首页退出登录，销毁session
	 * @author HM
	 * @resultType ModelAndView
	 */
	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpServletRequest request) {
		ModelAndView mo = new ModelAndView("login");
		return mo;
	}
	
	@RequestMapping("/list")
	// 查询用户列表信息
	public ModelAndView selectUser(HttpServletRequest request,
		@ModelAttribute("userInfo") UserInfo userInfo) {
		ModelAndView mo = new ModelAndView();
		// 当前页码
		String currNo1 = request.getParameter("currNo");
		// 每页显示条数
		String page1 = request.getParameter("page");
		// 三元运算符 当前页有值就取其值，没有赋10
		int currNo = (currNo1 != null) ? Integer.parseInt(currNo1) : 1;
		// 三元运算符 每页显示条数有值就取其值，没有赋1
		int page = (page1 != null && !page1.equals("")) ? Integer
				.parseInt(page1) : 5;
		// 下标=每页显示条数*（当前页码-1）
		PageBean pageBean = new PageBean(page, currNo);
		// 1.查询总条数
		int totalNum = 0;
		// 时间
		String createTime1 = request.getParameter("createTime1");
		String imports = request.getParameter("improt");
		// 查询用户列表
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", userInfo);
		map.put("pageBean", pageBean);
		map.put("createTime1", createTime1);
		map.put("imports", imports);
		
		List<UserInfo> list = userInfoService.selectUserInfo(map);
		List<UserGroup> userGrouplist= userGroupService.selectUserGroup();
		
		StringBuffer params = new StringBuffer();
		String url = "/user/list.action";
		params.append("");
		// 数据导出
		if (imports != null && imports.equals("3")) {
			mo.setViewName("writeOut");
		} else {
			totalNum = userInfoService.getUserCount(userInfo);
			mo.setViewName("user-list");
		}
		// 判断用户名是否为空
		if (userInfo.getUserName() != null) {
			params.append("&userName=").append(userInfo.getUserName());
		}
		if (userInfo.getUserState() != null) {
			params.append("&userState=").append(userInfo.getUserState());
		}
		PageBean pageBeans = new PageBean(page, totalNum, currNo, list, url,
				params.toString());
		request.getSession().setAttribute("page", pageBeans);
		request.getSession().setAttribute("userGrouplist", userGrouplist);
		// 查询所有角色名字展示到前端
		List<Role> listRole = roleService.selectRoleName();
		mo.addObject("listRole", listRole);
		return mo;
	}

	/**
	 * 
	 * @comment 时间转换
	 * @author HM
	 * @resultType void
	 */

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 
	 * @comment //根据前台传过来的用户ID批量修改用户状态[ID数组]
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updateStatic")
	public JSONObject updateStaticOne(Long... userId) {
		JSONObject json = new JSONObject();
		int count = 0;
		// 遍历数组
		for (Long long1 : userId) {
			count = count + userInfoService.updateStatic(long1);
		}
		if (count > 0) {
			json.put("msg", "1");
		} else {
			json.put("msg", "2");
		}
		return json;
	}

	/**
	 * 
	 * @comment //根据前台传过来的用户ID单个修改用户状态
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updateStatics")
	public JSONObject updateStaticAll(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String userId1 = request.getParameter("userId");
		Long userId = Long.parseLong(userId1);
		Long[] userIds = { userId };
		int count = 0;
		for (Long long1 : userIds) {
			count = userInfoService.updateStatic(long1);
		}
		if (count > 0) {
			json.put("msg", "1");
		} else {
			json.put("msg", "2");
		}
		return json;
	}

	/**
	 * 
	 * @comment //添加用户
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/addUserInfo")
	public JSONObject addUserInfo(UserInfo userInfo) {
		JSONObject json = new JSONObject();
		int count = userInfoService.addUserInfo(userInfo);
		if (count > 0) {
			json.put("msg", "1");
		} else {
			json.put("msg", "2");
		}
		return json;
	}

	/**
	 * 
	 * @comment //添加时查询用户名是否重复
	 * @author HM
	 * @resultType JSONObject
	 */
	@RequestMapping("/selectUserByName")
	@ResponseBody
	public JSONObject selectUserByName(UserInfo userInfo) {
		JSONObject json = new JSONObject();
		List<UserInfo> list = userInfoService.selectUserByName(userInfo);
		if (list.size() > 0) {
			json.put("msg", "1");// 名字重复
		} else {
			json.put("msg", "0");// 名字不重复
		}
		return json;
	}

	/**
	 * 
	 * @comment //根据用户ID重置密码
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updatapswd")
	public JSONObject updatapswd(UserInfo userInfo) {
		JSONObject json = new JSONObject();
		int count = userInfoService.updatapswd(userInfo);
		if (count > 0) {
			json.put("msg", "1");
		} else {
			json.put("msg", "2");
		}
		return json;
	}

	/**
	 * 
	 * @comment //启用禁用
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updataStaticById")
	public JSONObject updataStaticById(UserInfo userInfo) {
		JSONObject json = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String userState = userInfo.getUserState();
		if (userState.equals("禁用")) {
			map.put("userState", 1);
			map.put("UserInfo", userInfo);
			int count = userInfoService.updataStaticById(map);
			if (count > 0) {
				json.put("msg", "1");// 更改成功
			}
		} else if (userState.equals("启用")) {
			map.put("userState", 0);
			map.put("UserInfo", userInfo);
			int count = userInfoService.updataStaticById(map);
			if (count > 0) {
				json.put("msg", "1");// 更改成功
			}
		} else {
			json.put("msg", "2");// 更改失败
		}
		return json;
	}

	/**
	 * 
	 * @comment //根据用户名修改密码和昵称
	 * @author HM
	 * @resultType JSONObject
	 */
	@ResponseBody
	@RequestMapping("/updatapswds")
	public JSONObject updatapswds(UserInfo userInfo) {
		JSONObject json = new JSONObject();
		int count = userInfoService.updatapswds(userInfo);
		if (count > 0) {
			json.put("msg", "1");// 修改成功
		} else {
			json.put("msg", "2");// 修改失敗！
		}
		return json;
	}

	
	@RequestMapping("/findUsers")
	@ResponseBody
	public JSONArray findUsers(HttpServletRequest request){
		JSONArray jsonArr = new JSONArray();
		String userIds = request.getParameter("userIds");
		List<UserInfo> list = userInfoService.findUsersByIds(userIds);
		for(UserInfo user:list){
			JSONObject json = new JSONObject();
			json.put("userId",user.getUserId());
			json.put("userName", user.getUserName());
			jsonArr.add(json);
		}
		return jsonArr;	
	}
}