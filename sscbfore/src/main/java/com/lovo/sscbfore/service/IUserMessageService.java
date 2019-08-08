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
     * @param page     当前页
     * @param limit    每页显示行数
     * @return 用户消息集合
     */
    List<UserInfoEntity> findAllUserMessage(String userName, String page, String limit);

    /**
     * 修改用户所有消息状态，将未读（0）改为已读（1）
     *
     * @param userName 用户名
     */
    void updateMessageStatus(String userName);

    /**
     * 添加用户消息
     *
     * @param userMessage 消息实体类
     */
    void addUserMessage(UserInfoEntity userMessage);
}
