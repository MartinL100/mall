package com.lovo.sscafter.customerRetention.Entity.DTO;

public class UserUnfreezeDto {

    /**用户名*/
    private String userName;
    /**解冻理由*/
    private String applyReason;
    /**申请时间*/
    private String auditTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}
