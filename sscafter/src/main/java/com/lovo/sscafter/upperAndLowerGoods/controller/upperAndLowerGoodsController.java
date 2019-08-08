package com.lovo.sscafter.upperAndLowerGoods.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;

import com.lovo.sscafter.goodsStock.service.IGoodsTypeService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("upperAndLower")
public class upperAndLowerGoodsController {
    @Autowired
    private IGoodsTypeService goodsTypeService;

    @Autowired
    private IGoodsService goodsService;


    @RequestMapping("/findAllgoodsType")

    public List<GoodsTypeEntity> goodsTypeFind(){
        List<GoodsTypeEntity> list=  goodsTypeService.findAll();

        return  list;
    }

    @RequestMapping("/updateGoodsLowerTime")

    public  void updateGoodsLowerTime(String goodsId,String lowerTime){
        System.out.printf(""+lowerTime);
        goodsService.updateGoodsLowerTime(goodsId,lowerTime);
    }

    @RequestMapping("/updateGoodsStateBooking1")

    public  void updateStateBooking1(String list){
        List<String> listString =jsonString(list);
        for (String goodsId: listString ) {
            goodsService.updateStateBooking1(goodsId);
        }
    }

    @RequestMapping("/updateGoodsStateBooking")

    public  void updateStateBooking(String list){
        List<String> listString =jsonString(list);
        for (String goodsId: listString ) {
            goodsService.updateStateBooking(goodsId);
        }
    }


    @RequestMapping("/updateGoodsStateLower")

    public void updateStateLower(String list){
        List<String> listString =jsonString(list);
        for (String goodsId: listString ) {
            goodsService.updateStateLower(goodsId);
        }
    }

    @RequestMapping("/updateGoodsStateUpper")

    public void updateStateUpper(String list){
        List<String> listString =jsonString(list);
        for (String goodsId: listString ) {
            goodsService.updateStateUpper(goodsId);
        }


    }

    @RequestMapping("/goodsPage")
    public Map<String,Object> getGoodsList(int page, int rows, String goodsState, String goodsType, String goodsBooking, String goodsName){

        if(StringUtil.blString(goodsState)){
            goodsState="下架";
        }
        Map<String,Object> map=new HashMap<>();
        List<GoodsEntity> list =goodsService.getGoodsList((page-1)*rows,rows,goodsState,goodsType,goodsBooking,goodsName);
        map.put("rows",list);
        map.put("page",page);
       long total=goodsService.getGoodsCount(goodsState,goodsType,goodsBooking,goodsName);
        map.put("total",total);
//
//        ObjectMapper obj =new ObjectMapper();
//        try {
//            String result= obj.writeValueAsString(map);
//            System.out.printf(result);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return map;
    }

public   List<String> jsonString(String list){
    List<String> listString=null;
    ObjectMapper mapper=new ObjectMapper();
    try {
        //将json字符串转换为list集合
        listString=  (List<String>)mapper.readValue(list,Object.class);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return listString;
}

}
