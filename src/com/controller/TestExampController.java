package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.service.impl.IRecycleService;
import com.service.impl.ITestExampService;
import com.entity.ProjectInfo;
import com.entity.Recycle;
import com.entity.TestExamp;
import com.entity.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("test")
public class TestExampController {
@Autowired
ITestExampService testExampService;
@Autowired
IRecycleService iRecycleService;
	/**
	 * 回收站列表分页信息
	 * 
	 * @param request
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("testExampList")
	public ModelAndView toTestExamp(HttpServletRequest request, TestExamp testExamp) {
		ModelAndView mav = new ModelAndView("testExamp-List");
		mav.addObject("page", testExampService.getPageBean(request, testExamp));
		return mav;

	}

	/**
	 * 修改测试用例
	 * @param testExamp
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("update")
	@ResponseBody
	public JSONObject updateTestExamp(TestExamp testExamp){
		JSONObject json =new JSONObject();
		json.put("res", testExampService.updateTestExamp(testExamp));
		return json;
	}
	
	/**
	 * 
	 *@comment 检测项目Id是否合法
	 *@author Administrator
	 *@date 2018年7月27日 上午11:19:47
	 *@param request
	 *@param proj
	 *@return JSONObject
	 *@version 1.0
	 */
	@RequestMapping("checkProjId")
	@ResponseBody
	public JSONObject checkProjId(HttpServletRequest request,ProjectInfo proj){
		List<UserInfo> users = testExampService.findTestUserInfo(proj);
		UserInfo userSession = (UserInfo)request.getSession().getAttribute("UserInfo");
		boolean flag = false;
		for(UserInfo user:users){
			if(userSession.getUserId()==user.getUserId()){
				flag=true;
			}
		}
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		return json;
	}
	
	/**
	 *@comment 添加测试
	 *@author Administrator
	 *@date 2018年7月27日 下午1:43:58
	 *@param request
	 *@param testExamp
	 *@return JSONObject
	 *@version 1.0
	 */
	@RequestMapping("addTest")
	@ResponseBody
	public JSONObject addTest(HttpServletRequest request,TestExamp testExamp){
		UserInfo user = (UserInfo)request.getSession().getAttribute("userinfo");
		HashMap map = new HashMap();
		map.put("user", user);
		map.put("testExamp", testExamp);
		testExampService.addTestExamp(map);
		JSONObject json = new JSONObject();
		json.put("res", "1");
		return json;
	}
	
	/**
	 * 
	 *@comment 返回指定测试用例的可执行人
	 *@author Administrator
	 *@date 2018年7月28日 下午1:18:30
	 *@param user
	 *@param proj
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("findExecutors")
	@ResponseBody
	public JSONObject findExecutors(UserInfo user,ProjectInfo proj){
		List<UserInfo> users = testExampService.findExecutors(proj, user);
		JSONObject json = new JSONObject();
		List<String> userNames = new ArrayList<String>();
		for(UserInfo userGet : users){
			userNames.add(userGet.getUserName());
		}
		json.put("s",userNames);
		return json;
	}
	
	/**
	 * 
	 *@comment 分配测试用例的执行人
	 *@author Administrator
	 *@date 2018年7月28日 下午1:18:30
	 *@param user
	 *@param proj
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("assignExecutor")
	@ResponseBody
	public JSONObject assignExecutor(UserInfo user, TestExamp testExamp){
		user = testExampService.getUserByName(user);
		HashMap map = new HashMap();
		map.put("user", user);
		map.put("testExamp", testExamp);
		testExampService.updateExecutor(map);
		return new JSONObject();
	}
	
	/**
	 *@comment 删除测试用例
	 *@author Administrator
	 *@date 2018年7月28日 下午1:18:30
	 *@param user
	 *@param proj
	 *@return
	 *JSONObject
	 *@version 1.0
	 */
	@RequestMapping("delTestExamp")
	@ResponseBody
	public JSONObject delTestExamp(TestExamp testExamp){
		Recycle recycle = new Recycle();
		recycle.setKeyName("test_id");
		recycle.setKeyValue(testExamp.getTestId());
		recycle.setTableName("test_examp");
		recycle.setClassId(0);
		recycle.setIntro("测试用例");
		iRecycleService.delToRecycle(recycle);
		JSONObject json = new JSONObject();
		json.put("res", true);
		return json;
	}

}
