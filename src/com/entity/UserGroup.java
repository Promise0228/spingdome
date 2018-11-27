package com.entity;

/**
 * 用户组
 * @filename UserGroup.java
 * @author hm
 * @date 2018年6月26日下午3:06:38
 */
public class UserGroup {
    private Integer groupId;//用户组id

    private String groupName;//用户组名称

    private String groupCode;//用户组code

    private String groupDesc;//用户组描述

    private String groupState;//用户组状态

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState == null ? null : groupState.trim();
    }

	@Override
	public String toString() {
		return "UserGroup [groupId=" + groupId + ", groupName=" + groupName
				+ ", groupCode=" + groupCode + ", groupDesc=" + groupDesc
				+ ", groupState=" + groupState + "]";
	}
    
    
}