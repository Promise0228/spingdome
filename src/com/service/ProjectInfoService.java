/**
 * 
 */
package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.ProjectInfo;
import com.entity.ProjDynamic;
import com.entity.SysClass;
import com.entity.UserInfo;
import com.mapper.ProjectInfoMapper;
import com.mapper.UserInfoMapper;
import com.service.impl.IProjectInfoService;
import com.util.PageBean;

/**
 *@comment
 *@author Administrator
 *@date 2018年7月19日 下午5:15:25
 *@modifyUser Administrator
 *@modifyDate 2018年7月19日 下午5:15:25
 *@modifyDesc  TODO
 *@version 1.0
 */
@Service
public class ProjectInfoService implements IProjectInfoService{
	@Autowired
	ProjectInfoMapper projMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	/**
	 * 
	 *@comment 根据用户及项目输入条件查询所有项目
	 *@author Administrator
	 *@date 2018年7月19日 下午5:14:23
	 *@param user
	 *@return
	 *List<Proj>
	 *@version 1.0
	 */
	@Override
	public List<ProjectInfo> findProjs(HashMap map) {
		// TODO Auto-generated method stub
		return projMapper.findProjs(map);
	}
	
	/**
	 * 
	 *@comment 根据用户及项目输入条件查询项目总条数
	 *@author Administrator
	 *@date 2018年7月19日 下午6:18:24
	 *@param map
	 *@return
	 *int
	 *@version 1.0
	 */
	@Override
	public int getProjsNumber(HashMap map) {
		// TODO Auto-generated method stub
		return projMapper.getProjsNumber(map);
	}
	
	/**
	 * 
	 *@comment 查询所有项目经理
	 *@author Administrator
	 *@date 2018年7月20日 上午10:47:49
	 *@return
	 *List<UserInfo>
	 *@version 1.0
	 */
	@Override
	public List<UserInfo> findProjManagers() {
		// TODO Auto-generated method stub
		return userInfoMapper.findProjManagers();
	}

	/**
	 * 
	 *@comment 添加项目
	 *@author Administrator
	 *@date 2018年7月20日 下午11:11:50
	 *@param request
	 *@return
	 *int
	 *@version 1.0
	 */
	@Override
	public int addProj(HttpServletRequest request,ProjectInfo proj) {
		// TODO Auto-generated method stub
		UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
		HashMap hashMap = new HashMap();
		hashMap.put("user", user);
		hashMap.put("proj", proj);
		return projMapper.addProj(hashMap);
	}

	@Override
	public int checkProj(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return projMapper.checkProj(proj);
	}
	
	/**
	 *@comment 添加白名单
	 *@author Administrator
	 *@date 2018年7月24日 上午9:15:49
	 *@param userIds
	 *@return int
	 *@version 1.0
	 */
	@Override
	public int addWhiteSheet(String userIds,String projId) {
		// TODO Auto-generated method stub
		String[] userIdsArr = userIds.split(",");
		int i;
		HashMap map = new HashMap();
		map.put("projId", projId);
		for( i=0;i<userIdsArr.length;i++){
			map.put("userId", userIdsArr[i]);	
			projMapper.addWhiteSheet(map);
		}
		return i;
	}

	/**
	 *@comment 通过项目Id获取项目
	 *@author Administrator
	 *@date 2018年7月24日 上午10:31:52
	 *@param proj
	 *@return Proj
	 *@version 1.0
	 */
	@Override
	public ProjectInfo getProjById(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return projMapper.getProjById(proj);
	}

	/**
	 *@comment 修改指定id的项目
	 *@author Administrator
	 *@date 2018年7月24日 下午7:10:51
	 *@param proj
	 *@return
	 *int
	 *@version 1.0
	 */
	@Override
	public int updateProj(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return projMapper.updateProj(proj);
	}

