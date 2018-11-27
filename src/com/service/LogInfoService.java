package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.LogInfo;
import com.entity.UserInfo;
import com.mapper.LogInfoMapper;
import com.service.impl.ILogInfoService;
import com.util.PageBean;

/**
 * @comment 日志业务层实现类
 * @author changjiaqi
 * @date 2018年7月17日 上午11:00:16
 * @version TODO
 */
@Service
public class LogInfoService implements ILogInfoService {
	// 注入mapper层接口
	@Autowired
	LogInfoMapper logInfoMapper;

	/**
	 * @comment: 分页查询日志列表
	 * @param map
	 * @return
	 * @author changjiaqi
	 * @date 2018年7月17日
	 */
	public PageBean findLogInfo(HttpServletRequest request, LogInfo logInfo) {
		// 当前页
		String currNo = request.getParameter("currNo");
		// 每页显示的条数
		String page = request.getParameter("page");
		int ncurrNo = (currNo != null) ? Integer.parseInt(currNo) : 1;
		int npage = (page != null) ? Integer.parseInt(page) : 5;
		// 下标=每页显示条数*（当前页码-1）
		PageBean pageBean = new PageBean(npage, ncurrNo);
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取前端输入的项目名称
		String projName = request.getParameter("projName");
		// 获取前端输入的操作人名称
		String userName = request.getParameter("userName");
		//获取前端输入的异常信息
		String exception = request.getParameter("exception");
		// 获取前端输入的开始时间
		String stateTime = request.getParameter("stateTime");
		// 获取前端输入的结束时间
		String endTime = request.getParameter("endTime");
		map.put("logInfo", logInfo);
		map.put("page", pageBean);
		map.put("projName", projName);
		map.put("exception", exception);
		map.put("userName", userName);
		map.put("stateTime", stateTime);
		map.put("endTime", endTime);
		map.put("export", request.getParameter("export"));
		// 导出并删除
		if (StringUtils.isNotBlank(request.getParameter("export"))) {
			String logIds = logInfoMapper.findLogIdBy(map);
			request.getSession().setAttribute("logIds", logIds);
		}

		// 查询总条数
		int totalNum = logInfoMapper.findLogInfoCount(map);
		// 查询用户列表 分页
		List<LogInfo> logInfoList = logInfoMapper.findLogInfo(map);
		StringBuffer sbf = new StringBuffer();
		String url = "/loglist.action";
		// 判断属性是否为空StringUtils工具类中的isNotBlank方法判定
		if (StringUtils.isNotBlank(projName)) {
			sbf.append("&projName=").append(projName);
		}
		if (StringUtils.isNotBlank(logInfo.getLogInfo())) {
			sbf.append("&logInfo=").append(logInfo.getLogInfo());
		}
		if (StringUtils.isNotBlank(logInfo.getIpAddress())) {
			sbf.append("&ipAddress=").append(logInfo.getIpAddress());
		}
		if (StringUtils.isNotBlank(logInfo.getException())) {
			sbf.append("&exception=").append(logInfo.getException());
		}
		if (StringUtils.isNotBlank(userName)) {
			sbf.append("&userName=").append(userName);
		}
		if (StringUtils.isNotBlank(stateTime)) {
			sbf.append("&stateTime=").append(stateTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			sbf.append("&endTime=").append(endTime);
		}
		pageBean = new PageBean(npage, totalNum, ncurrNo, logInfoList, url, sbf.toString());
		return pageBean;
	}
	/**
	 * @comment: 添加日志信息
	 * @param map
	 *            void
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */
	public void insertLogInfo(Map map) {
		logInfoMapper.insertLogInfo(map);
	}

	/**
	 * @comment: 根据用户名查询用户Id;
	 * @param userInfo
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */
	public int getUserIdByName(UserInfo userInfo) {
       int re=0;
       if (logInfoMapper.getUserIdByName(userInfo)!=null) {
		re=logInfoMapper.getUserIdByName(userInfo);
	}else {
		re=0;
	}
		return re;
	}

	/**
	 * @comment: 删除日志信息
	 * @param logIds
	 * @return Object
	 * @author changjiaqi
	 * @date 2018年7月19日
	 */

	public int deleteLogInfo(HttpServletRequest request) {
		String logIds = (String) request.getSession().getAttribute("logIds")
				+ ",";

		// 截取字符串循坏删除日志信息
		String str[] = logIds.split(",");
		int re = 0;
		// 循坏遍历
		for (String string : str) {
			re = logInfoMapper.deleteLogInfo(string);
		}
		if (re > 0) {
			re = 1;// 删除成功
		} else {
			re = 0;// 删除失败
		}
		return re;

	}
	/**
	 * @comment: 通过用户code查询项目主键ID
	 * @param userInfo
	 * @return   
	 * Object  
	 * @author changjiaqi
	 * @date 2018年7月21日
	 */
	public String getProjIdByUserCode(UserInfo userInfo) {
		
		return logInfoMapper.getProjIdByUserCode(userInfo);
	}
}
