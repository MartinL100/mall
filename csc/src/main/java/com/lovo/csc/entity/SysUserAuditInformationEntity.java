package com.lovo.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_auditUserMessage")
public class SysUserAuditInformationEntity {
    //用户审核信息id
    @Id
    @GenericGenerator(name="guid",strategy="uuid")
    @GeneratedValue(generator="guid")
    @Column(name = "audit_Id",length = 48)
    private String userAuditInformationId;
    //用户登录名
    @Column(name = "audit_userName",length = 48,unique = true)
    private String userName;
    //用户真实姓名
    @Column(name = "audit_trueName",length = 48)
    private String trueName;
    //用户密码
    @Column(name = "audit_password",length = 64)
    private String password;
    //用户性别
    @Column(name = "audit_sex",length = 48)
    private String sex;
    //用户电话号
    @Column(name = "audit_telphone",length = 13)
    private String telphone;
    //用户资质图片，这里为了便于传输，采用Base64编码将图片加密成Base64字符串
    @Column(name = "audit_aptitudeImg",length = 200)
    private String aptitudeImg;
    //用户身份证图片，这里为了便于传输，采用Base64编码将图片加密成Base64字符串
    @Column(name = "audit_identityImg",length = 200)
    private String identityImg;
    //用户公司名字
    @Column(name = "audit_companyName",length = 48)
    private String companyName;
    //请求审核的类型，0代表用户注册审核,1请求冻结账户，2，请求解冻
    @Column(name = "audit_auditType",length = 48)
    private String auditType;
    //用户状态
    @Column(name = "audit_userState",length = 48)
    private String userState;
//
//    //用户状态显示中文
//    private String userStateView;
//    //请求审核的类型显示中文
//    private String auditTypeView;

    //用户等级
    @Column(name = "audit_userGrade",length = 48)
    private String userGrade;
    // 审核意见
    @Column(name = "audit_auditOpinion",length = 48)
    private String	auditOpinion;
    //提交冻结或解冻管理员
    @Column(name = "audit_maintenanceManager",length = 48)
    private String maintenanceManager;
    //提交审核时间
    @Column(name = "audit_auditTime",length = 48)
    private String auditTime;
    //审核管理员
    @Column(name = "audit_auditPerson",length = 48)
    private String auditPerson;
    //审核复批时间
    @Column(name = "audit_auditReplyTime",length = 48)
    private String auditReplyTime;


    public SysUserAuditInformationEntity() {
    }

    public String getUserAuditInformationId() {
        return userAuditInformationId;
    }

    public void setUserAuditInformationId(String userAuditInformationId) {
        this.userAuditInformationId = userAuditInformationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAptitudeImg() {
        return aptitudeImg;
    }

    public void setAptitudeImg(String aptitudeImg) {
        this.aptitudeImg = aptitudeImg;
    }

    public String getIdentityImg() {
        return identityImg;
    }

    public void setIdentityImg(String identityImg) {
        this.identityImg = identityImg;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getMaintenanceManager() {
        return maintenanceManager;
    }

    public void setMaintenanceManager(String maintenanceManager) {
        this.maintenanceManager = maintenanceManager;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public String getAuditReplyTime() {
        return auditReplyTime;
    }

    public void setAuditReplyTime(String auditReplyTime) {
        this.auditReplyTime = auditReplyTime;
    }

    @Override
    public String toString() {
        return "SysUserAuditInformationEntity{" +
                "userAuditInformationId=" + userAuditInformationId +
                ", userName='" + userName + '\'' +
                ", trueName='" + trueName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", telphone='" + telphone + '\'' +
                ", aptitudeImg='" + aptitudeImg + '\'' +
                ", identityImg='" + identityImg + '\'' +
                ", companyName='" + companyName + '\'' +
                ", auditType='" + auditType + '\'' +
                ", userState='" + userState + '\'' +
                ", userGrade='" + userGrade + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                ", maintenanceManager='" + maintenanceManager + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", auditPerson='" + auditPerson + '\'' +
                ", auditReplyTime='" + auditReplyTime + '\'' +
                '}';
    }
}
