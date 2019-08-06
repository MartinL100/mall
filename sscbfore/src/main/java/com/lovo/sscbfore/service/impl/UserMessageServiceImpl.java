package com.lovo.sscbfore.service.impl;

import com.lovo.sscbfore.dao.IUserMessageDao;
import com.lovo.sscbfore.service.IUserMessageService;
import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author che
 * @title: UserMessageServiceImpl
 * @projectName mall
 * @description: 用户消息接口实现类
 * @date 2019/8/5  14:17
 */
@Service
public class UserMessageServiceImpl implements IUserMessageService {

    /**
     * 用户持久接口
     */
    @Autowired
    IUserMessageDao userMessageDao;

    @Override
    public List<UserInfoEntity> findAllUserMessage(String userName, String page, String limit) {

        return null;
    }

    @Override
    public void updateMessageStatus(String userName) {

    }

    @Override
    public void addUserMessage(UserInfoEntity userMessage) {
        userMessageDao.save(userMessage);
    }
}
