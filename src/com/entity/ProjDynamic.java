/**
 * 
 */
package com.entity;

import java.util.List;

/**
 *@comment 项目动态实体类
 *@author Administrator
 *@date 2018年7月25日 上午9:02:57
 *@modifyUser Administrator
 *@modifyDate 2018年7月25日 上午9:02:57
 *@modifyDesc  TODO
 *@version 1.0
 */
public class ProjDynamic {
	//项目动态Id
	private Integer dynamicId;
	//项目Id
	private Integer projId;
	//项目动态描述
	private String dynamicDesc;
	//创建时间
	private String createTime;
	//创建人名
	private String userName;
	//动态回复父Id
	private Integer parentId;
	//是否删除
	private String isDelete;
	//下一级回复列表
	private List<ProjDynamic> secondList;
	//若是项目，则表示此项目下所有回复；若是评论，则为空
	private List<ProjDynamic> allComments;
	
	public ProjDynamic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getDynamicDesc() {
		return dynamicDesc;
	}

	public void setDynamicDesc(String dynamicDesc) {
		this.dynamicDesc = dynamicDesc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<ProjDynamic> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<ProjDynamic> secondList) {
		this.secondList = secondList;
	}

	public List<ProjDynamic> getAllComments() {
		return allComments;
	}

	public void setAllComments(List<ProjDynamic> allComments) {
		this.allComments = allComments;
	}

	
}
