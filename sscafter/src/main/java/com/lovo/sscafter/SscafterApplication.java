package com.lovo.sscafter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SscafterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SscafterApplication.class, args);
    }

}
