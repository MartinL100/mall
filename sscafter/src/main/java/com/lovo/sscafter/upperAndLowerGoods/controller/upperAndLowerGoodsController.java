package com.lovo.sscafter.upperAndLowerGoods.controller;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class upperAndLowerGoodsController {
    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("saveGoods")
    public  String saveGoodsEntity(GoodsEntity goods){
        goodsService.saveGoods(goods);
        return "true";
    }
    @RequestMapping("goodsPage")
    public Map<String,Object> getGoodsList(int page, int rows, @RequestParam("goodsState") String goodsState,
                                           @RequestParam("goodsType") String goodsType,
                                           @RequestParam("goodsBooking") String goodsBooking,
                                           @RequestParam("goodsName") String goodsName){

        Map<String,Object> map=new HashMap<>();
        List<GoodsEntity> list =goodsService.getGoodsList(page,rows,goodsState,goodsType,goodsBooking,goodsName);
        map.put("list",list);
        map.put("page",page);
       long total=goodsService.getGoodsCount(goodsState,goodsType,goodsBooking,goodsName);
        map.put("total",total);
        return map;
    }


}
