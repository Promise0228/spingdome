package com.entity;

/**
 * 角色权限表
 * @filename RoleAuth.java
 * @author hm
 * @date 2018年6月26日下午3:04:39
 */
public class RoleAuth {
    private Integer roleAuthId;//角色权限id

    private Integer roleId;//角色id

    private Integer authId;//权限id

    public Integer getRoleAuthId() {
        return roleAuthId;
    }

    public void setRoleAuthId(Integer roleAuthId) {
        this.roleAuthId = roleAuthId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}