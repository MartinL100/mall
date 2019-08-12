package com.lovo.sscafter.goodsStock.entity;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

public class RestTemplate {
    @Bean
    @LoadBalanced
        //负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
