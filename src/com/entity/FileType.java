package com.entity;

public class FileType {

	/**
	 * 资源类型id
	 */
	private Integer fileTypeId;
	/**
	 * 资源类型名称
	 */
	private String typeName;
	/**
	 * 资源类型编码
	 */
	private String typeCode;
	/**
	 * 资源类型父id
	 */
	private Integer parentId;
	/**
	 * 资源类型级别
	 * @return
	 */
	public Integer grade;
	public Integer getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(Integer fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
}
