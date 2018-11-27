package com.entity;

/**
 * 用户权限表
 * @filename UserAuth.java
 * @author hm
 * @date 2018年6月26日下午3:06:15
 */
public class UserAuth {
    private Integer userAuthId;//主键id

    private Integer userId;//用户id

    private Integer authId;//权限id

    public Integer getUserAuthId() {
        return userAuthId;
    }

    public void setUserAuthId(Integer userAuthId) {
        this.userAuthId = userAuthId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

	@Override
	public String toString() {
		return "UserAuth [userAuthId=" + userAuthId + ", userId=" + userId
				+ ", authId=" + authId + "]";
	}
    
    
    
}