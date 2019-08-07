package com.lovo.sscbfore.service.impl;

import com.lovo.sscbfore.dao.IUserMessageDao;
import com.lovo.sscbfore.service.IUserMessageService;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author che
 * @title: UserMessageServiceImpl
 * @projectName mall
 * @description: 用户消息接口实现类
 * @date 2019/8/5  14:17
 */
@Service(value = "userMessageService")
public class UserMessageServiceImpl implements IUserMessageService {

    /**
     * 用户持久接口
     */
    @Autowired
    IUserMessageDao userMessageDao;

    @Override
    public List<UserInfoEntity> findAllUserMessage(String userName) {

        UserEntity user = new UserEntity();
        user.setUserName(userName);
        return userMessageDao.findAllByUserInfoIsOrderByMessageDateDesc(user);
    }

    @Override
    public List<UserInfoEntity> findAllUserMessagePageAble(String userName, int page, int limit) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        Pageable pageable = PageRequest.of(page - 1, limit);
        return userMessageDao.findByUserNameAndPage(user, pageable);
    }

    @Override
    public int countUserMessages(String userName) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        return userMessageDao.countByUserInfo(user);
    }

    @Override
    public int countUserNewMessage(String userName) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        return userMessageDao.countByUserInfoAndAndMessageStatus(user, 0);
    }

    @Override
    public void updateUserMessage(String userName) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        userMessageDao.updateMessageStatus(userName);
    }


    @Override
    public void addUserMessage(UserInfoEntity userMessage) {
        userMessageDao.save(userMessage);
    }
}
