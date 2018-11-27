package com.entity;

public class SysClass {
	// 分类id
	private Integer classId;
	// 分类名称
	private String className;
	// 分类父id
	private Integer parentId;
	// 分类描述
	private String classDesc;
	// 系统类型
	private String sysType;
	// 级别
	private Integer grade;
	// 分类编码
	private String classCode;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@Override
	public String toString() {
		return "SysClass [classId=" + classId + ", className=" + className + ", parentId=" + parentId + ", classDesc="
				+ classDesc + ", sysType=" + sysType + ", grade=" + grade + ", classCode=" + classCode + "]";
	}

}
