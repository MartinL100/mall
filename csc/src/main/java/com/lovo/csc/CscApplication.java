package com.lovo.csc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class CscApplication {

    public static void main(String[] args) {
        SpringApplication.run(CscApplication.class, args);
    }

}
