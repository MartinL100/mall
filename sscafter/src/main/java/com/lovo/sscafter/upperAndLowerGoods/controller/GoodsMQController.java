package com.lovo.sscafter.upperAndLowerGoods.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsMQController {

    @Autowired
    private IGoodsService goodsService;

    @JmsListener(destination = "addGoodsMQ")
    public  String saveGoodsEntity(String message){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            GoodsEntity goods=   objectMapper.readValue(message, GoodsEntity.class);
            goods.setGoodsState("下架");
            goods.setLowerTime("手动下架");
            goodsService.saveGoods(goods);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }

    @RequestMapping("/dynamicPresell/{goodsType}/{goodsName}/{currentPage}/{pageNumber}")
      public String dynamicPresell(@PathVariable("goodsType") String goodsType,@PathVariable("goodsName") String goodsName,
                                 @PathVariable("currentPage") int currentPage,@PathVariable("pageNumber") int pageNumber ){
        String result="";
        int page=currentPage;
        int rows=pageNumber;
        String goodsState="";
        String goodsBooking="预售";
        if(null==goodsName){
            goodsName="";
        }
       // System.out.printf(""+goodsState+""+page+"/"+rows);
        Map<String,Object> map=new HashMap<>();
        List<GoodsEntity> list =goodsService.getGoodsList((page-1)*rows,rows,goodsState,goodsType,goodsBooking,goodsName);
        map.put("rows",list);
        map.put("page",page);
        long total=goodsService.getGoodsCount(goodsState,goodsType,goodsBooking,goodsName);
        map.put("total",total);
        ObjectMapper obj =new ObjectMapper();
        try {
           result= obj.writeValueAsString(map);
          //  System.out.printf(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }




}
