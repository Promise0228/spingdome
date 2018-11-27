package com.entity;

/**
 * 用户组角色关系表
 * @filename GroupRole.java
 * @author hm
 * @date 2018年6月26日下午3:06:58
 */
public class GroupRole {
    private Integer goupRoleId;//用户组角色id

    private Integer groupId;//用户组id

    private Integer roleId;//角色id

    public Integer getGoupRoleId() {
        return goupRoleId;
    }

    public void setGoupRoleId(Integer goupRoleId) {
        this.goupRoleId = goupRoleId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	@Override
	public String toString() {
		return "GroupRole [goupRoleId=" + goupRoleId + ", groupId=" + groupId
				+ ", roleId=" + roleId + "]";
	}
    
    
    
}