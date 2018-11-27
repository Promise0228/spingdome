package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entity.BugInfo;
import com.entity.BugReply;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.SysClass;
import com.entity.TeamMember;
import com.entity.UserInfo;

/**
 * 
 * @author 31886
 *
 */
public interface IBugInfoService {
	/**
	 * 
	 * @comment 
	 * @return
	 * @version 1.0
	 * List<BugInfo>
	 */
	public List<BugInfo> findBug(Map map);
	/**
	 * 查询bug总数
	 * @comment 
	 * @param map
	 * @return
	 * @version 1.0
	 * int
	 */
	public int findBugSum(Map map);
	/**
	 * 查询所有测试组
	 * @comment 
	 * @return
	 * @version 1.0
	 * String
	 */
	public String findTest();
	/**
	 * 根据用户id查询角色
	 * @comment 
	 * @return
	 * @version 1.0
	 * String
	 */
	public Role getRoleIdByUserInfo(UserInfo user);
	/**
	 * 根据用户id查询用户所在项目组
	 * @comment 
	 * @param user
	 * @return
	 * @version 1.0
	 * TeamMember
	 */
	public ProjTeam getProjTeamByUserId(UserInfo user);
	/**
	 * 查询所有项目
	 * @comment 
	 * @return
	 * @version 1.0
	 * List<SysClass>
	 */
	public List<ProjectInfo> selectProjectName();
	/**
	 * 查询所有模块
	 * @comment 
	 * @return
	 * @version 1.0
	 * List<BugInfo>
	 */
	public List<SysClass> selectClassId();
	/**
	 * bug编号唯一效验
	 * @comment 
	 * @return
	 * @version 1.0
	 * BugInfo
	 */
	public BugInfo findBugInfoByBugNumber(BugInfo buginfo);
	/**
	 * 添加bug
	 * @comment 
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * int
	 */
	public int addBug(BugInfo buginfo);
	/**
	 * 删除bug
	 * @comment 
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * int
	 */
	public int delBug(BugInfo buginfo);
	/**
	 * 启动bug
	 * @comment 
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * int
	 */
	public int updateStartBug(BugInfo buginfo);
	/**
	 * 关闭bug
	 * @comment 
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * int
	 */
	public int updateForbidBug(BugInfo buginfo);
	/**
	 * 查询项目组成员
	 * @comment 
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * List<UserInfo>
	 */
	public List<UserInfo> findUserIdByTeamId(int teamIds);
	/**
	 * 查询项目组
	 * @comment 
	 * @param buginfo
	 * @return
	 * @version 1.0
	 * List<UserInfo>
	 */
	public List<ProjTeam> findTeamIdByProjId(int projId);
	/**
	 * 分配bug
	 * @comment 
	 * @param bugInfo
	 * @return
	 * @version 1.0
	 * int
	 */
	public int updateAllocationBug(BugInfo bugInfo);
	/**
	 * 根据用户查项目
	 * @comment 
	 * @param userInfo
	 * @return
	 * @version 1.0
	 * List<ProjTeam>
	 */
	public List<ProjectInfo> getProjByUser(Map map);
	/**
	 * 根据项目查询模块
	 * @comment 
	 * @param projectInfo
	 * @return
	 * @version 1.0
	 * List<ProjectInfo>
	 */
	public List<SysClass> findClassByProj(ProjectInfo projectInfo);
	/**
	 * 根据项目num查询项目id
	 * @comment 
	 * @param str
	 * @return
	 * @version 1.0
	 * String
	 */
	public int getProjIdByNum(String str);
	/**
	 * 根据执行人name查询执行人id
	 * @comment 
	 * @param str
	 * @return
	 * @version 1.0
	 * String
	 */
	public String getUserIdByName(String str);
	/**
	 * 根据模块name查询模块id
	 * @comment 
	 * @param str
	 * @return
	 * @version 1.0
	 * String
	 */
	public String getBugIdByNum(String str);
	/**
	 * 更改bug
	 * @comment 
	 * @param bugInfo
	 * @return
	 * @version 1.0
	 * int
	 */
	public int updateBugByNum(BugInfo bugInfo);
	/**
	 * 根据num查询bug
	 * @comment 
	 * @param bugInfo
	 * @return
	 * @version 1.0
	 * List<BugInfo>
	 */
    public List<BugInfo> getBugByNum(BugInfo bugInfo);
    /**
     * 查询备注
     * @comment 
     * @param bugInfo
     * @return
     * @version 1.0
     * List<BugReply>
     */
    public List<BugReply> findReplyByNum(BugInfo bugInfo);
    /**
     * 增加留言
     * @comment 
     * @param bugReply
     * @return
     * @version 1.0
     * int
     */
    public int addReply(BugReply bugReply);
    /**
     * 删除留言
     * @comment 
     * @param bugReply
     * @return
     * @version 1.0
     * int
     */
    public int delReply(BugReply bugReply);
    /**
   	 * 查询我的bug
   	 * @comment 
   	 * @return
   	 * @version 1.0
   	 * List<BugInfo>
   	 */
   	public List<BugInfo> findMyBug(Map map);
   	/**
   	 * 查询我的bug总数
   	 * @comment 
   	 * @param map
   	 * @return
   	 * @version 1.0
   	 * int
   	 */
   	public int findMyBugSum(Map map); 
   	/**
	 * 项目中的总bug
	 * @comment 
	 * @param i
	 * @return
	 * @version 1.0
	 * int
	 */
	public int findBugSumByProj(int i);
	/**
	 * 项目中的未解决bug
	 * @comment 
	 * @param i
	 * @return
	 * @version 1.0
	 * int
	 */
	public int findNoBugByProj(int i);
	/**
	 * 项目中的已解决bug
	 * @comment 
	 * @param i
	 * @return
	 * @version 1.0
	 * int
	 */
	public int findBugByProj(int i);
	/**
	 * 查询我的bug总数(未解决)
	 * @comment 
	 * @param map
	 * @return
	 * @version 1.0
	 * int
	 */
	public int findNoBugSums(Map map); 
	/**
	 * bug名自动补全
	 * @comment 
	 * @param bugInfo
	 * @return
	 * @version 1.0
	 * List<BugInfo>
	 */
	public List<BugInfo> findBugNameByBugName(BugInfo bugInfo);
}

