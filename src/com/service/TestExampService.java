package com.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.ProjectInfo;
import com.entity.Recycle;
import com.entity.TestExamp;
import com.entity.UserInfo;
import com.mapper.TestExampMapper;
import com.service.impl.ITestExampService;
import com.util.PageBean;

@Service
public class TestExampService implements ITestExampService {
	@Autowired
	TestExampMapper testExampMapper;

	/**
	 * 分页查询方法
	 * 
	 * @param request
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	@Override
	public PageBean getPageBean(HttpServletRequest request, TestExamp testExamp) {
		
		UserInfo user =(UserInfo) request.getSession().getAttribute("UserInfo");
		// 前台传入的当前页码
		String currpage = request.getParameter("currpage");
		// 非空.默认值,设置,以及获取前台当前页码并转码int类型
		int currpages = (currpage != null) ? Integer.parseInt(currpage) : 1;
		// 前台获取每页条数
		String pagelimit = request.getParameter("pagelimit");
		// 非空.默认值,设置,以及获取前台获取每页条数并转码int类型
		int page = (pagelimit != null) ? Integer.parseInt(pagelimit) : 5;
		// 通过构造方法传入当前页码,每页显示条数,用于计算下标
		PageBean pages = new PageBean(page,currpages);
		// 获取前台查询开始时间
		String beginTime = request.getParameter("beginTime");
		// 获取前台查询结束时间
		String endTime = request.getParameter("endTime");
		// 创建HashMap对象,封装userInfo,page,用于储存分页查询的条件的属性.
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("user", user);
		hashMap.put("testExamp", testExamp);
		hashMap.put("page", pages);
		hashMap.put("beginTime", beginTime);
		hashMap.put("endTime", endTime);
		// 获取总条数
		int totalcount = getCount(hashMap);
		// 分页查询的方法,返回List
		List<TestExamp> testExampList = testExampMapper.getTestExamps(hashMap);
		// 创建请求地址url,并赋值
		String url = "/test/testExampList.action";
		// 创建请求参数params,通过StringBuffer对象进行拼接,用append方法拼接,
		StringBuffer params = new StringBuffer();
		if (StringUtils.isNotBlank(testExamp.getProjName())) {
			params.append("&projName=").append(testExamp.getProjName());
		}
		if (StringUtils.isNotBlank(testExamp.getTestTitle())) {
			params.append("&testTitle=").append(testExamp.getTestTitle());
		}
		if (StringUtils.isNotBlank(testExamp.getTestState())) {
			params.append("&testState()=").append(testExamp.getTestState());
		}
		if (StringUtils.isNotBlank(testExamp.getTestType())) {
			params.append("&testType=").append(testExamp.getTestType());
		}
		if (StringUtils.isNotBlank(testExamp.getStartTime())) {
			params.append("&startTime=").append(testExamp.getStartTime());
		}
		if (StringUtils.isNotBlank(testExamp.getEndTime())) {
			params.append("&endTime()=").append(testExamp.getEndTime());
		}
		// 通过构造方法给pageBean对象赋值(总条数,当前页码,每页显示条数,分页查询list结果,请求地址,请求参数).
		PageBean pageBean = new PageBean(page, totalcount, currpages,  testExampList, url, params.toString());
		System.out.println(pageBean.toString());
		// 返回pageBean对象;
		return pageBean;
	}

	private int getCount(HashMap<String, Object> hashMap) {
		return testExampMapper.getCount(hashMap);
	}

	/**
	 * 修改测试用例
	 * 
	 * @param testExamp
	 * @return
	 * @version 1.0
	 */
	@Override
	public int updateTestExamp(TestExamp testExamp) {
		// TODO Auto-generated method stub
		return testExampMapper.updateTestExamp(testExamp);
	}

	
	@Override
	public List<UserInfo> findTestUserInfo(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return testExampMapper.findTestUser(proj);
	}

	
	@Override
	public int addTestExamp(HashMap map) {
		// TODO Auto-generated method stub
		testExampMapper.addTestExamp(map);
		return 0;
	}

	
	@Override
	public List<UserInfo> findExecutors(ProjectInfo proj, UserInfo user) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("proj", proj);
		map.put("user", user);
		return testExampMapper.findExecutors(map);
	}

	/* (non-Javadoc)
	 * @see com.auth.service.TestExampService#getUserByName(com.auth.bean.UserInfo)
	 */
	@Override
	public UserInfo getUserByName(UserInfo user) {
		return testExampMapper.getUserByName(user);
	}


	@Override
	public int updateExecutor(HashMap map) {
		// TODO Auto-generated method stub
		return testExampMapper.updateExecutor(map);
	}

}
