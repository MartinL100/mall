package com.lovo.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//用户账户维护审核信息实体
@Entity
@Table(name="sys_PreserveAccountsMessage")
public class SysFrozenOrUnfrozenAccountsEntity {

    //冻结信息id
    @Id
    @GenericGenerator(name="guid",strategy="uuid")
    @GeneratedValue(generator="guid")
    @Column(name = "audit_PreserveAccountsMessageId",length = 48)
    private String frozenOrUnfrozenAccountsMessageId;
    //维护管理员
    @Column(name="frozen_maintenanceManager",length = 48)
    private String maintenanceManager;
    //请求类型
    @Column(name = "frozen_auditType",length = 48)
    private String auditType;
    //请求理由
    @Column(name = "frozen_auditOpinion",length = 48)
    private String auditOpinion;
    //审核结果
    @Column(name = "frozen_auditResult",length = 48)
    private String auditResult;
    //申请内容-用户名字符串
    @Column(name="frozen_userNameStr",length =200)
    private String userNameStr;
    //审核状态-正在审核或审核完毕
    @Column(name = "frozen_auditState",length = 48)
    private String auditState;
    //请求审核时间
    @Column(name = "frozen_auditTime",length = 48)
    private String auditTime;

    public SysFrozenOrUnfrozenAccountsEntity() {
    }

    public String getFrozenOrUnfrozenAccountsMessageId() {
        return frozenOrUnfrozenAccountsMessageId;
    }

    public void setFrozenOrUnfrozenAccountsMessageId(String frozenOrUnfrozenAccountsMessageId) {
        this.frozenOrUnfrozenAccountsMessageId = frozenOrUnfrozenAccountsMessageId;
    }

    public String getMaintenanceManager() {
        return maintenanceManager;
    }

    public void setMaintenanceManager(String maintenanceManager) {
        this.maintenanceManager = maintenanceManager;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAuditOpinion() {
        return auditOpinion;
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

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }


    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    @Override
    public String toString() {
        return "SysFrozenOrUnfrozenAccountsEntity{" +
                "frozenOrUnfrozenAccountsMessageId='" + frozenOrUnfrozenAccountsMessageId + '\'' +
                ", maintenanceManager='" + maintenanceManager + '\'' +
                ", auditType='" + auditType + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", auditResult='" + auditResult + '\'' +
                ", userNameStr='" + userNameStr + '\'' +
                ", auditState='" + auditState + '\'' +
                ", auditTime='" + auditTime + '\'' +
                '}';
    }
}
