package com.lovo.sscbfore.service;

import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author che
 * @title: UserMessageServiceImplTest
 * @projectName mall
 * @description: 用户消息业务测试类
 * @date 2019/8/5  14:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMessageServiceImplTest {

    @Autowired
    IUserMessageService userMessageService;

    @Test
    public void findAllUserMessage() {
    }

    @Test
    public void updateMessageStatus() {
    }

    @Test
    public void addUserMessage() {

        UserEntity user = new UserEntity();
        user.setUserName("che");

        UserInfoEntity message = new UserInfoEntity();
        message.setMessageInfo("23123123123");
        message.setMessageDate(System.currentTimeMillis() + "");
        message.setMessageStatus(0);


        userMessageService.addUserMessage(message);

    }
}