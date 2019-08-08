package com.lovo.csc.util;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.jms.ConnectionFactory;

@Configuration
public class MyMQ {
    //创建一个发送给审核管理信息队列
    @Bean
    public ActiveMQQueue accountsRegistrationAuditResultMQ(){
        return new ActiveMQQueue("accountsRegistrationAuditResultMQ");
    }

    //创建一个给用户注册管理员队列
    @Bean
    public ActiveMQQueue frozenOrUnfrozenAccountsResultMQ(){
        return new ActiveMQQueue("frozenOrUnfrozenAccountsResultMQ");
    }
    //websocket 放入到spring容器
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }

//    @Bean
//    public ConnectionFactory connectionFactory(){
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        connectionFactory.setBrokerURL("tcp://localhost:61616");
//        connectionFactory.setUserName("admin");
//        connectionFactory.setPassword("admin");
//        return connectionFactory;
//    }

//
//    @Bean
//    public JmsTemplate JmsTemplate(){
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaabbbbbbbbb");
//        return new JmsTemplate(connectionFactory());
//
//    }
//    @Bean
//    public JmsMessagingTemplate jmsMessagingTemplate(){
//        System.out.println("ccccccccccccc");
//        return new JmsMessagingTemplate(connectionFactory());
//
//    }
}
