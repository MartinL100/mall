package com.lovo.sscafter.customerRetention.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class MQController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    // @Autowired
//    private  ActiveMQQueue frozenOrUnfrozenAccountsMessageMQ;
    Log log= LogFactory.getLog(this.getClass());
    //发送保审核请求到MQ

    @RequestMapping(value = "sendMessageToMQ.lovo")
    public String sendMessageToMQ(){
//    jmsMessagingTemplate.convertAndSend(frozenOrUnfrozenAccountsMessageMQ,"我送的信息");
//        log.info("+++++++++++++++++++++++++++++++++++++++++");
        return "提交成功";

    }

    @JmsListener(destination = "frozenOrUnfrozenAccountsMessageMQ")
    public void l(String message){
        log.info(message);
    }



}
