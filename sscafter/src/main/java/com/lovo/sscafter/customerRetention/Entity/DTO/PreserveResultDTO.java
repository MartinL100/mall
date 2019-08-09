package com.lovo.sscafter.customerRetention.Entity.DTO;


//用户状态维护 审核完成后返回结果VO
public class PreserveResultDTO {
    //维护用户的姓名集合
    private String[] userNameArray;
    //用户账号状态 1、审核通过，3：已冻结
    private String userState;
    //审核意见
    private String  auditOpinion;
    //批复时间
    private String auditReplyTime;


    public String[]  getUserNameArray() {
        return userNameArray;
    }

    public void setUserNameArray(String[] userNameArray) {
        this.userNameArray = userNameArray;
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



    @Override
    public String toString() {
        return "PreserveResultVo{" +
                "userNameArray=" + userNameArray +
                ", userState='" + userState + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", auditReplyTime='" + auditReplyTime + '\'' +
                '}';
    }
}
