package com.lovo.csc.controller.userController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.AuditEntity;
import com.lovo.csc.service.userService.IUserService;
import com.lovo.csc.util.verityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public String userLogin(String userName, String userPwd, HttpServletRequest request) throws JsonProcessingException {
        //得到登陆用户对象
        AuditEntity auditEntity = userService.findAuditEntityByAuditNameAndAuditPwd(userName, userPwd);
        //得到用户输入的验证码
        String verifyCode = request.getParameter("verifyCode");
        //得到session中的验证码
        String graphicCode = (String) request.getSession().getAttribute("graphicCode");
        Map<String, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        //将用户对象和验证码发送到页面
        map.put("auditObj", auditEntity);
        map.put("graphicCode", graphicCode);

        //将用户对象放入session
        request.getSession().setAttribute("auditObj", auditEntity);


        //返回用户Json
        return objectMapper.writeValueAsString(map);
    }


    @RequestMapping("/codeimg")
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
            request.getSession().setAttribute("graphicCode", graphicCode);
            //把图片写入到输出流
            ImageIO.write(img, "jpg", out);
            //关闭流
            out.close();
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "/regits")
    @ResponseBody
    public String regits(HttpServletRequest request) {
        String auditName = request.getParameter("username");
        String auditPwd = request.getParameter("password");
        String auditPeople = request.getParameter("nickname");
        //数据验证

        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setAuditName(auditName);
        auditEntity.setAuditPeople(auditPeople);
        auditEntity.setAuditPwd(auditPwd);
        System.out.println(auditName);
        userService.addAudit(auditEntity);
        return "ok";
    }

    @RequestMapping(value = "getAuditName")
    @ResponseBody
    public String getAuditName(HttpServletRequest request) {
        AuditEntity auditEntity = (AuditEntity) request.getSession().getAttribute("auditObj");
       if (null==auditEntity){
           return "";
       }
        return auditEntity.getAuditName();

    }


}
