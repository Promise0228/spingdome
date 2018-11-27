package com.entity;

public class ProjTeam {
    private Integer teamId;

    private Integer projId;

    private String teamCode;

    private String teamName;

    private String teamDesc;

    private Integer projChief;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc == null ? null : teamDesc.trim();
    }

    public Integer getProjChief() {
        return projChief;
    }

    public void setProjChief(Integer projChief) {
        this.projChief = projChief;
    }

	@Override
	public String toString() {
		return "ProjTeam [teamId=" + teamId + ", projId=" + projId
				+ ", teamCode=" + teamCode + ", teamName=" + teamName
				+ ", teamDesc=" + teamDesc + ", projChief=" + projChief + "]";
	}
    
}