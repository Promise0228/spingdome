/**  
 * @Title: ProjTask.java
 * @Package com.entity
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月25日
 */
package com.entity;


/**
 * ClassName: ProjTask 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月25日
 */
public class ProjTask {
	//任务id
	private Integer taskId;
	//项目主键id
	private Integer projId;
	//任务名称
	private String taskName;
	//任务描述
	private String taskDesc;
	//执行人
	private Integer excutor;
	//任务状态
	private String taskState;
	//创建时间
	private String createTime;
	//创建人
	private Integer createBy;
	//所属模块
	private Integer classId;
	//工时
	private double ableDay;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//计划时间
	private double planTime;
	//实际消耗时间
	private double finishTime;
	//实际进度
	private Integer process;
	//删除标记
	private String isDelete;
	//备注
	private String remark;
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getProjId() {
		return projId;
	}
	public void setProjId(Integer projId) {
		this.projId = projId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public Integer getExcutor() {
		return excutor;
	}
	public void setExcutor(Integer excutor) {
		this.excutor = excutor;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
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
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	public double getAbleDay() {
		return ableDay;
	}
	public void setAbleDay(double ableDay) {
		this.ableDay = ableDay;
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
	public double getPlanTime() {
		return planTime;
	}
	public void setPlanTime(double planTime) {
		this.planTime = planTime;
	}
	public double getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ProjTask [taskId=" + taskId + ", projId=" + projId
				+ ", taskName=" + taskName + ", taskDesc=" + taskDesc
				+ ", excutor=" + excutor + ", taskState=" + taskState
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", classId=" + classId + ", ableDay=" + ableDay
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", planTime=" + planTime + ", finishTime=" + finishTime
				+ ", process=" + process + ", isDelete=" + isDelete
				+ ", remark=" + remark + "]";
	}
	
}
