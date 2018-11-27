/**  
 * @Title: IPlatfromService.java
 * @Package com.item.service
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月16日
 */
package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Message;
import com.entity.ProjTeam;
import com.entity.UserInfo;
import com.util.PageBean;

/**
 * ClassName: IPlatfromService 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月16日
 */
public interface IPlatfromService {
	/**
	 * 
	 * @Description: 站内信分页展示信息
	 * @param @param request
	 * @param @param message
	 * @param @return   
	 * @return PageBean  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	public PageBean getPlatfromPageBean(HttpServletRequest request, Message message);

	/**
	 * @Description: 查询所有站内信
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月16日
	 */
	public List<Message> findMessage(Message message);

	/**
	 * @Description: 我的站内信分页展示信息
	 * @param @param request
	 * @param @param message
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月17日
	 */
	public PageBean getMyPlatfromPageBean(HttpServletRequest request,
			Message message);

	/**
	 * @Description: 查询所有用户信息
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public List<UserInfo> findAllUsers();

	/**
	 * @Description: 根据用户code查询所有用户信息
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public List<UserInfo> findUserByCode(UserInfo userInfo);

	/**
	 * @Description: 查询所有项目组信息
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public List<ProjTeam> findAllTeams();

	/**
	 * @Description: 添加站内信及相关信息
	 * @param @param message
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public int addMessage(@RequestParam("file")MultipartFile file,HttpServletRequest request , Message message);

	/**
	 * @Description: 根据msgid查询fromusercode
	 * @param @param message
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月23日
	 */
	public String findUserCodeByMsgid(Message message);

	/**
	 * @Description: 根据msgid查询tousercode
	 * @param @param message
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public String findUserCodesByMsgid(Message message);

	/**
	 * @Description: 查询未读信息条数
	 * @param @param message
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public int getCount(HttpServletRequest request);

	/**
	 * @Description: 标记所有信息已读
	 * @param    
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public void allMarkRead();

	/**
	 * @Description: 查询所有用户code
	 * @param    
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月25日
	 */
	public String findAllUserCode();

}
