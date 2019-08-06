package com.lovo.sscbfore.user.service;

import com.lovo.sscbfore.user.entity2.UserEntity;

public interface IUserService {
    /**
     * 根据用户名查询用户对象
     * @param userName 用户名
     * @return 用户对象
     */
    public UserEntity findUserByName(String userName);

    /**
     * 根据用户名和密码查询用户对象
     * @param userName 用户名
     * @param password 密码
     * @return 用户对象
     */
    public UserEntity userLogin(String userName,String password);

    /**
     * 根据用户名修改用户状态
     * @param userName 用户名
     * @param userState 用户状态
     */
    public void updateUserState(String userName,String userState);

    /**
     * 根据用户名修改用户电话或密码
     * @param userName 用户名
     * @param telphone 新电话
     * @param password 新密码
     */
    public void updateUser(String userName,String telphone,String password);

    /**
     * 用户快速注册
     * @param user 用户对象
     */
    public void rapidEnrollment(UserEntity user);
}
