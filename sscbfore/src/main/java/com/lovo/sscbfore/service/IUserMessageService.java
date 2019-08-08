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
     * 查询用户所有消息记录，按时间倒序，分页显示
     *
     * @param userName 用户名
     * @return 用户消息集合
     */
    List<UserInfoEntity> findAllUserMessage(String userName);

    /**
     * 分页查询用户消息
     *
     * @param userName 用户名
     * @param page     当前页
     * @param limit    每页行数
     * @return 用户消息集合
     */
    List<UserInfoEntity> findAllUserMessagePageAble(String userName, int page, int limit);

    /**
     * 查询用户消息总数
     *
     * @param userName 用户名称
     * @return 用户消息总数
     */
    int countUserMessages(String userName);

    /**
     * 查询用户未读消息总数
     *
     * @param userName 用户名
     * @return 用户未读消息总数
     */
    int countUserNewMessage(String userName);

    /**
     * 将用户所有消息更改为已读 （messageStatus = 1）
     *
     * @param userName 用户名称
     */
    void updateUserMessage(String userName);



    /**
     * 添加用户消息
     *
     * @param userMessage 消息实体类
     */
    void addUserMessage(UserInfoEntity userMessage);

}
