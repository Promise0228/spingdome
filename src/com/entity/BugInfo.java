package com.entity;

/**
 * bug详细表
 * 
 * @author 31886
 * 
 */
public class BugInfo {
	//bugid
	private Integer bugId;
	//项目主键id
	
	private Integer projId;

	//class_id
	private Integer classId;

	//bug名称
	private String bugTitle;

	//bug编号
	private String bugNum;

	//bug描述
	private String bugDesc;

	//bug状态
	private Integer bugState;

	//bug级别
	private Integer bugLevel;

	//创建时间
	private String createTime;
	
	private String createTime1;

	//创建人
	private Integer createBy;

	//执行人
	private Integer updateBy;

	//删除标记
	private Integer isDelete;

	//附件
	private String fileUrl;
	
	private String updateBys;
	
	private String classIds;
	
	 private String projIds;
	 
	 private String projNum;

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public int getProjId() {
		return projId;
	}

	public void setProjId(int projId) {
		this.projId = projId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugNum() {
		return bugNum;
	}

	public void setBugNum(String bugNum) {
		this.bugNum = bugNum;
	}

	public String getBugDesc() {
		return bugDesc;
	}

	public void setBugDesc(String bugDesc) {
		this.bugDesc = bugDesc;
	}

	public Integer getBugState() {
		return bugState;
	}

	public void setBugState(Integer bugState) {
		this.bugState = bugState;
	}

	public Integer getBugLevel() {
		return bugLevel;
	}

	public void setBugLevel(Integer bugLevel) {
		this.bugLevel = bugLevel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getUpdateBys() {
		return updateBys;
	}

	public void setUpdateBys(String updateBys) {
		this.updateBys = updateBys;
	}

	public String getClassIds() {
		return classIds;
	}

	public void setClassIds(String classIds) {
		this.classIds = classIds;
	}

	public String getProjIds() {
		return projIds;
	}

	public void setProjIds(String projIds) {
		this.projIds = projIds;
	}

	public String getProjNum() {
		return projNum;
	}

	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}

	@Override
	public String toString() {
		return "BugInfo [bugId=" + bugId + ", projId=" + projId + ", classId="
				+ classId + ", bugTitle=" + bugTitle + ", bugNum=" + bugNum
				+ ", bugDesc=" + bugDesc + ", bugState=" + bugState
				+ ", bugLevel=" + bugLevel + ", createTime=" + createTime
				+ ", createTime1=" + createTime1 + ", createBy=" + createBy
				+ ", updateBy=" + updateBy + ", isDelete=" + isDelete
				+ ", fileUrl=" + fileUrl + ", updateBys=" + updateBys
				+ ", classIds=" + classIds + ", projIds=" + projIds
				+ ", projNum=" + projNum + "]";
	}

	
	


}
