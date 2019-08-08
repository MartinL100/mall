package com.lovo.sscbfore.user.dao;

import com.lovo.sscbfore.user.entity2.UserEntity;

import java.util.List;

public interface IUserDao2 {
    /**
     * 模糊查询用户集合
     * @param userName 用户名
     * @param userState 用户状态
     * @param currentPage 当前页
     * @param rows 每页显示行数
     * @return 用户集合
     */
    public List<UserEntity> userList(String userName,String userState,int currentPage,int rows);

    /**
     * 查询条件用户的总行数
     * @param userName 用户名
     * @param userState 用户状态
     * @return 总行数
     */
    public int userRows(String userName,String userState);
}
