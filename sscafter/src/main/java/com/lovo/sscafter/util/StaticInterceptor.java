package com.lovo.sscafter.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StaticInterceptor   implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if(url.indexOf("login") != -1){
            return true;
        }
        if(url.indexOf("easy") != -1){
            return true;
        }
        if(url.indexOf("register") != -1){
            return true;
        }
        if(request.getSession().getAttribute("userName") == null){
            response.sendRedirect("/page/loginAndRegister/login.html");
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
