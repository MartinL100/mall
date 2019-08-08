package com.lovo.sscafter.customerRetention.Entity.DTO;

//用户账户维护审核 发送信息Vo
public class PreserveMessageDTO {
    //请求类型 冻结 、解冻
    private String auditType;
    //请求时间
    private  String auditTime;
    //请求理由
    private String auditOpinion;
    //申请内容-用户名字符串
    private String userNameStr;
    //维护管理员
    private String maintenanceManager;

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditOpinion() {
        return  auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getUserNameStr() {
        return userNameStr;
    }

    public void setUserNameStr(String userNameStr) {
        this.userNameStr = userNameStr;
    }

    public String getMaintenanceManager() {
        return maintenanceManager;
    }

    public void setMaintenanceManager(String maintenanceManager) {
        this.maintenanceManager = maintenanceManager;
    }


    @Override
    public String toString() {
        return "PreserveMessageVo{" +
                "auditType='" + auditType + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", userNameStr='" + userNameStr + '\'' +
                ", maintenanceManager='" + maintenanceManager + '\'' +
                '}';
    }
}
