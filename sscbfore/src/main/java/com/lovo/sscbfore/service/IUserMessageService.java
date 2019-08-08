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

    List<UserInfoEntity> findAllUserMessage(String userName);

    List<UserInfoEntity> findAllUserMessagePageAble(String userName, int page, int limit);

    int countUserMessages(String userName);

    int countUserNewMessage(String userName);

    void updateUserMessage(String userName);
}
