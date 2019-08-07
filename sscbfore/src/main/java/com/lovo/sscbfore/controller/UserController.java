package com.lovo.sscbfore.controller;

import com.lovo.sscbfore.user.entity2.ResgisterMessageVo;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;


import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RestController
public class UserController {
    @Autowired
    private IUserService serService;

    @RequestMapping("find")
    public String find(String userName) {
        UserEntity user = serService.findUserByName(userName);
        if (null == user) {
            return "ok";
        } else {
            return "no";
        }
    }

    @RequestMapping("find2")
    public String find2(String userName, String password,HttpServletRequest request) {
        UserEntity user2 = serService.findUserByName(userName);
        UserEntity user = serService.userLogin(userName, password);
        String info="";
        if (null != user2) {
            if (null == user) {
                info="no";
                return info;
            } else if ("1".equals(user.getAdministrator())) {
                return "adm";
            }

            request.getSession().setAttribute("userEntity",user2);
            return "ok";
       }
        else {
            return "yes";
        }

    }

    @RequestMapping("update2")
    public String updateUserState() {
        serService.updateUserState("zy", "1");
        return "ok";
    }

    @RequestMapping("update3")
    public String updateUser() {
        serService.updateUser("zy", "13422222222", "123");
        return "ok";
    }

    @RequestMapping("rapidEnrollment")
    public String rapidEnrollment(UserEntity user) {
        serService.rapidEnrollment(user);
        return "ok";
    }

    @RequestMapping("pageUpload")
    public String uploadPage(String aptitudeImg, String identityImg, String companyName,HttpServletRequest request) {
        ResgisterMessageVo vo=new ResgisterMessageVo();
        UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
        vo.setUserName(user.getUserName());
        vo.setTrueName(user.getTrueName());
        vo.setTelphone(user.getTelphone());
        vo.setSex(user.getSex());
        vo.setPassword(user.getPassword());
        vo.setAptitudeImg(aptitudeImg);
        vo.setIdentityImg(identityImg);
        vo.setCompanyName(companyName);
        vo.setAuditType(user.getAuditType());
        System.out.printf(""+new Timestamp(System.currentTimeMillis()));
        vo.setAuditTime(new Timestamp(System.currentTimeMillis())+"");
        return "ok";
    }

    @RequestMapping("showUser")
    public UserEntity showUser(HttpServletRequest request){
      UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
      return user;
    }
}
