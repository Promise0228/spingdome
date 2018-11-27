package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.LogInfo;
import com.entity.ProjVersion;
import com.entity.UserInfo;
import com.mapper.ProjVersionMapper;
import com.service.impl.IProjVersionService;
import com.util.PageBean;

@Service
public class ProjVersionService implements IProjVersionService {

	// 注入mapper接口
	@Autowired
	ProjVersionMapper projVersionMapper;

	/**
	 * @comment: 分页展示列表
	 * @param request
	 * @param projVersion
	 * @return
	 * @author changjiaqi
	 * @date 2018年7月24日
	 */
	public PageBean findProjVersion(HttpServletRequest request,
			ProjVersion projVersion) {
		// 当前页
		String currNo = request.getParameter("currNo");
		// 每页显示的条数
		String page = request.getParameter("page");
		int ncurrNo = (currNo != null) ? Integer.parseInt(currNo) : 1;
		int npage = (page != null) ? Integer.parseInt(page) : 5;
		// 下标=每页显示条数*（当前页码-1）
		PageBean pageBean = new PageBean(npage, ncurrNo);
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取前端输入的开始时间
		String stateTime = request.getParameter("stateTime");
		// 获取前端输入的结束时间
		String endTime = request.getParameter("endTime");
		// 获取前端传过来的项目主键Id
		/*
		 * String projId = request.getParameter("projId"); map.put("projId",
		 * Integer.parseInt(projId));
		 */
		map.put("projVersion", projVersion);
		map.put("page", pageBean);
		map.put("stateTime", stateTime);
		map.put("endTime", endTime);
		// 查询总条数
		int totalNum = projVersionMapper.findCountProjVersion(map);
		// 查询用户列表 分页
		List<ProjVersion> projVersionList = projVersionMapper
				.findProjVersion(map);
		StringBuffer sbf = new StringBuffer();
		String url = "/projlist.action";
		// 判断属性是否为空StringUtils工具类中的isNotBlank方法判定
		if (StringUtils.isNotBlank(projVersion.getVersionNum())) {
			sbf.append("&versionNum=").append(projVersion.getVersionNum());
		}
		if (StringUtils.isNotBlank(projVersion.getVersionDesc())) {
			sbf.append("&versionDesc=").append(projVersion.getVersionDesc());
		}
		if (StringUtils.isNotBlank(stateTime)) {
			sbf.append("&stateTime=").append(stateTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			sbf.append("&endTime=").append(endTime);
		}
		pageBean = new PageBean(npage, totalNum, ncurrNo, projVersionList, url,
				sbf.toString());
		return pageBean;
	}

	/**
	 * @comment: 添加版本信息
	 * @param projVersion
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int addProjVersion(HttpServletRequest request,
			ProjVersion projVersion) {
		// 获取session中的值
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		// 将登陆人的Id放入map中，是列表信息字段中的创建人
		map.put("createBy", userInfo.getUserId());
		// 获取项目主键Id的值
		  int projId = Integer.parseInt(request.getParameter("projId"));
		  map.put("projId", projId);
		// 将前端传过来的projVersion对象放入map中
		map.put("projVersion", projVersion);
		int re = 0;
		//获取添加前的版本号，根据“.”截取并转换成int类型
				 String versionNum2=projVersionMapper.findProjNum();	
				 
				if(StringUtils.isNotBlank(versionNum2)){				
				String str1[]=versionNum2.split("\\.");
				String versionNum3 = "";
				for (int i = 0; i < str1.length; i++) {
					versionNum3+=str1[i];
				}
				//获取前端传过来的版本号，根据“.”截取并且转换成int类型
				String versionNum = projVersion.getVersionNum();
				String str[] = versionNum.split("\\.");
				String versionNum1="";
				for (int i = 0; i < str.length; i++) {
					versionNum1+=str[i];
				}
				//versionNum3.compareTo(versionNum1)>0,前大后；=0，相等；<0,前小后
				if (versionNum3.compareTo(versionNum1)<0) {
			if (projVersionMapper.addProjVersion(map) > 0) {
				re = 1;// 添加成功
			} else {
				re = 0;// 添加失败
			}
		}else {
			re = -1;// 版本号低于前版本号
		} 
				}else {
					if (projVersionMapper.addProjVersion(map) > 0) {
						re = 1;// 添加成功
					} else {
						re = 0;// 添加失败
					}
				}
				
		return re;
	}

	/**
	 * @comment: 修改版本信息
	 * @param projVersion
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int updateProjVersion(ProjVersion projVersion) {
		int re = 0;
		//获取添加前的版本号，根据“.”截取并转换成int类型
		 String versionNum2=projVersionMapper.findProjNum();			
				String str1[]=versionNum2.split("\\.");
				String versionNum3 = "";
				for (int i = 0; i < str1.length; i++) {
					versionNum3+=str1[i];
				}
				//获取前端传过来的版本号，根据“.”截取并且转换成int类型
				String versionNum = projVersion.getVersionNum();
				String str[] = versionNum.split("\\.");
				String versionNum1="";
				for (int i = 0; i < str.length; i++) {
					versionNum1+=str[i];
				}
				//versionNum3.compareTo(versionNum1)>0,前大后；=0，相等；<0,前小后
				if (versionNum3.compareTo(versionNum1)<0) {
			if (projVersionMapper.updateProjVersion(projVersion) > 0) {
				re = 1;// 修改成功
			} else {
				re = 0;// 修改失败
			}
		}else {
			re = -1;// 版本号低于前版本号
		}
		return re;
	}

	/**
	 * @comment: 删除版本信息
	 * @param projVersion
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int deleteProjVersion(ProjVersion projVersion) {
		int re = 0;
		if (projVersionMapper.deleteProjVersion(projVersion) > 0) {
			re = 1;// 删除成功
		} else {
			re = 0;// 删除失败
		}
		return re;
	}

}
