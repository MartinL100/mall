package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.service.IUserMessageService;
import com.lovo.sscbfore.user.entity2.*;
import com.lovo.sscbfore.user.service.IUserService;
import com.lovo.sscbfore.util.verityCode;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public String find2(String userName, String password,String graphicCode,HttpServletRequest request) {
        UserEntity user2 = serService.findUserByName(userName);
        UserEntity user = serService.userLogin(userName, password);
        String str= (String) request.getSession().getAttribute("graphicCode2");
        if (null != user2) {
            if (null == user) {
                return "no";
            } else if ("1".equals(user.getAdministrator())) {
                request.getSession().setAttribute("userEntity",user2);
                return "adm";
            }
            if(str.equals(graphicCode)){
                request.getSession().setAttribute("userEntity",user2);
                return "ok";
            }else {
                return "false";
            }
       }
        else {
            return "yes";
        }
    }
    @RequestMapping("codeimg")
    public void img(HttpServletRequest request, HttpServletResponse response) {
        //接收结果集
        Map<String, Object> map = null;
        //创建输出流
        ServletOutputStream out = null;

        try {
            map = verityCode.GraphicCode(130, 30, 4);
            out = response.getOutputStream();
            //获取图片存储对象
            BufferedImage img = (BufferedImage) map.get("img");
            //获得验证图片生成的验证码
            String graphicCode = (String) map.get("graphicCode");
            //将生成的验证码放入session
            request.getSession().setAttribute("graphicCode2", graphicCode);
            //把图片写入到输出流
            ImageIO.write(img, "jpg", out);
            //关闭流
            out.close();
        } catch (Exception e) {

        }
    }

    @RequestMapping("update2/{userName}/{userState}")
    public String updateUserState(@PathVariable("userName")String userName,@PathVariable("userState")String userState) {
        serService.updateUserState(userName,userState);
        return "true";
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
    public String userEntityList(@PathVariable("userName")String userName,@PathVariable("userState")String userState,@PathVariable("currentPage")String currentPage,@PathVariable("rows")String rows){
       List<UserEntity> list= serService.userList(userName,userState,Integer.parseInt(currentPage),Integer.parseInt(rows));
       List<UserShowDto> list1=new ArrayList<>();
        for (UserEntity u:list) {
            UserShowDto user=new UserShowDto();
            user.setUserId(u.getUserId());
            user.setUserName(u.getUserName());
            user.setPassword(u.getPassword());
            user.setSex(u.getSex());
            user.setTelphone(u.getTelphone());
            user.setTrueName(u.getTrueName());
            user.setUserState(u.getUserState());
            list1.add(user);
        }
        ObjectMapper obj=new ObjectMapper();
        try {
            String json= obj.writeValueAsString(list1);
            try {
                return URLEncoder.encode(json,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
       return "";
    }

    @RequestMapping("userRows/{userName}/{userState}")
    public String userRows(@PathVariable("userName")String userName,@PathVariable("userState")String userState){
        String i= String.valueOf(serService.userRows(userName,userState));
        return i;
    }

    @JmsListener(destination = "accountsRegistrationAuditResultMQ")
    public void userInfo(String message) {
        System.out.printf(message);
        if(message!=null || !"".equals(message)){
            ObjectMapper obj=new ObjectMapper();
            try {
                RegisterResultVo vo= obj.readValue(message,RegisterResultVo.class);
              UserEntity user=  serService.findUserByName(vo.getUserName());
              user.setUserGrade(vo.getUserGrade());
              user.setUserState(vo.getUserState());
              serService.rapidEnrollment(user);
               UserInfoEntity info=new UserInfoEntity();
               info.setUserInfo(user);
               info.setMessageInfo(vo.getAuditOpinion());
               info.setMessageDate(vo.getAuditReplyTime());
               service.addUserMessage(info);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
