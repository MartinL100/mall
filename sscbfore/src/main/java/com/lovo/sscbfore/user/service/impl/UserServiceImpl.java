package com.lovo.sscbfore.user.service.impl;

import com.lovo.sscbfore.user.dao.IUserDao;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public UserEntity findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public UserEntity userLogin(String userName, String password) {
        return userDao.userLogin(userName,password);
    }

    @Override
    public void updateUserState(String userName, String userState) {
        userDao.updateUserState(userName,userState);
    }

    @Override
    public void updateUser(String userName, String telphone, String password) {
       UserEntity user= userDao.findUserByName(userName);
       user.setTelphone(telphone);
       user.setPassword(password);
       userDao.save(user);
    }

    @Override
    public void rapidEnrollment(UserEntity user) {
        userDao.save(user);
    }

    @Override
    public List<UserEntity> userList(String userName) {
       List<UserEntity> list= userDao.userList(userName);
        return list;
    }
}
