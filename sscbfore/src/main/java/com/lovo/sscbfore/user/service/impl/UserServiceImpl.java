package com.lovo.sscbfore.user.service.impl;

import com.lovo.sscbfore.user.dao.IUserDao;
import com.lovo.sscbfore.user.dao.IUserDao2;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserDao2 userDao2;
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
    public List<UserEntity> userList(String userName,String userState,int currentPage,int rows) {
       List<UserEntity> list= userDao2.userList(userName,userState,currentPage,rows);
        return list;
    }

    @Override
    public int userRows(String userName, String userState) {
        return userDao2.userRows(userName,userState);
    }
}
