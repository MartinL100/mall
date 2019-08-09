package com.lovo.sscbfore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author che
 * @title: DefaultController
 * @projectName mall
 * @description: 用于重定向到登陆页面
 * @date 2019/8/6  11:10
 */
@Controller
public class DefaultController {

    @RequestMapping("/")
    String home() {
        return "redirect:/pageTwo/user/userLogin.html";
    }
}
