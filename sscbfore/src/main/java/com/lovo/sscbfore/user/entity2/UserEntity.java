package com.lovo.sscbfore.user.entity2;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_user")
public class UserEntity {
    @Column(name="user_id",length = 32)
    /**用户ID*/
    private String userId = IdUtil.simpleUUID();
    /**用户名*/
    @Column(name="user_name",length =48,unique=true)
    @Id
    private String userName;
    @Column(name="user_password",length =48)
    /**用户密码*/
    private String password;
    @Column(name="true_name",length =48)
    /**真实姓名*/
    private String trueName;
    @Column(name="sex",length =48)
    /**用户性别*/
    private String sex;
    @Column(name="telphone",length =48)
    /**电话号码*/
    private String telphone;
    @Column(name="audit_type",length =48)
    /**审核类型*/
    private String auditType="0";
    @Column(name="user_state",length =48)
    /**用户状态*/
    private String userState="0";
    @Column(name="user_grade",length =48)
    /**用户等级*/
    private String userGrade="";
    @Column(name="administrator",length =48)
    /**管理员标志*/
    private String administrator="0";
    @Column(name="audit_time",length =48)
    /**请求审核时间*/
    private String auditTime="";
    @Column(name="audit_opinion",length =48)
    /**审核意见*/
    private String auditOpinion="";
    @Column(name="apply_reason",length =48)
    /**申请原因*/
    private String applyReason="";

    /**用户地址集合*/
    @OneToMany(mappedBy = "userSite")
    @JsonIgnore
    private List<SiteEntity> userSiteList;

    /**用户消息集合*/
    @JsonIgnore
    @OneToMany(mappedBy ="userInfo")
    private List<UserInfoEntity> userInfoList;

    public UserEntity() {
    }

    public UserEntity(String userName, String password, String trueName, String sex, String telphone, String auditType, String userState, String userGrade, String administrator, String auditTime, String auditOpinion, String applyReason, List<SiteEntity> userSiteList, List<UserInfoEntity> userInfoList) {
        this.userName = userName;
        this.password = password;
        this.trueName = trueName;
        this.sex = sex;
        this.telphone = telphone;
        this.auditType = auditType;
        this.userState = userState;
        this.userGrade = userGrade;
        this.administrator = administrator;
        this.auditTime = auditTime;
        this.auditOpinion = auditOpinion;
        this.applyReason = applyReason;
        this.userSiteList = userSiteList;
        this.userInfoList = userInfoList;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public List<SiteEntity> getUserSiteList() {
        return userSiteList;
    }

    public void setUserSiteList(List<SiteEntity> userSiteList) {
        this.userSiteList = userSiteList;
    }

    public List<UserInfoEntity> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfoEntity> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
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

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}
