package com.lovo.sscafter.customerRetention.controller;

import com.lovo.sscafter.customerRetention.Entity.UserEntity;
import com.lovo.sscafter.customerRetention.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/*")
public class loginController {

    @Autowired
    private IUserDao userDao;

    //index页面
    @RequestMapping("/index")
    public String index() {
        return "../static/page/loginAndRegister/index.html";
        //return "redirect:/index";
    }

    //注册页面
    @RequestMapping("/register")
    public String register(){
        return "../static/page/loginAndRegister/register.html";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(){
        return "../static/page/loginAndRegister/login.html";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String realname=request.getParameter("realname");
        if (password.equals(password2)){
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName1(username);
            userEntity.setPassword1(password);
            userEntity.setTrueName1(realname);
            userDao.save(userEntity);
            return "../static/page/loginAndRegister/login.html";
        }else {
            return "../static/page/loginAndRegister/register.html";
        }
    }

    //登录方法
    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserEntity userEntity = userDao.findByUserName1AAndPassword1(username,password);
        String str = "";
        if (userEntity !=null){
            return  "../static/page/loginAndRegister/index.html";
        }else {
            return "../static/page/loginAndRegister/login.html";
        }

    }

}
