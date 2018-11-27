/**  
 * @Title: ProjTaskService.java
 * @Package com.item.service
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月25日
 */
package com.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jboss.weld.bean.proxy.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.ProjTask;
import com.entity.Role;
import com.entity.SysClass;
import com.entity.UserInfo;
import com.mapper.ProjTaskMapper;
import com.service.impl.IProjTaskService;
import com.util.PageBean;

/**
 * ClassName: ProjTaskService
 * 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月25日
 */
@Service
public class ProjTaskService implements IProjTaskService {
	@Autowired
	ProjTaskMapper projTaskMapper;

	/**
	 * @Description: 分页查询项目任务
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月25日
	 */
	@Override
	public PageBean getPageBean(HttpServletRequest request, ProjTask projTask) {
		// 创建请求参数params,通过StringBuffer对象进行拼接,用append方法拼接,
		StringBuffer params = new StringBuffer();
		//创建hashMap对象
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		//session中获取user对象
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		//根据当前对象获取身份
		String role = projTaskMapper.getRoleCode(user.getUserId());
		//获取当前对象的id
		Integer userId = user.getUserId();
		//把id,code存入map中
		hashMap.put("userid", userId);
		if (role!=null&&role!="") {
			hashMap.put("rolecode", role);
		}
		//前台获取begintime,esexcutor,fprojIdf,classId,outtimemark,endtime属性
		String begintime = request.getParameter("begintime");
		String usercode = request.getParameter("usercode");
		String fprojId = request.getParameter("fprojId");
		String fclassId = request.getParameter("fclassId");
		Integer uid=null;
		if (usercode!=""&&usercode!=null) {
			 uid= projTaskMapper.getUid(usercode);
		
		if (uid!=null&&uid!=null) {
			hashMap.put("excutor", uid);
		}else{
			hashMap.put("excutor", -1);
		}}
		//标记查询快过期任务
		String outtimemark = request.getParameter("outtimemark");
		//类型装换
		if (fprojId != null && fprojId != "") {
			projTask.setProjId(Integer.parseInt(fprojId));
		}
		if (fclassId != null && fclassId != "") {
			projTask.setClassId(Integer.parseInt(fclassId));
		}
		
		String endtime = request.getParameter("endtime");
		if (StringUtils.isNotBlank(begintime)) {
			params = params.append("&begintime=").append(begintime);
		}
		if (StringUtils.isNotBlank(endtime)) {
			params = params.append("&endtime=").append(endtime);
		}
		//map存值
		hashMap.put("begintime", begintime);
		hashMap.put("endtime", endtime);
		hashMap.put("projTask", projTask);
		// 前台传入的当前页码
		String currpage = request.getParameter("currNo");
		// 非空,默认值,设置,以及获取前台当前页码并转码int类型
		int currpages = (currpage != null) ? Integer.parseInt(currpage) : 1;
		// 前台获取每页显示条数
		String pagelimit = request.getParameter("page");// 5
		// 非空,默认值,设置,以及获取前台每页显示条数并转码int类型+
		int pagelimits = (pagelimit != null) ? Integer.parseInt(pagelimit) : 5;
		// 通过构造方法传入当前页码,每页显示条数,用于计算下标.
		PageBean page = new PageBean(pagelimits, currpages);
		// 创建HashMap对象,封装userInfo,page,用于储存分页查询的条件的属性.
		hashMap.put("page", page);
		// 分页查询的方法,返回List<UserInfo>,
		// 获取总条数,数据库查询
		
		if ("out".equals(outtimemark)) {
			//创建时间格式化对象
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建时间data对象
			Date date1 = new Date();
		//创建操作时间Calendar对象
			Calendar ca = Calendar.getInstance();
		//修改时间
			ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
		//获取修改后的时间
			date1 = ca.getTime();
		//格式时间
			String enddate = format.format(date1);
		//存入map中
			hashMap.put("userid", userId);
			if (role!=null&&role!="") {
				hashMap.put("rolecode", role);
			}
			hashMap.put("outtime", enddate);
			hashMap.put("time", format.format(new Date()));
			if (enddate!=null&&enddate!="") {
				params = params.append("&outtime=").append(enddate);
			}
			if (StringUtils.isNotBlank(format.format(new Date()))) {
				params = params.append("&time=").append(format.format(new Date()));
			}
			
		}
		int totalcount = projTaskMapper.getCount(hashMap);
		List<ProjTask> userList = projTaskMapper.getAllProjTaskByCon(hashMap);
		if ("out".equals(outtimemark)) {
		request.getSession().setAttribute("nearOutTask1", totalcount);
		}
		// 创建请求地址url,并赋值list.action?&currpage=3
		String url = "/projtask/projtasklist.action";
		// 判定属性是否为空StringUtils工具类中的isNotBlank方法判定,
		System.out.println(outtimemark);
			params = params.append("&outtimemark=").append(outtimemark);
		if (projTask!=null&&projTask.getProjId()!=null) {
			params = params.append("&projId=").append(projTask.getProjId());
		}
		if (projTask!=null&&projTask.getClassId()!=null) {
			params = params.append("&classId=").append(projTask.getClassId());
		}
		if (StringUtils.isNotBlank(projTask.getTaskName())) {
			params = params.append("&taskName=").append(projTask.getTaskName());
		}
		if (StringUtils.isNotBlank(usercode)) {
			params = params.append("&usercode=").append(usercode);
		}
		// 通过构造方法给pageBean对象赋值(总条数,当前页码,每页显示条数,分页查询list结果,请求地址,请求参数).
		PageBean pageBean = new PageBean(pagelimits, totalcount, currpages,
				userList, url, params.toString());
		// 返回pageBean对象;
		return pageBean;
	}

