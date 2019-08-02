package com.lovo.sscafter.customerRetention.service.impl;


import com.lovo.sscafter.customerRetention.Entity.UserEntity;
import com.lovo.sscafter.customerRetention.dao.IUserDao;
import com.lovo.sscafter.customerRetention.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public UserEntity findByUserName1AndPassword1(String userName1, String password1) {
        return userDao.findByUserName1AAndPassword1( userName1, password1);
    }



    @Override
    public void savaUser(UserEntity user) {
        userDao.save(user);

    }


}
