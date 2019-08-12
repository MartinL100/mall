package com.lovo.csc.util;

import com.lovo.csc.entity.supplierEntity.AuditEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StaticInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURI();
        if(url.indexOf("index")!=-1){
            return true;
        }
        if(url.indexOf("regist")!=-1){
            return  true;
        }
        AuditEntity audit=(AuditEntity) request.getSession().getAttribute("auditObj");
        if(null!=audit){
            return true;
        }
        response.sendRedirect("/page/index.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
