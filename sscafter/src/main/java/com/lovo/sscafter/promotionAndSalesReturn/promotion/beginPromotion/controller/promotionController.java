package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.controller;

import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service.IPromotionGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 促销controller
 */
@Controller
@RequestMapping("promotion")
public class promotionController {

    @Autowired
    private IPromotionGoodsService service;

    @RequestMapping("/findAll")
    @ResponseBody//只返回数据
    public Map<String,Object> findAll(String goodsName, String goodsType, int page, int rows){
        //动态查询总行数
      int pageCount= (int)service.findCount(goodsName,goodsType);

      //计算起始位置
      int nowPage=(page-1)*rows;

       List<GoodsEntity> list= service.findBygoodsNameAndgoodsState(goodsName,goodsType,nowPage,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",list);
        map.put("page",nowPage);
        map.put("total",pageCount);
        return map;
    }
}
