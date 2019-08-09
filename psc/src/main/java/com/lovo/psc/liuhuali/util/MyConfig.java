package com.lovo.psc.liuhuali.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public ActiveMQQueue ZCMQ(){
         return  new ActiveMQQueue("ZCMQ");
    }
}
