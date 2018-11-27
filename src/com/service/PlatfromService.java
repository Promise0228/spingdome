/**  
 * @Title: PlatfromService.java
 * @Package com.item.service
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月16日
 */
package com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Message;
import com.entity.ProjTeam;
import com.entity.UserInfo;
import com.mapper.PlatfromMapper;
import com.service.impl.IPlatfromService;
import com.util.PageBean;

/**
 * ClassName: PlatfromService
 * 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月16日
 */
@Service
public class PlatfromService implements IPlatfromService {
	@Autowired
	PlatfromMapper platfromMapper;

	/**
	 * 
	 * @Description: 分页查询站内信的封装的方法
	 * @param @param request userInfo
	 * @param @return
	 * @return PageBean
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月4日
	 */
	@Override
	public PageBean getPlatfromPageBean(HttpServletRequest request,
			Message message) {
		System.out.println(message.getReadstate());
		// 创建请求参数params,通过StringBuffer对象进行拼接,用append方法拼接,
		StringBuffer params = new StringBuffer();
		// 创建HashMap对象,封装userInfo,page,用于储存分页查询的条件的属性.
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// 封装message用于储存分页查询的条件的属性.
		if (message != null) {
			hashMap.put("message", message);
		}
		// 前台获取exprot,begintime,endtime,readstate属性
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserInfo");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String sendtype = request.getParameter("fsendtype");
		if (sendtype != null && sendtype != "") {
			hashMap.put("sendtype", Integer.parseInt(sendtype));
		} else {
			hashMap.put("sendtype", sendtype);
		}
		String roleCode = platfromMapper.getRoleCode(userInfo.getUserId());
		// 拼接查询时间的参数字符串,起始时间,终止时间,
		if (StringUtils.isNotBlank(begintime)) {
			params = params.append("&begintime=").append(begintime);
		}
		if (StringUtils.isNotBlank(endtime)) {
			params = params.append("&endtime=").append(endtime);
		}
		System.out.println(userInfo.getUserId());
		// 当时间为空时给时间设置默认值
		// 前台获取exprot,begintime,endtime,readstate属性放入hashMap中
		hashMap.put("begintime", begintime);
		hashMap.put("endtime", endtime);
		hashMap.put("userid", userInfo.getUserId());
		hashMap.put("rolecode", roleCode);
		// 获取总条数,数据库查询
		int totalcount = platfromMapper.getMessageCount(hashMap);
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
		List<Message> userList = null;
		if (platfromMapper.findAllMessageByPage(hashMap) != null) {
			userList = platfromMapper.findAllMessageByPage(hashMap);
		}
		// 创建请求地址url,并赋值list.action?&currpage=3
		String url = "/platfrom/platfromlist.action";
		// 判定属性是否为空StringUtils工具类中的isNotBlank方法判定,

		if (StringUtils.isNotBlank(message.getMsgtitle())) {
			params = params.append("&msgtitle=").append(message.getMsgtitle());
		}
		if (StringUtils.isNotBlank(sendtype)) {
			params = params.append("&sendtype=").append(sendtype);
		}
		// 通过构造方法给pageBean对象赋值(总条数,当前页码,每页显示条数,分页查询list结果,请求地址,请求参数).
		PageBean pageBean = new PageBean(pagelimits, totalcount, currpages,
				userList, url, params.toString());
		// 返回pageBean对象;
		return pageBean;
	}

	/**
	 * @Description: 查询所以站内信信息
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月16日
	 */
	@Override
	public List<Message> findMessage(Message message) {
		return platfromMapper.findMessage(message);
	}

	/**
	 * @Description: 分页查询我的站内信的封装的方法
	 * @param @param request
	 * @param @param message
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月17日
	 */
	@Override
	public PageBean getMyPlatfromPageBean(HttpServletRequest request,
			Message message) {
		// 创建请求参数params,通过StringBuffer对象进行拼接,用append方法拼接,
		StringBuffer params = new StringBuffer();
		// 创建HashMap对象,封装userInfo,page,用于储存分页查询的条件的属性.
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// 封装message用于储存分页查询的条件的属性.
		hashMap.put("message", message);
		// 前台获取exprot,begintime,endtime,readstate属性
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserInfo");
		String exprot = request.getParameter("exprot");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");

		// 拼接查询时间的参数字符串,起始时间,终止时间,
		if (StringUtils.isNotBlank(begintime)) {
			params = params.append("&begintime=").append(begintime);
		}
		if (StringUtils.isNotBlank(endtime)) {
			params = params.append("&endtime=").append(endtime);
		}
		// 当时间为空时给时间设置默认值
		// 前台获取exprot,begintime,endtime,readstate属性放入hashMap中
		hashMap.put("exprot", exprot);
		hashMap.put("begintime", begintime);
		hashMap.put("endtime", endtime);
		hashMap.put("userid", userInfo.getUserId());
		// 获取总条数,数据库查询
		Integer totalcount = platfromMapper.getMyMessageCount(hashMap);
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
		List<Message> userList = platfromMapper.findAllMyMessageByPage(hashMap);
		// 创建请求地址url,并赋值list.action?&currpage=3
		String url = "/platfrom/myplatfromlist.action";
		// 判定属性是否为空StringUtils工具类中的isNotBlank方法判定,

		if (StringUtils.isNotBlank(message.getMsgtitle())) {
			params = params.append("&msgtitle=").append(message.getMsgtitle());
		}
		if (StringUtils.isNotBlank(message.getSendtype())) {
			params = params.append("&sendtype=").append(message.getSendtype());
		}
		if (StringUtils.isNotBlank(message.getReadstate())) {
			params = params.append("&readstate=")
					.append(message.getReadstate());
		}
		// 通过构造方法给pageBean对象赋值(总条数,当前页码,每页显示条数,分页查询list结果,请求地址,请求参数).
		PageBean pageBean = new PageBean(pagelimits, totalcount, currpages,
				userList, url, params.toString());
		// 返回pageBean对象;
		return pageBean;
	}

