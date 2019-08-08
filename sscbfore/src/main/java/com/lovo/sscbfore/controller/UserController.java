package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.service.IUserMessageService;
import com.lovo.sscbfore.user.entity2.*;
import com.lovo.sscbfore.user.service.IUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService serService;
    @Autowired
    private IUserMessageService service;
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
    public String updateUser(String telphone,String password,HttpServletRequest request) {
        UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
        String userName=user.getUserName();
        serService.updateUser(userName,telphone,password);
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
    @RequestMapping("userList/{userName}/{userState}/{currentPage}/{rows}")
    public List<UserShowDto> userEntityList(@PathVariable("userName")String userName,@PathVariable("userState")String userState,@PathVariable("currentPage")String currentPage,@PathVariable("rows")String rows){
       List<UserEntity> list= serService.userList(userName,userState,Integer.parseInt(currentPage),Integer.parseInt(rows));
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
    @RequestMapping("userRows/{userName}/{userState}")
    public int userRows(@PathVariable("userName")String userName,@PathVariable("userState")String userState){
        int i=serService.userRows(userName,userState);
        return i;
    }

    @JmsListener(destination = "accountsRegistrationAuditResultMQ")
    public void userInfo(String message) {
        System.out.printf(message);
        System.out.printf(message);
//        if(message!=null || "".equals(message)){
//            ObjectMapper obj=new ObjectMapper();
//            try {
//                RegisterResultVo vo= obj.readValue(message,RegisterResultVo.class);
//              UserEntity user=  serService.findUserByName(vo.getUserName());
//              user.setUserGrade(vo.getUserGrade());
//              user.setUserState(vo.getUserState());
//              serService.rapidEnrollment(user);
//               UserInfoEntity info=new UserInfoEntity();
//               info.setUserInfo(user);
//               info.setMessageInfo(vo.getAuditOpinion());
//               info.setMessageDate(vo.getAuditReplyTime());
//               service.addUserMessage(info);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }

    @RequestMapping("requestUnfreezeMQ")
    public String requestUnfreezeMQ(String applyReason,HttpServletRequest request){
        UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
        UserUnfreezeDto dto=new UserUnfreezeDto();
        dto.setUserName(user.getUserName());
        dto.setApplyReason(applyReason);
        dto.setAuditTime(new Timestamp(System.currentTimeMillis())+"");
        ActiveMQQueue queue=new ActiveMQQueue("requestUnfreezeMQ");
        jmsMessagingTemplate.convertAndSend(queue,dto);
        return "ok";
    }

    @JmsListener(destination = "frozenOrUnfrozenUpsetMQ")
    public void UnfrozenUpset(UserUnfrozenUpsetDto dto){
        if(null!=dto){
            UserEntity user=serService.findUserByName(dto.getUserName());
            UserInfoEntity info=new UserInfoEntity();
            info.setUserInfo(user);
            info.setMessageInfo(dto.getAuditOpinion());
            info.setMessageDate(dto.getAuditReplyTime());
            service.addUserMessage(info);
        }
    }

}
