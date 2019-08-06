package com.lovo.csc.vo.clientvo;

//用户注册审核信息vo
public class RegisterAuditPageVo {

    //用户名
    private String userName;
    //用户账户状态
    private String userState;
    //审核意见
    private String auditOpinion ;
    //审核批复时间
    private String auditReplyTime;

    //审核人
    private String auditPerson;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
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

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }
}
