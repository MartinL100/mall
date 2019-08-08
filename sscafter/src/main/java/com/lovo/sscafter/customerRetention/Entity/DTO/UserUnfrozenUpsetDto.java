package com.lovo.sscafter.customerRetention.Entity.DTO;

public class UserUnfrozenUpsetDto {
    /**用户名*/
    private String userName;
    /**审核结果*/
    private String auditOpinion;
    /**审核时间*/
    private String auditReplyTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditReplyTime() {
        return auditReplyTime;
    }

    public void setAuditReplyTime(String auditReplyTime) {
        this.auditReplyTime = auditReplyTime;
    }
}
