package com.lovo.sscafter.customerRetention.controller;

import com.lovo.sscafter.customerRetention.Entity.UserEntity;
import com.lovo.sscafter.customerRetention.dao.IUserDao;
import com.lovo.sscafter.customerRetention.service.IUserService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class loginController {

    @Autowired
    private IUserService userService;

    public static String userName="";
    //注册方法
    @RequestMapping("addregister/{username}/{password}/{realUsername}")
    public void register(@PathVariable("username")String username,
                         @PathVariable("password")String password,@PathVariable("realUsername")String realUsername){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName1(username);
        userEntity.setPassword1(password);
        userEntity.setTrueName1(realUsername);
        userService.savaUser(userEntity);
    }

    //登录方法

    @RequestMapping("addlogin/{username}/{password}")
    @ResponseBody
    public String login(@PathVariable("username")String username,
                        @PathVariable("password")String password, HttpServletRequest request){

        UserEntity userEntity = userService.findByUserName1AndPassword1(username,password);
        if(null != userEntity){
            request.getSession().setAttribute("userName",userEntity);
            userName=username;
            return "{'info':'true'}";
        }
        return "{'info':'false'}";
    }
    @RequestMapping("registerFindUser")
    @ResponseBody
    public String findUser(String userName){

        UserEntity userEntity = userService.findByUser(userName);
        if(null != userEntity){
            return "false";
        }
        return "true";
    }
}
