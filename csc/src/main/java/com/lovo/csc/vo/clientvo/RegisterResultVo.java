package com.lovo.csc.vo.clientvo;

//用户注册审核完成后返回信息VO
public class RegisterResultVo {
    //用户名:
    private String userName;
    //用户等级
    private String userGrade;
    //用户状态 返回1为审核通过，2为审核未通过
    private String userState;
    //审核意见
    private String auditOpinion ;
    //审核批复时间
    private String auditReplyTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
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

    public RegisterResultVo() {
    }

    @Override
    public String toString() {
        return "RegisterResultVo{" +
                "userName='" + userName + '\'' +
                ", userGrade='" + userGrade + '\'' +
                ", userState='" + userState + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", auditReplyTime='" + auditReplyTime + '\'' +
                '}';
    }

}
