package com.lovo.sscbfore.site.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.site.service.IsiteService;
import com.lovo.sscbfore.user.entity2.SiteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        siteEntityList.forEach(System.out::println);
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
    @RequestMapping("/update/sid/{sid}")
    public String updateSite(@PathVariable("sid") String sid, Model model){
//        int result=isiteService.updateSite(sid);
        SiteEntity siteEntit=isiteService.findSiteBySid(sid);
        model.addAttribute("site",siteEntit);
        System.out.println(siteEntit);
        return "forward:/static/page/siteUpdate.html";
    }




        //TODO 修改默认地址
    @RequestMapping("/update/execute")
    public String updateExecuteSite(SiteEntity siteEntity){
        SiteEntity site=isiteService.findSiteISSiteDefault();
        siteEntity.setSiteDefault(1);
        System.out.println(siteEntity);
        if (1==site.getSiteDefault()){
            site.setSiteDefault(0);
            isiteService.updateSite(site);
        }
        int result=isiteService.updateSite(siteEntity);
        return "";
    }
           //TODO 删除地址
    @RequestMapping("/delete/sid/{sid}")
    public String deleteSite(@PathVariable("sid") String sid){

        int result=isiteService.deleteSite(sid);

        return "";
    }
          //TODO 保存
    @RequestMapping("/save")
    public String saveSite(SiteEntity siteEntity){
       isiteService.saveSite(siteEntity);


        return "";
    }


}
