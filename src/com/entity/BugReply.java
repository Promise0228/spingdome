package com.entity;

/**
 * bug备注表
 * @author 31886
 * 
 */
public class BugReply {
	// 回复id
	private Integer replyId;
	// bugid
	private Integer bugId;
	// 回复内容
	private String replyContent;
	// 回复父id
	private Integer parentId;
	// 创建时间
	private String createTime;
	// 创建人
	private Integer createBy;
	// 删除标记
	private Integer isDelete;
    
	private String createBys;
	
	public String getCreateBys() {
		return createBys;
	}
	public void setCreateBys(String createBys) {
		this.createBys = createBys;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getBugId() {
		return bugId;
	}
	public void setBugId(int bugId) {
		this.bugId = bugId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "bugReply [replyId=" + replyId + ", bugId=" + bugId
				+ ", replyContent=" + replyContent + ", parentId=" + parentId
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", isDelete=" + isDelete + "]";
	}
	
}