	/**
	 * @Description: 添加任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public int addProjTask(HttpServletRequest request, ProjTask projTask) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		int userid = user.getUserId();
		String usercode = request.getParameter("addusercode");
		//创建map对象
		HashMap<String, Object> map = new HashMap<String, Object>();
		//map存入projTask对象,userid
		map.put("projTask", projTask);
		map.put("userid", userid);
		//添加项目任务
		int addProjTask = projTaskMapper.addProjTask(map);
		return addProjTask;
	}

	/**
	 * @Description: 回写修改任务信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public ProjTask getUpdProjTask(HttpServletRequest request, ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskMapper.getUpdProjTask(projTask);
	}

	/**
	 * @Description: 获取所有任务状态
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public SysClass getAlltaskState(HttpServletRequest request,
			ProjTask projTask) {

		return projTaskMapper.getAlltaskState(projTask.getProjId());
	}

	/**
	 * @Description: 修改任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public int updateProjTask(HttpServletRequest request, ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskMapper.updateProjTask(projTask);
	}

	/**
	 * @Description: 根据任务id获取任务信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public ProjTask getDealProjTask(HttpServletRequest request,
			ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskMapper.getDealProjTask(projTask);
	}

	/**
	 * @Description: 删除任务
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public int delProjTask(HttpServletRequest request, ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskMapper.delProjTask(projTask);
	}

	/**
	 * @Description: 查询所有项目code
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public List<SysClass> findAllproj(HttpServletRequest request,
			ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskMapper.findAllproj();
	}

	/**
	 * @Description: 查询所有模块信息
	 * @param @param request
	 * @param @param projTask
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月26日
	 */
	@Override
	public List<SysClass> findAllprojMK(HttpServletRequest request,
			ProjTask projTask) {
		// TODO Auto-generated method stub
		return projTaskMapper.findAllprojMK();
	}

	/**
	 * @Description: 查询所有用户
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月27日
	 */
	@Override
	public List<UserInfo> findAllUser() {
		// TODO Auto-generated method stub
		return projTaskMapper.findAllUser();
	}

	/**
	 * @Description: 获取快过去任务条数
	 * @param @param request
	 * @param @param projTask   
	 * @return   
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月28日
	 */
	@Override
	public int getOutTask(HttpServletRequest request) {
		//创建map对象
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		//session中获取user对象
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		//获取当前用户角色code
		String role = projTaskMapper.getRoleCode(user.getUserId());
		//获取当前用户id
		Integer userId = user.getUserId();
		//创建时间格式化对象
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建时间data对象
			Date date1 = new Date();
		//创建操作时间Calendar对象
			Calendar ca = Calendar.getInstance();
		//修改时间
			ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
		//获取修改后的时间
			date1 = ca.getTime();
		//格式时间
			String enddate = format.format(date1);
		//存入map中
			hashMap.put("userid", userId);
			if (role!=null&&role!="") {
				hashMap.put("rolecode", role);
			}
			hashMap.put("outtime", enddate);
			hashMap.put("time", format.format(new Date()));
			//查询快过期任务条数
		int totalcount = projTaskMapper.getOutTimeCount(hashMap);
		return totalcount;
	}

	/**
	 * @Description: TODO
	 * @param @param request
	 * @param @param userInfo
	 * @param @return   
	 * @return   
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@Override
	public Integer getUserIdByUserCode(HttpServletRequest request,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		return projTaskMapper.getUid(userInfo.getUserCode());
	}

}
