package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.BugInfo;
import com.entity.BugReply;
import com.entity.ProjTeam;
import com.entity.ProjectInfo;
import com.entity.Role;
import com.entity.SysClass;
import com.entity.UserInfo;
import com.mapper.BugInfoMapper;
import com.service.impl.IBugInfoService;

/**
 * 
 * @author 31886
 *
 */
@Service
public class BugInfoService implements IBugInfoService{
//注入mapper层
	@Autowired
	BugInfoMapper bugInfoMapper;

	
	@Override
	public Role getRoleIdByUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getRoleIdByUserInfo(user);
	}

	@Override
	public ProjTeam getProjTeamByUserId(UserInfo user) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getProjTeamByUserId(user);
	}

	@Override
	public List<SysClass> selectClassId() {
		// TODO Auto-generated method stub
		return bugInfoMapper.selectClassId();
	}

	@Override
	public List<ProjectInfo> selectProjectName() {
		// TODO Auto-generated method stub
		return bugInfoMapper.selectProjectName();
	}

	@Override
	public BugInfo findBugInfoByBugNumber(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findBugInfoByBugNumber(buginfo);
	}

	@Override
	public int addBug(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.addBug(buginfo);
	}

	@Override
	public int delBug(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.delBug(buginfo);
	}

	@Override
	public int updateStartBug(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.updateStartBug(buginfo);
	}

	@Override
	public int updateForbidBug(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.updateForbidBug(buginfo);
	}

	@Override
	public List<UserInfo> findUserIdByTeamId(int teamIds) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findUserIdByTeamId(teamIds);
	}

	@Override
	public List<ProjTeam> findTeamIdByProjId(int projId) {
		return bugInfoMapper.findTeamIdByProjId(projId);
	}

	@Override
	public int updateAllocationBug(BugInfo bugInfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.updateAllocationBug(bugInfo);
	}

	@Override
	public List<BugInfo> findBug(Map map) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findBug(map);
	}

	@Override
	public String findTest() {
		// TODO Auto-generated method stub
		return bugInfoMapper.findTest();
	}

	@Override
	public int findBugSum(Map map) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findBugSum(map);
	}


	@Override
	public List<SysClass> findClassByProj(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findClassByProj(projectInfo);
	}

	@Override
	public List<ProjectInfo> getProjByUser(Map map) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getProjByUser(map);
	}

	@Override
	public int getProjIdByNum(String str) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getProjIdByNum(str);
	}

	@Override
	public String getUserIdByName(String str) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getUserIdByName(str);
	}

	@Override
	public String getBugIdByNum(String str) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getBugIdByNum(str);
	}

	@Override
	public int updateBugByNum(BugInfo bugInfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.updateBugByNum(bugInfo);
	}

	@Override
	public List<BugInfo> getBugByNum(BugInfo bugInfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.getBugByNum(bugInfo);
	}

	@Override
	public List<BugReply> findReplyByNum(BugInfo bugInfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findReplyByNum(bugInfo);
	}

	@Override
	public int addReply(BugReply bugReply) {
		// TODO Auto-generated method stub
		return bugInfoMapper.addReply(bugReply);
	}

	@Override
	public int delReply(BugReply bugReply) {
		// TODO Auto-generated method stub
		return bugInfoMapper.delReply(bugReply);
	}

	@Override
	public List<BugInfo> findMyBug(Map map) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findNoBug(map);
	}

	@Override
	public int findMyBugSum(Map map) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findNoBugSum(map);
	}

	@Override
	public int findBugSumByProj(int i) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findBugSumByProj(i);
	}

	@Override
	public int findNoBugByProj(int i) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findNoBugByProj(i);
	}

	@Override
	public int findBugByProj(int i) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findBugByProj(i);
	}

	@Override
	public int findNoBugSums(Map map) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findNoBugSums(map);
	}

	@Override
	public List<BugInfo> findBugNameByBugName(BugInfo bugInfo) {
		// TODO Auto-generated method stub
		return bugInfoMapper.findBugNameByBugName(bugInfo);
	}

	
}
