package com.entity;
/**
 * 资源文件表
 * @author Administrator
 *
 */
public class Files {

	/**
	 * 文件id
	 */
	private Integer fileId;
	/**
	 * 资源类型id
	 */
	private Integer fileTypeId;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件描述
	 */
	private String fileDesc;
	/**
	 * 文件URL
	 */
	private String fileUrl;
	/**
	 * 文件密钥
	 */
	private String filePswd;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建人
	 */
	private Integer createBy;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 修改人
	 */
	private Integer updateBy;
	/**
	 * 删除标记
	 */
	private Integer isDelete;
	
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public Integer getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(Integer fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFilePswd() {
		return filePswd;
	}
	public void setFilePswd(String filePswd) {
		this.filePswd = filePswd;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	@Override
	public String toString() {
		return "Files [fileId=" + fileId + ", fileTypeId=" + fileTypeId
				+ ", fileName=" + fileName + ", fileDesc=" + fileDesc
				+ ", fileUrl=" + fileUrl + ", filePswd=" + filePswd
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", updateTime=" + updateTime + ", updateBy=" + updateBy
				+ ", isDelete=" + isDelete + "]";
	}
	
}
