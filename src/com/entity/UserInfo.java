package com.entity;

import java.util.Date;
/**
 * 用户表
 * @filename UserInfo.java
 * @author hm
 * @date 2018年6月26日下午3:03:14
 */
public class UserInfo {
    private Integer userId;//用户id

    private Integer groupId;//用户组id

    private String userName;//用户名

    private String nickName;//昵称

    private String userCode;//用户登录code

    private String userPwd;//用户密码

    private String userType;//用户类型

    private String userState;//用户状态

    private String isDelete;//删除状态

    private Integer createBy;//创建人

    private Date createTime;//创建时间

    private Integer udateBy;//修改人

    private Date updateTime;//修改时间
    
    private Date  createTime1;
   

	public Date getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(Date createTime1) {
		this.createTime1 = createTime1;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUdateBy() {
        return udateBy;
    }

    public void setUdateBy(Integer udateBy) {
        this.udateBy = udateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", groupId=" + groupId
				+ ", userName=" + userName + ", nickName=" + nickName
				+ ", userCode=" + userCode + ", userPwd=" + userPwd
				+ ", userType=" + userType + ", userState=" + userState
				+ ", isDelete=" + isDelete + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", udateBy=" + udateBy
				+ ", updateTime=" + updateTime + ", createTime1=" + createTime1
				+ "]";
	}


	
    
}