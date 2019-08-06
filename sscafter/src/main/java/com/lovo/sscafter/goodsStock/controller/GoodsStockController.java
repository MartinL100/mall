package com.lovo.sscafter.goodsStock.controller;

import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsStockService;
import com.lovo.sscafter.goodsStock.service.IOrderGoodsService;
import com.lovo.sscafter.goodsStock.service.IReturnGoodsService;
import com.lovo.sscafter.goodsStock.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsStockController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IGoodsStockService goodsStockService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private ISupplyService supplyService;
    @Autowired
    private IReturnGoodsService returnGoodsService;
    @RequestMapping("/goodsStock")
    @ResponseBody
    public Map<String,Object> goodsStock(int page,int rows,String goodsName,String goodsType){
        List<GoodsStockEntity> list= goodsStockService.findAllGoodsStock(goodsName,goodsType,page,rows);
        Long ro=goodsStockService.findAllGoodsStockCount(goodsName,goodsType);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        map.put("page",page);
        map.put("total",ro);
        return map;
    }
}
