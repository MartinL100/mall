package com.lovo.sscbfore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SscbforeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SscbforeApplication.class, args);
    }

}
