package com.lovo.sscafter.upperAndLowerGoods.controller;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("upperAndLower")
public class upperAndLowerGoodsController {
    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/saveGoods")
    public  String saveGoodsEntity(GoodsEntity goods){
        goodsService.saveGoods(goods);
        return "true";
    }
    @RequestMapping("/goodsPage")
    public Map<String,Object> getGoodsList(int page, int rows, String goodsState, String goodsType, String goodsBooking, String goodsName){

        if(StringUtil.blString(goodsState)){
            goodsState="上架";
        }
        Map<String,Object> map=new HashMap<>();
        List<GoodsEntity> list =goodsService.getGoodsList((page-1)*rows,rows,goodsState,goodsType,goodsBooking,goodsName);
        map.put("rows",list);
        map.put("page",page);
       long total=goodsService.getGoodsCount(goodsState,goodsType,goodsBooking,goodsName);
        map.put("total",total);

        return map;
    }


}
