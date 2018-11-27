package com.entity;


public class ProjectInfo {
    private Integer projId;

    private Integer classId;

    private String projName;

    private String projNum;

    private String vistorType;

    private String createTime;

    private Integer createBy;

    private String startTime;

    private String endTime;

    private Float ableDay;

    private String projState;

    private Integer projChief;

    private String remark;

    private String projDesc;

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjNum() {
		return projNum;
	}

	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}

	public String getVistorType() {
		return vistorType;
	}

	public void setVistorType(String vistorType) {
		this.vistorType = vistorType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Float getAbleDay() {
		return ableDay;
	}

	public void setAbleDay(Float ableDay) {
		this.ableDay = ableDay;
	}

	public String getProjState() {
		return projState;
	}

	public void setProjState(String projState) {
		this.projState = projState;
	}

	public Integer getProjChief() {
		return projChief;
	}

	public void setProjChief(Integer projChief) {
		this.projChief = projChief;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjDesc() {
		return projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}

	@Override
	public String toString() {
		return "ProjectInfo [projId=" + projId + ", classId=" + classId
				+ ", projName=" + projName + ", projNum=" + projNum
				+ ", vistorType=" + vistorType + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", ableDay=" + ableDay
				+ ", projState=" + projState + ", projChief=" + projChief
				+ ", remark=" + remark + ", projDesc=" + projDesc + "]";
	}

    
    
}