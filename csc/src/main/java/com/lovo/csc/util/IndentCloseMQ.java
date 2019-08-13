package com.lovo.csc.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class IndentCloseMQ {
    @Bean
    public ActiveMQQueue scopeMQ2(){
        return new ActiveMQQueue("scopeMQ2");
    }
}
