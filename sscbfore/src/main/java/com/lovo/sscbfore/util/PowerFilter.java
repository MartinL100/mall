package com.lovo.sscbfore.util;//随便起咯


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class PowerFilter {



    //配置切点
    @Pointcut("execution(* com.lovo.sscbfore.controller.MakeDelController..*(..))")
    public void log() {
    }

    @Before("log()")  //前置通知
    public void beforeExcution(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

    }

    @AfterReturning(returning = "ret", pointcut = "log()")  //后置返回
    public void doAfterReturning(Object ret) throws Throwable {

    }
}