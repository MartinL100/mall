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
//    @RequestMapping("initGoods")
//    public String initGoods(){
//        //模拟预售
//        String info = "{\"total\":2,\"page\":1,\"rows\":[{\"goodsId\":\"ssss\",\"goodsName\":\"苹果\",\"goodsType\":\"水果\",\"goodsNorms\":\"qqwee\",\"goodsUnit\":null,\"goodsBid\":12.12,\"goodsPath\":\"1.jpg\",\"goodsPrice\":\"100\",\"goodsDiscount\":12,\"goodsNum\":343,\"goodsState\":\"上架\",\"goodsBooking\":\"未预售\",\"promotionState\":null,\"shelfTime\":null,\"lowerTime\":null},{\"goodsId\":\"1116\",\"goodsName\":\"苹果6\",\"goodsType\":\"水果\",\"goodsNorms\":\"qqwee\",\"goodsUnit\":null,\"goodsBid\":12.12,\"goodsPath\":\"2.jpg\",\"goodsPrice\":\"130\",\"goodsDiscount\":12,\"goodsNum\":343,\"goodsState\":\"上架\",\"goodsBooking\":\"未预售\",\"promotionState\":null,\"shelfTime\":null,\"lowerTime\":null}]}";
//        return  info;
//    }

@RequestMapping("initSelect")
    public String initSelect(){
//        模拟获取远程下拉数据
    String info = "";
        info = restTemplate.getForEntity(UrlUtil.INIT_SELECT,String.class).getBody();
    System.out.println(info);
//        String info ="{\"SG\":\"水果\",\"YF\":\"衣服\",\"KZ\":\"裤子\"}";
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
            page="null";
        }if(StringUtils.isEmpty(limit)){
            limit="null";
        }
        String info = "{\"total\":2,\"page\":1,\"rows\":[{\"goodsId\":\"ssss\",\"goodsName\":\"促销香蕉\",\"goodsType\":\"水果\",\"goodsNorms\":\"qqwee\",\"goodsUnit\":null,\"goodsBid\":12.12,\"goodsPath\":\"1.jpg\",\"goodsPrice\":\"100\",\"goodsDiscount\":12,\"goodsNum\":343,\"goodsState\":\"上架\",\"goodsBooking\":\"未预售\",\"promotionState\":null,\"shelfTime\":null,\"lowerTime\":null},{\"goodsId\":\"1116\",\"goodsName\":\"库尔勒香梨\",\"goodsType\":\"水果\",\"goodsNorms\":\"qqwee\",\"goodsUnit\":null,\"goodsBid\":12.12,\"goodsPath\":\"2.jpg\",\"goodsPrice\":\"130\",\"goodsDiscount\":12,\"goodsNum\":343,\"goodsState\":\"上架\",\"goodsBooking\":\"未预售\",\"promotionState\":null,\"shelfTime\":null,\"lowerTime\":null}]}";


        info = restTemplate.getForEntity(UrlUtil.FIND_GOODS_CUXIAO+goodsType+"/"+goodsName+"/"+page+"/"+limit,String.class).getBody();
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
            page="null";
        }if(StringUtils.isEmpty(limit)){
            limit="null";
        }
        String info = "{\"total\":2,\"page\":1,\"rows\":[{\"goodsId\":\"ssss\",\"goodsName\":\"所有香蕉\",\"goodsType\":\"水果\",\"goodsNorms\":\"qqwee\",\"goodsUnit\":null,\"goodsBid\":12.12,\"goodsPath\":\"1.jpg\",\"goodsPrice\":\"100\",\"goodsDiscount\":12,\"goodsNum\":343,\"goodsState\":\"上架\",\"goodsBooking\":\"未预售\",\"promotionState\":null,\"shelfTime\":null,\"lowerTime\":null},{\"goodsId\":\"1116\",\"goodsName\":\"库尔勒香梨\",\"goodsType\":\"水果\",\"goodsNorms\":\"qqwee\",\"goodsUnit\":null,\"goodsBid\":12.12,\"goodsPath\":\"2.jpg\",\"goodsPrice\":\"130\",\"goodsDiscount\":12,\"goodsNum\":343,\"goodsState\":\"上架\",\"goodsBooking\":\"未预售\",\"promotionState\":null,\"shelfTime\":null,\"lowerTime\":null}]}";
        String url = UrlUtil.FIND_GOODS_PRESELL+goodsType+"/"+goodsName+"/"+page+"/"+limit;
        System.out.println(url);
//        info = restTemplate.getForEntity(UrlUtil.FIND_GOODS_PRESELL+goodsType+"/"+goodsName+"/"+page+"/"+limit,String.class).getBody();
        return info ;
    }
}
