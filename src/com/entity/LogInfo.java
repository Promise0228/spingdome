package com.entity;

import java.util.Date;


/**
 *@comment 日志表实体类
 *@author changjiaqi
 *@date 2018年7月16日 下午6:27:08
 *@version TODO
 */
public class LogInfo {
	//日志主键
	private Integer logId;
	//IP地址
	private String ipAddress;
	//请求url
	private String url;
	//系统分类
	private Integer classId;
	//项目主键id
	private Integer projId;
	//类型
	private Integer logType;
	//内容
	private String logInfo;
	//异常信息
	private String exception;
	//创建时间
	private Date createTime;
	//创建人
	private Integer createBy;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getProjId() {
		return projId;
	}
	public void setProjId(Integer projId) {
		this.projId = projId;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
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
	@Override
	public String toString() {
		return "LogInfo [logId=" + logId + ", ipAddress=" + ipAddress
				+ ", url=" + url + ", classId=" + classId + ", projId="
				+ projId + ", logType=" + logType + ", logInfo=" + logInfo
				+ ", exception=" + exception + ", createTime=" + createTime
				+ ", createBy=" + createBy + "]";
	}
	
	
}
