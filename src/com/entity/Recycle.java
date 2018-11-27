package com.entity;



import java.util.Date;

public class Recycle {
	// 回收站id
	private Integer recyId;
	// 数据库用户
	private String dbUser;
	// 系统分类
	private Integer classId;
	// 描述
	private String intro;
	// 表名称
	private String tableName;
	// 主键名称
	private String keyName;
	// 创建时间
	private Date createTime;
	// 主键值
	private Integer keyValue;
	// 创建人
	private Integer createBy;
	//
	private String className;
	//
	private String projName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Integer getRecyId() {
		return recyId;
	}

	public void setRecyId(Integer recyId) {
		this.recyId = recyId;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Integer keyValue) {
		this.keyValue = keyValue;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	@Override
	public String toString() {
		return "Recycle [recyId=" + recyId + ", dbUser=" + dbUser + ", classId=" + classId + ", intro=" + intro
				+ ", tableName=" + tableName + ", keyName=" + keyName + ", createTime=" + createTime + ", keyValue="
				+ keyValue + ", createBy=" + createBy + "]";
	}

}
