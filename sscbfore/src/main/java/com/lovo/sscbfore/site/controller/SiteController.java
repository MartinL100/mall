package com.lovo.sscbfore.site.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.site.service.IsiteService;
import com.lovo.sscbfore.user.entity2.SiteEntity;
import com.lovo.sscbfore.user.entity2.UserEntity;
import org.eclipse.jgit.transport.CredentialItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
//@RequestMapping("site")
public class SiteController {
    @Autowired
    ObjectMapper objectMapper;
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IsiteService isiteService;
    //TODO 查看 编辑 删除 页面跳转  单选框的设置值的问题
     //TODO 初始化主页
    @RequestMapping("index/user/n ")
    @ResponseBody
    public String IndexData(@PathVariable("uname") String name) throws JsonProcessingException {
        List<SiteEntity> siteEntityList = isiteService.findAllByUserSite(name);

         String str=  new ObjectMapper().writeValueAsString(siteEntityList);
         return str;
    }



     //TODO 根据用户名查找所以地址
    @RequestMapping("/index/get/username")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request) throws JsonProcessingException {
        Object user = request.getSession().getAttribute("user");
        String username="1";
        List<SiteEntity> siteEntityList = isiteService.findAllByUserSite(username);
        siteEntityList.forEach(System.out::println);
//       String str=new ObjectMapper().writeValueAsString(siteEntityList);
//       System.out.println(str);
        String goodInfo="";
        try {
            goodInfo = objectMapper.writeValueAsString(siteEntityList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        goodInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodInfo+"}";
        return goodInfo;
    }





    @RequestMapping("/index/get/siteid")
    @ResponseBody
    public SiteEntity siteId(@PathVariable("siteId" )String siteId){
        SiteEntity siteId1 = isiteService.findSiteBySid("siteId");
        return siteId1;
    }


      //TODO 修改地址信息
    @RequestMapping("/update")
    @ResponseBody
    public String updateSite(@RequestBody SiteEntity siteEntity){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName("1");
        userEntity.setUserId("1");
        siteEntity.setUserSite(userEntity);
        int result = isiteService.updateSite(siteEntity);
        return "OK";
    }


        //TODO 修改默认地址
    @RequestMapping("/update/execute")
    public String updateExecuteSite(String wid){
     isiteService.updateSiteDefaultById(wid);
        return "";
    }
           //TODO 删除地址
    @RequestMapping("/delete/sid")
    public String deleteSite( String sid){

       isiteService.deleteSite(sid);

        return "";

    }
    //TODO 保存
    @RequestMapping("/save")
    public String saveSite(SiteEntity siteEntity){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName("1");
        siteEntity.setUserSite(userEntity);
        isiteService.saveSite(siteEntity);

        return "";
    }


}
