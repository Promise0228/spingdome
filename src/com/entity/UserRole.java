package com.entity;
/**
 * 用户角色表
 * @filename UserRole.java
 * @author hm
 * @date 2018年6月26日下午3:03:55
 */
public class UserRole {
    private Integer userRoleId;//用户角色id

    private Integer roleId;//角色id

    private Integer userId;//用户id

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", roleId=" + roleId
				+ ", userId=" + userId + "]";
	}
    
    
}