package com.entity;

import java.util.Date;
/**
 * 角色表
 * @filename Role.java
 * @author hm
 * @date 2018年6月26日下午3:04:11
 */
public class Role {
    private Integer roleId;//角色id

    private String roleName;//角色名称

    private String roleDesc;//角色描述

    private String roleCode;//角色代码

    private String roleState;//角色状态

    private Integer createBy;//创建人

    private Date createTime;//创建时间

    private Integer udateBy;//修改人

    private Date updateTime;//修改时间

    private Date createTime2;//创建时间
    
    
    
    
    public Date getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState == null ? null : roleState.trim();
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
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + ", roleCode=" + roleCode
				+ ", roleState=" + roleState + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", udateBy=" + udateBy
				+ ", updateTime=" + updateTime + ", createTime2=" + createTime2
				+ "]";
	}

    
}