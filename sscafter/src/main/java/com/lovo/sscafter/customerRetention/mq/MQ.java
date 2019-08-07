package com.lovo.sscafter.customerRetention.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQ {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    Log log = LogFactory.getLog(this.getClass());

//    @JmsListener(destination = "j168Point")
//    public void getPointMessage(String message) {
//        log.info("发送来的信息" + message);
//        //回执消息
//        ActiveMQQueue queue = new ActiveMQQueue("frozenOrUnfrozenAccountsMessageMQ");
//        jmsMessagingTemplate.convertAndSend(queue, "已经收到");
//    }
//
//    @JmsListener(destination = "ResultQueue")
//    public void ResultQueue(String message) {
//
//        log.info("返回的信息" + message);
//    }


}
