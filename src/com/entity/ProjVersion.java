package com.entity;

import java.util.Date;

/**
 *@comment 版本信息实体类
 *@author changjiaqi
 *@date 2018年7月23日 下午6:24:06
 *@version TODO
 */
public class ProjVersion {
	//版本id
	private Integer versionId;
	//项目主键id
	private Integer projId;
	//版本号
	private String versionNum;
	//版本说明
	private String versionDesc;
	//创建时间
	private Date createTime;
	//创建人
	private Integer createBy;
	//删除标记
	private Integer isDelete;
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public Integer getProjId() {
		return projId;
	}
	public void setProjId(Integer projId) {
		this.projId = projId;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	

}
