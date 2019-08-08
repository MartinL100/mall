package com.lovo.sscafter.customerRetention.Entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String trueName1;
    private  String userName1;
    private String password1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrueName1() {
        return trueName1;
    }

    public void setTrueName1(String trueName1) {
        this.trueName1 = trueName1;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

}