	/**
	 *@comment 查询指定项目动态Id的项目动态回复,返回第一层回复
	 *@author Administrator
	 *@date 2018年7月25日 上午9:56:08
	 *@param projId
	 *@param parentId
	 *@return
	 *List<ProjDyna>
	 *@version 1.0
	 */
	@Override
	public List<ProjDynamic> findProjDynaComments(int parentId) {
		// TODO Auto-generated method stub
		List<ProjDynamic> firstProjDynas = projMapper.findProjDynaComments(parentId);
		if(firstProjDynas!=null){
			for(ProjDynamic projDyna :firstProjDynas){
				List<ProjDynamic> secondProjDynas= findProjDynaComments(projDyna.getDynamicId());
				projDyna.setSecondList(secondProjDynas);
			}
		}
		return firstProjDynas;
	}

	/**
	 *@comment 将项目动态的树形回复转换为list集合
	 *@author Administrator
	 *@date 2018年7月25日 下午2:35:16
	 *@param projDynas
	 *@return List<ProjDyna>
	 *@version 1.0
	 */
	public List<ProjDynamic> treeToList(List<ProjDynamic> projDynas) {
		List<ProjDynamic> list=new ArrayList<ProjDynamic>();
		if(projDynas==null){
			list=null;
		}else{
			list.addAll(projDynas);
			for(ProjDynamic projDynaGet:projDynas){
				list.addAll(treeToList(projDynaGet.getSecondList()));
			}
		}
		return list;
	}

	/**
	 *@comment 分页查询项目动态
	 *@author Administrator
	 *@date 2018年7月25日 下午3:06:19
	 *@param projId
	 *@param page
	 *@return List<ProjDyna>
	 *@version 1.0
	 */
	@Override
	public List<ProjDynamic> findProjDynas(ProjDynamic projDynaGet,PageBean page) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("projDyna", projDynaGet);
		map.put("page", page);
		//分页查询项目动态
		List<ProjDynamic> projDynas = projMapper.findProjDynas(map);
		for(ProjDynamic projDyna:projDynas){
			//查询每一个项目动态的树形回复
			List<ProjDynamic> firstComments = findProjDynaComments(projDyna.getDynamicId());
			//对每一个项目动态根据树形回复转换为线性回复集合
			List<ProjDynamic> allComments=treeToList(firstComments);
			/*
			//对回复按照时间排序
			Collections.sort(allComments, new Comparator<ProjDyna>(){
            	public int compare(ProjDyna p1, ProjDyna p2) {
            		//按照ProjDyna的时间进行升序排列
                	return p1.getCreateTime().compareTo(p2.getCreateTime());
            	}
			});
			*/
			//对每一个项目动态加入所有回复
			projDyna.setAllComments(allComments);
		}
		return projDynas;
	}

	/**
	 *@comment 查询项目动态数量
	 *@author Administrator
	 *@date 2018年7月25日 下午6:32:50
	 *@param projId
	 *@param projDyna
	 *@return int
	 *@version 1.0
	 */
	@Override
	public int getProjDynasNum(ProjDynamic projDyna) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("projDyna", projDyna);
		return projMapper.getProjDynasNum(map);
	}

	
	@Override
	public int addProjDynamic(UserInfo user, ProjDynamic projDyna) {
		HashMap map = new HashMap();
		map.put("user", user);
		map.put("projDyna", projDyna);
		projMapper.addProjDynamic(map);
		return 0;
	}

	
	@Override
	public int getProjExpend(ProjectInfo proj) {
		return projMapper.getProjExpend(proj);
	}

	/**
	 * 
	 *@comment 删除项目白名单
	 *@author Administrator
	 *@date 2018年7月26日 下午3:20:18
	 *@param proj
	 *void
	 *@version 1.0
	 */
	@Override
	public int delWhiteSheet(ProjectInfo proj) {
		return projMapper.delWhiteSheet(proj);
	}

	/**
	 * @comment: 查询项目基本信息列表
	 * @return     
	 * @author changjiaqi
	 * @date 2018年7月24日
	 */
	public List<ProjectInfo> findprojectInfo() {
		
		return projMapper.findprojectInfo();
	}
	
	@Override
	public int addSysClass(HashMap map) {
		// TODO Auto-generated method stub
		return projMapper.addSysClass(map);
	}

	@Override
	public SysClass getSysClassByProj(ProjectInfo proj) {
		// TODO Auto-generated method stub
		return projMapper.getSysClassByProj(proj);
	}
}
