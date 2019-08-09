package com.lovo.csc.util;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierMQ {

    @LoadBalanced   //使用负载均衡机制
    @Bean
    public ActiveMQQueue SHMQ(){
        return new ActiveMQQueue("SHHMQ");
    }

    @LoadBalanced   //使用负载均衡机制
    @Bean
    public ActiveMQQueue returnSupplierGoodsAudit(){
        return new ActiveMQQueue("returnSupplierGoodsAudit");
    }
    @LoadBalanced   //使用负载均衡机制
    @Bean
    public ActiveMQQueue FBMQ(){
        return new ActiveMQQueue("FBMQ");
    }

    @LoadBalanced   //使用负载均衡机制
    @Bean
    public ActiveMQQueue priceMQ(){
        return new ActiveMQQueue("priceMQ");
    }
}
