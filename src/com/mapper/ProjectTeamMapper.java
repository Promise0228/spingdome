package com.mapper;

import java.util.List;
import java.util.Map;

import com.entity.ProjTask;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.TeamMember;
import com.entity.UserInfo;

public interface ProjectTeamMapper {

	/**
	 * 查询用户角色
	 * @param userId
	 * @return
	 */
	public List<Role> getRole(int userId);

	/**
	 * 查询项目组信息
	 * @param map
	 * @return
	 */
	public List<ProjTeam> findProjTeamAll(Map map);

	/**
	 * 查询项目组信息条数
	 * @param map
	 * @return
	 */
	public Integer countProjTeamAll(Map map);

	/**
	 * 根据角色id及用户id查询用户所在的项目
	 * @param map
	 * @return
	 */
	public List<ProjectInfo> findProjInfo(Map map);

	/**
	 * 查询项目下所有成员
	 * @param projectInfo
	 * @return
	 */
	public List<UserInfo> findUserByProjId(ProjectInfo projectInfo);

	/**
	 * 创建项目组
	 * @param projTeam
	 * @return
	 */
	public int insertProjTeam(ProjTeam projTeam);

	/**
	 * 查询项目组是否存在
	 * @param projTeam
	 * @return
	 */
	public int getCountTeam(ProjTeam projTeam);

	/**
	 * 查询项目组成员
	 * @param projTeam
	 * @return
	 */
	public List<UserInfo> findUserByProjTeamId(Map map);




	/**
	 * 查询开发部成员
	 * @return
	 */
	public List<UserInfo> findUserBy();

	/**
	 * 添加项目组成员
	 * @param teamMember
	 * @return
	 */
	public int addTeamUser(TeamMember teamMember);

	/**
	 * 查询总条数
	 * @param map
	 * @return
	 */
	public Integer findCountTeamAll(Map map);

	/**
	 * 删除用户组成员
	 * @param teamMember
	 * @return
	 */
	public int deleteTeamUser(TeamMember teamMember);

	/**
	 * 查询分配人员所在用户组之外的用户
	 * @param userInfo
	 * @return
	 */
	public List<ProjTeam> selectProjTask(UserInfo userInfo);

	/**
	 * 查询成员执行任务
	 * @param map
	 * @return
	 */
	public List<ProjTask> findTaskId(Map map);

	/**
	 * 修改任务状态为已暂停
	 * @param taskId
	 * @return
	 */
	public int updateTaskState(int taskId);

	/**
	 * 将任务执行人重置为空
	 * @param taskId
	 * @return
	 */
	public int updateExcutor(int taskId);

	/**
	 * 修改项目成员表
	 * @param userIds
	 * @return
	 */
	public int deleteTeamMember(int userIds);
	public int insertTeamMember(Map map);

}
