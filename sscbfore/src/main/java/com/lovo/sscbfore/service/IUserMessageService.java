package com.lovo.sscbfore.service;

import com.lovo.sscbfore.user.entity2.UserInfoEntity;

import java.util.List;

/**
 * @author che
 * @title: IUserMessageService
 * @projectName mall
 * @description: 用户消息业务接口
 * @date 2019/8/5  14:08
 */
public interface IUserMessageService {


    /**
     * 添加用户消息
     *
     * @param userMessage 消息实体类
     */
    void addUserMessage(UserInfoEntity userMessage);

    /**
     * 按用户名查询用户所有消息
     *
     * @param userName 用户名
     * @return 用户消息集合
     */
    List<UserInfoEntity> findAllUserMessage(String userName);

    /**
     * 按用户名查询所有用户消息（翻页）
     *
     * @param userName 用户名称
     * @param page     当前页
     * @param limit    每页显示行数
     * @return 用户消息集合
     */
    List<UserInfoEntity> findAllUserMessagePageAble(String userName, int page, int limit);

    /**
     * 查询用户所有消息总数
     *
     * @param userName 用户名
     * @return 用户所有消息总数
     */
    int countUserMessages(String userName);

    /**
     * 查询用户新消息总数
     *
     * @param userName 用户名称
     * @return 用户新消息总数
     */
    int countUserNewMessage(String userName);

    /**
     * 按用户名称 更新用户消息状态
     *
     * @param userName 用户名称
     */
    void updateUserMessage(String userName);
}
