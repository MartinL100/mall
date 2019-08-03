package com.lovo.sscbfore.configer;

import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author che
 * @title: BeanConfig
 * @projectName mall
 * @description: 实体类配置器，用于配置各种实体类
 * @date 2019/8/3  15:08
 */
@Configuration
public class BeanConfig {

    /**
     * 配置hutoolJason工具
     *
     * @return hutoolJason工具
     */
    @Bean
    public JsonUtils jsonObject() {
        return new JsonUtils();
    }
}