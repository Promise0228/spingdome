package com.service.impl;

import java.util.List;
import java.util.Map;

import com.entity.ProjTask;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.TeamMember;
import com.entity.UserInfo;

public interface IProjectTeamService {

	/**
	 * 查询用户对应的角色
	 * @param userId
	 * @return
	 */
	List<Role> getRole(int userId);

	/**
	 * 查询用户组信息
	 * @param map
	 * @return
	 */
	List<ProjTeam> findProjTeamAll(Map map);

	/**
	 * 查询总条数
	 * @param map
	 * @return
	 */
	Integer findCountProjTeamAll(Map map);

	/**
	 * 根据角色id及用户id查询用户所在的项目
	 * @param map
	 * @return
	 */
	List<ProjectInfo> findProjInfo(Map map);

	/**
	 * 查询项目下所有成员
	 * @param projectInfo
	 * @return
	 */
	List<UserInfo> findUserByProjId(ProjectInfo projectInfo);

	/**
	 * 创建项目组
	 * @param projTeam
	 * @return
	 */
	int insertProjTeam(ProjTeam projTeam);

	/**
	 * 查询项目组是否存在
	 * @param projTeam
	 * @return
	 */
	int getCountTeam(ProjTeam projTeam);

	/**
	 * 查询用户组成员
	 * @param projTeam
	 * @return
	 */
	List<UserInfo> findUserByProjTeamId(Map map);

	/**
	 * 查询开发部成员
	 * @return
	 */
	List<UserInfo> findUserBy();

	/**
	 * 添加项目组成员
	 * @param teamMember
	 * @return
	 */
	int addTeamUser(TeamMember teamMember);

	/**
	 * 查询总条数
	 * @param map
	 * @return
	 */
	Integer findCountTeamAll(Map map);

	/**
	 * 删除项目组成员
	 * @param teamMember
	 * @return
	 */
	int deleteTeamUser(TeamMember teamMember);

	/**
	 * 查询分配人员所在用户组之外的用户组
	 * @param userInfo
	 * @return
	 */
	List<ProjTeam> selectProjTask(UserInfo userInfo);

	/**
	 * 查询成员执行任务
	 * @param map
	 * @return
	 */
	List<ProjTask> findTaskId(Map map);

	/**
	 * 修改任务状态
	 * @param taskId
	 */
	int updateTaskState(int taskId);

	/**
	 * 将执行人重置为空
	 * @param taskId
	 */
	int updateExcutor(int taskId);

	/**
	 * 修改项目成员表
	 * @param userIds
	 * @return
	 */
	int deleteTeamMember(int userIds);

	int insertTeamMember(Map map);

}
