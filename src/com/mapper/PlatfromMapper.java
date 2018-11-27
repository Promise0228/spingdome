/**  
 * @Title: PlatfromMapper.java
 * @Package com.item.mapper
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月16日
 */
package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.Message;
import com.entity.ProjTeam;
import com.entity.UserInfo;

/**
 * ClassName: PlatfromMapper 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月16日
 */
public interface PlatfromMapper {

	/**
	 * @Description: 查询所以站内信
	 * @param @return   
	 * @return List<Message>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月16日
	 */
	public List<Message> findMessage(Message message);

	/**
	 * @Description: 站内信条件分页查询
	 * @param @param hashMap
	 * @param @return   
	 * @return List<UserInfo>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月17日
	 */
	public List<Message> findAllMessageByPage(HashMap<String, Object> hashMap);

	/**
	 * @Description: 站内信条件分页查询总条数
	 * @param @param hashMap
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月17日
	 */
	public int getMessageCount(HashMap<String, Object> hashMap);

	/**
	 * @Description: 我的站内信条件分页查询
	 * @param @param hashMap
	 * @param @return   
	 * @return List<MyMessage>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月17日
	 */
	public List<Message> findAllMyMessageByPage(
			HashMap<String, Object> hashMap);

	/**
	 * @Description: 我的站内信条件分页查询总条数
	 * @param @param hashMap
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月17日
	 */
	public Integer getMyMessageCount(HashMap<String, Object> hashMap);

	/**
	 * @Description: 查询所以用户信息
	 * @param @return   
	 * @return List<UserInfo>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public List<UserInfo> findAllUsers();

	/**
	 * @Description: 根据usercode查询用户信息
	 * @param @param userInfo
	 * @param @return   
	 * @return List<UserInfo>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public List<UserInfo> findUserByCode(UserInfo userInfo);

	/**
	 * @Description: 查询所以项目组信息
	 * @param @return   
	 * @return List<ProjTeam>  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public List<ProjTeam> findAllTeams();

	/**
	 * @Description: 添加站内信
	 * @param @param message
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月21日
	 */
	public int addMessage(Message message);

	/**
	 * @Description: 根据用户code查询用户id
	 * @param @param string   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月22日
	 */
	public int getMsgId(String string);

	/**
	 * @Description: 添加站内信关系表数据
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月22日
	 */
	public int addUserMessage(HashMap<String, Object> map);

	/**
	 * @Description: 查询所有用户code
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月22日
	 */
	public String findAllUserCode();

	/**
	 * @Description: 查询所有项目组code
	 * @param @param string
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月22日
	 */
	public String findUserIdsByTeam(String string);

	/**
	 * @Description: 根据msgid查询所有fromusercode
	 * @param @param message
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月23日
	 */
	public String findUserCodeByMsgid(Message message);

	/**
	 * @Description: 查询父id
	 * @param @param msgid   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月23日
	 */
	public int getParentId(int msgid);

	/**
	 * @Description: 根据msgid查询所有tousercode
	 * @param @param message
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public String findUserCodesByMsgid(Message message);

	/**
	 * @Description: 查询所有未读信息条数
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public int findnoreadcount();

	/**
	 * @Description: 查询所有未读信息条数
	 * @param @param map
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public int getCount(HashMap<String, Object> map);

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
	 * @Description: 获取当前用户的角色code
	 * @param @param userid   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月24日
	 */
	public String getRoleCode(int userid);

	

}
