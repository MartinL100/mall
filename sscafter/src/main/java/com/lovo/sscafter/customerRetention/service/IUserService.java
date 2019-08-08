package com.lovo.sscafter.customerRetention.service;


import com.lovo.sscafter.customerRetention.Entity.UserEntity;

public interface IUserService {

    public void savaUser(UserEntity user);
    public UserEntity findByUserName1AndPassword1(String userName1,String password1);


}