	/**
	 * @Description: 查询所以用户信息
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	@Override
	public List<UserInfo> findAllUsers() {
		return platfromMapper.findAllUsers();
	}

	/**
	 * @Description: 根据用户code模糊查询用户信息
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	@Override
	public List<UserInfo> findUserByCode(UserInfo userInfo) {
		return platfromMapper.findUserByCode(userInfo);
	}

	/**
	 * @Description: 查询所以项目组信息
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	@Override
	public List<ProjTeam> findAllTeams() {
		return platfromMapper.findAllTeams();
	}

	/**
	 * @Description: 插入站内信以及站内信关系表数据
	 * @param @param message
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	@Override
	public int addMessage(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, Message message) {
		// 创建map对象
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 获取session中用户的user对象
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("UserInfo");
		message.setUserid(user.getUserId());
		// 定义返回值变量
		int addUserMessage = 0;
		// 插入站内信信息
		String fileUpload = fileUpload(file, request);
		message.setFileurl(fileUpload);
		int addMessage = platfromMapper.addMessage(message);
		String usercode = "," + platfromMapper.findAllUserCode() + ",";
		if (addMessage > 0) {// 如果addMessage > 0 :插入站内信信息成功
			// 获取返回值msgid 并放入map中
			map.put("msgid", message.getMsgid());
			// 如果获取的session中user不为空且获取的code存在
			if (user != null && user.getUserCode() != "") {
				// 根据code查询该用户的id 并放入map中
				map.put("fuserid", platfromMapper.getMsgId(user.getUserCode()));
				// 前端获取接收人code拼接字符串
				String touser = request.getParameter("touser");
				// 处理字符串
				String[] split = touser.split(",");
				// 遍历数组
				for (String string : split) {
					if (string != null && string != "") {

						if (usercode.indexOf("," + string + ",") > -1) {
							// 根据code查询该用户的id 并放入map中
							int msgId = platfromMapper.getMsgId(string);
							if (msgId > 0) {
								map.put("tuserid", msgId);
								// 插入到站内信关系表中
								addUserMessage = platfromMapper.addUserMessage(map);
							}
						} else if (string!=null) {
							String userid = platfromMapper.findUserIdsByTeam(string) + ",";
							String[] split2 = userid.split(",");
							for (String string2 : split2) {
								System.out.println("String2"+string2);
								if (string2.length()>0) {
									System.out.println("ceshi---------");
									// 根据code查询该用户的id 并放入map中
									map.put("tuserid", string2);
									// 插入到站内信关系表中
									addUserMessage = platfromMapper.addUserMessage(map);
								}
							}
						}
					}
				}
			}
		}
		return addUserMessage;
	}

	/**
	 * @Description: 根据msgid查询所有fromuser的code
	 * @param @param message
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月23日
	 */
	@Override
	public String findUserCodeByMsgid(Message message) {
		// TODO Auto-generated method stub
		return platfromMapper.findUserCodeByMsgid(message);
	}

	/**
	 * @Description: TODO
	 * @param @param message
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	@Override
	public String findUserCodesByMsgid(Message message) {
		// TODO Auto-generated method stub
		return platfromMapper.findUserCodesByMsgid(message);
	}

	/**
	 * @Description: 根据msgid查询所有touser的code
	 * @param @param message
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	@Override
	public int getCount(HttpServletRequest request) {
		// TODO Auto-generated method stub
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserId());
		return platfromMapper.getCount(map);
	}

	/**
	 * @Description: 标记全部已读
	 * @param
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	@Override
	public void allMarkRead() {
		// TODO Auto-generated method stub
		platfromMapper.allMarkRead();
	}

	/**
	 * @Description: 查询所有用户code
	 * @param @return
	 * @return
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月25日
	 */
	@Override
	public String findAllUserCode() {
		// TODO Auto-generated method stub
		return platfromMapper.findAllUserCode();
	}

	/**
	 * 
	 * @Description: 文件上传
	 * @param @param file
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	public String fileUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		// 判断文件是否为空
		String filePath = "";
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				// file.getOriginalFilename() 获取上传文件的原始名称
				filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "upload/" + file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 重定向
		return file.getOriginalFilename();
	}

}
