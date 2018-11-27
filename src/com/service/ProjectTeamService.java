package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.ProjTask;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.TeamMember;
import com.entity.UserInfo;
import com.mapper.ProjectTeamMapper;
import com.service.impl.IProjectTeamService;
@Service
public class ProjectTeamService implements IProjectTeamService {

	@Autowired ProjectTeamMapper projectTeamMapper;
	/**
	 * 查询用户角色
	 */
	@Override
	public List<Role> getRole(int userId) {
		// TODO Auto-generated method stub
		return projectTeamMapper.getRole(userId);
	}
	/**
	 * 查询用户组信息
	 */
	@Override
	public List<ProjTeam> findProjTeamAll(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.findProjTeamAll(map);
	}
	/**
	 * 查询用户组条数
	 */
	@Override
	public Integer findCountProjTeamAll(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.countProjTeamAll(map);
	}
	/**
	 * 根据角色id及用户id查询用户所在的项目
	 */
	@Override
	public List<ProjectInfo> findProjInfo(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.findProjInfo(map);
	}
	/**
	 * 查询项目下所有成员
	 */
	@Override
	public List<UserInfo> findUserByProjId(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		return projectTeamMapper.findUserByProjId(projectInfo);
	}
	/**
	 * 创建项目组
	 */
	@Override
	public int insertProjTeam(ProjTeam projTeam) {
		// TODO Auto-generated method stub
		return projectTeamMapper.insertProjTeam(projTeam);
	}
	/**
	 * 查询项目组是否存在
	 */
	@Override
	public int getCountTeam(ProjTeam projTeam) {
		// TODO Auto-generated method stub
		return projectTeamMapper.getCountTeam(projTeam);
	}
	/**
	 * 查询用户组成员
	 */
	@Override
	public List<UserInfo> findUserByProjTeamId(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.findUserByProjTeamId(map);
	}
	
	@Override
	public List<UserInfo> findUserBy() {
		// TODO Auto-generated method stub
		return projectTeamMapper.findUserBy();
	}
	/**
	 * 添加项目组成员
	 */
	@Override
	public int addTeamUser(TeamMember teamMember) {
		// TODO Auto-generated method stub
		return projectTeamMapper.addTeamUser(teamMember);
	}
	/**
	 * 查询总条数
	 */
	@Override
	public Integer findCountTeamAll(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.findCountTeamAll(map);
	}
	/**
	 * 删除项目组成员
	 */
	@Override
	public int deleteTeamUser(TeamMember teamMember) {
		// TODO Auto-generated method stub
		return projectTeamMapper.deleteTeamUser(teamMember);
	}
	/**
	 * 查询分配人员所在用户组之外的用户组
	 */
	@Override
	public List<ProjTeam> selectProjTask(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return projectTeamMapper.selectProjTask(userInfo);
	}
	/**
	 * 查询成员执行任务
	 */
	@Override
	public List<ProjTask> findTaskId(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.findTaskId(map);
	}
	/**
	 * 修改任务状态
	 */
	@Override
	public int updateTaskState(int taskId) {
		return projectTeamMapper.updateTaskState(taskId) ;
		
	}
	/**
	 * 将任务执行人重置为空
	 */
	@Override
	public int updateExcutor(int taskId) {
		return projectTeamMapper.updateExcutor(taskId);
		
	}
	/**
	 * 修改项目成员表
	 */
	@Override
	public int deleteTeamMember(int userIds) {
		// 删除表数据
		return projectTeamMapper.deleteTeamMember(userIds);
			//添加表数据
		
	}
	@Override
	public int insertTeamMember(Map map) {
		// TODO Auto-generated method stub
		return projectTeamMapper.insertTeamMember(map);
	}
	

}
