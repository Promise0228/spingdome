package com.entity;

import java.util.Date;

public class TestExamp {
	// 用例主键id
	private Integer testId;
	// 项目主键id
	private Integer projId;
	// 测试类型
	private String testType;
	// 用例主题
	private String testTitle;
	// 测试结果
	private String result;
	// 执行人
	private String executor;
	// 附件
	private String fileUrl;
	// 创建时间
	private Date createTime;
	// 创建人
	private Integer createBy;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 状态
	private String testState;
	// 删除标记
	private String isDelete;
	// 用例描述
	private String testDesc;
	//项目名称
	private String projName;

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType == null ? null : testType.trim();
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle == null ? null : testTitle.trim();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result == null ? null : result.trim();
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor == null ? null : executor.trim();
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
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

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState == null ? null : testState.trim();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc == null ? null : testDesc.trim();
	}

}
