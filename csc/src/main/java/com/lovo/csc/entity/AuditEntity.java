package com.lovo.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 审核系统账号表
 */
@Entity
@Table(name = "sys_audit")
public class AuditEntity {
    //审核账号ID
    @Id
    @Column(length=48)
    @GenericGenerator(name="myuuid",strategy="uuid")
    @GeneratedValue(generator="myuuid")
    private String auditId;
    //审核账号名
    @Column(length=48)
    private String auditName;
    //审核密码
    @Column(length=48)
    private String auditPwd;
    //审核人姓名
    @Column(length=48)
    private String auditPeople;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getAuditPwd() {
        return auditPwd;
    }

    public void setAuditPwd(String auditPwd) {
        this.auditPwd = auditPwd;
    }

    public String getAuditPeople() {
        return auditPeople;
    }

    public void setAuditPeople(String auditPeople) {
        this.auditPeople = auditPeople;
    }
}
