package com.lovo.sscafter.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class MyConfig {



    // 开启websocket
//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
