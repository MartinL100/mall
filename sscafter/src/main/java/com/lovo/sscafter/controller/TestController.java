package com.lovo.sscafter.controller;

import com.lovo.common.entity.EntityTest;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @RequestMapping(value = "MqTest/{userName}",method = RequestMethod.GET)
    public String mqTest(@PathVariable("userName") String userName){
        EntityTest entityTest = new EntityTest();
        entityTest.setUserName(userName);
        ActiveMQQueue queue = new ActiveMQQueue("MqTest");
        jmsMessagingTemplate.convertAndSend(queue,"Hello Mq");
        System.out.println(entityTest.getUserName());
        return "hello page";
    }
}
