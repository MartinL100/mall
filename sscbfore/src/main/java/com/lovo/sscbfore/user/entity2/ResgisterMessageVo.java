package com.lovo.sscbfore.user.entity2;

//用户账户注册审核 发送信息VO
public class ResgisterMessageVo {
    //用户名唯一标识符
    private String userName;
    //真实姓名
    private String trueName;
    //密码
    private String password;
    //手机号
    private String telphone;
    //性别
    private String sex;
    //审核类型 0代表请求审核， 1：代表请求冻结，2：取消冻结
    private String auditType;
    //资质图片
    private String aptitudeImg;
    //身份证图片
    private String identityImg;
    //公司名
    private String companyName;
    //请求审核审核时间
    private String auditTime;

    public ResgisterMessageVo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
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

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    @Override
    public String toString() {
        return "ResgisterMessageVo{" +
                "userName='" + userName + '\'' +
                ", auditType='" + auditType + '\'' +
                ", aptitudeImg='" + aptitudeImg + '\'' +
                ", identityImg='" + identityImg + '\'' +
                ", companyName='" + companyName + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", sex='" + sex + '\'' +
                ", telphone='" + telphone + '\'' +
                ", auditTime='" + auditTime + '\'' +
                '}';
    }
}
