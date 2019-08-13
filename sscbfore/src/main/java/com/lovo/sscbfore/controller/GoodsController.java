package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class GoodsController {
    @Autowired
    ObjectMapper objectMapper;
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;

@RequestMapping("initSelect")
    public String initSelect(){
//        模拟获取远程下拉数据
    String info = "";
        info = restTemplate.getForEntity(UrlUtil.INIT_SELECT,String.class).getBody();
        
        return info;
    }
    //查找预售商品
    @RequestMapping("findGoodsPreSell")
    public  String findGoodsPreSell(String goodsType,String goodsName,String page,String limit){
        if(StringUtils.isEmpty(goodsType)){
            goodsType="null";
        }if(StringUtils.isEmpty(goodsName)){
            goodsName="null";
        }if(StringUtils.isEmpty(page)){
            page="1";
        }if(StringUtils.isEmpty(limit)){
            limit="10";
        }

        String info = "";
            String url = UrlUtil.FIND_GOODS_PRESELL+goodsType+"/"+goodsName+"/"+page+"/"+limit;
        info = restTemplate.getForEntity(url,String.class).getBody();
        return info ;
    }

    //查找促销商品
    @RequestMapping("findGoodsCuXiao")
    public  String findGoodsCuXiao(String goodsType,String goodsName,String page,String limit){
        if(StringUtils.isEmpty(goodsType)){
            goodsType="null";
        }if(StringUtils.isEmpty(goodsName)){
            goodsName="null";
        }if(StringUtils.isEmpty(page)){
            page="1";
        }if(StringUtils.isEmpty(limit)){
            limit="10";
        }
        String url = UrlUtil.FIND_GOODS_CUXIAO+page+"/"+limit+"/"+goodsType+"/"+goodsName;
        String info  = restTemplate.getForEntity(url,String.class).getBody();
        return info ;
    }
    //查找所有商品
    @RequestMapping("findGoodsAll")
    public  String findGoodsAll(String goodsType,String goodsName,String page,String limit){
        if(StringUtils.isEmpty(goodsType)){
            goodsType="null";
        }if(StringUtils.isEmpty(goodsName)){
            goodsName="null";
        }if(StringUtils.isEmpty(page)){
            page="1";
        }if(StringUtils.isEmpty(limit)){
            limit="10";
        }
        String url = UrlUtil.FIND_ALL_GOODS+page+"/"+limit+"/"+goodsType+"/"+goodsName;
        String  info = restTemplate.getForEntity(UrlUtil.FIND_GOODS_PRESELL+goodsType+"/"+goodsName+"/"+page+"/"+limit,String.class).getBody();
        return info ;
    }
}
