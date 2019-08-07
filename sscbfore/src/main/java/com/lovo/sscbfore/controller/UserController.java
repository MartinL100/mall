package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.user.entity2.ResgisterMessageVo;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.entity2.UserShowDto;
import com.lovo.sscbfore.user.service.IUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService serService;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

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
                request.getSession().setAttribute("userEntity",user2);
                return "adm";
            }

            request.getSession().setAttribute("userEntity",user2);
            return "ok";
       }
        else {
            return "yes";
        }

    }

    @RequestMapping("update2/{userName}/{userState}")
    public String updateUserState(@PathVariable("userName")String userName,@PathVariable("userState")String userState) {
        serService.updateUserState(userName,userState);
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
    public String uploadPage(String aptitudeImg, String identityImg, String companyName,HttpServletRequest request) throws JsonProcessingException {
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
        vo.setAuditTime(new Timestamp(System.currentTimeMillis())+"");
        ObjectMapper obj=new ObjectMapper();
        String json=obj.writeValueAsString(vo);
        ActiveMQQueue queue=new ActiveMQQueue("accountsRegistrationAuditMessageMQ");
        jmsMessagingTemplate.convertAndSend(queue,json);
        return "ok";
    }

    @RequestMapping("showUser")
    public UserEntity showUser(HttpServletRequest request){
      UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
      return user;
    }
    @RequestMapping("userList/{userName}")
    public List<UserShowDto> userEntityList(@PathVariable("userName")String userName){
       List<UserEntity> list= serService.userList(userName);
       List<UserShowDto> list1=new ArrayList<>();
       UserShowDto user=new UserShowDto();
        for (UserEntity u:list) {
            user.setUserId(u.getUserId());
            user.setUserName(u.getUserName());
            user.setPassword(u.getPassword());
            user.setSex(u.getSex());
            user.setTelphone(u.getTelphone());
            user.setTrueName(u.getTrueName());
            user.setUserState(u.getUserState());
            list1.add(user);
        }
       return list1;
    }


}
