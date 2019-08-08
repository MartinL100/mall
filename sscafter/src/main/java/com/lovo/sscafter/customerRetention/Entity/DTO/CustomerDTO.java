package com.lovo.sscafter.customerRetention.Entity.DTO;
//客户
public class CustomerDTO {
    //用户ID
    private String userId;
    //用户名
    private String userName;
    //用户密码
    private String password;
    //真实姓名
    private String trueName;
    //性别
    private String sex;
    //电话
    private String telphone;
    //用户状态
    private String userState;

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

    public String getTelephone() {
        return telphone;
    }

    public void setTelephone(String telphone) {
        this.telphone = telphone;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }
}
