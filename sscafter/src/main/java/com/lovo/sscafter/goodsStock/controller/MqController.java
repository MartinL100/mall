package com.lovo.sscafter.goodsStock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.goodsStock.dto.SendGoodsDto;
import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import com.lovo.sscafter.goodsStock.entity.OrderEntity;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsStockService;
import com.lovo.sscafter.goodsStock.service.IOrderGoodsService;
import com.lovo.sscafter.goodsStock.service.IOrderServicr;
import com.lovo.sscafter.util.MqUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MqController {
    @Autowired
   private IGoodsStockService goodsStockService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private IOrderServicr orderServicr;

    @JmsListener(destination = "goodsMQ")
public void orderGoodsMq(String sendGoods) throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(new Date());
        MqUtil2.queue2.put("yes");

        ObjectMapper mapper=new ObjectMapper();
        try {
    SendGoodsDto goodsDto= mapper.readValue(sendGoods, SendGoodsDto.class);
         //增加库存
            goodsStockService.updateGoodsNumByNameAndTypeAndnorms(goodsDto.getSupplyNum(),goodsDto.getGoodsName(),goodsDto.getGoodsType(),goodsDto.getGoodsNoms());
             //更改商品状态为已到货
            goodsStockService.updateGoodsTagByNameAndTypeAndnorms(goodsDto.getGoodsName(),goodsDto.getGoodsType(),goodsDto.getGoodsNoms());
       //保存订单
            OrderEntity o = new OrderEntity();
            o.setOrderId(goodsDto.getIndentId());
            orderServicr.saveOrder(o);
        //保存中间表
            OrderGoodsEntity oe= new OrderGoodsEntity();

            GoodsStockEntity goods = goodsStockService.findByNameTypeAnAndNorms(goodsDto.getGoodsName(),goodsDto.getGoodsType(),goodsDto.getGoodsNoms());
            oe.setGoods(goods);
            oe.setGoodsBid(goodsDto.getSupplyPrice());
            oe.setGoodsNum(goodsDto.getSupplyNum());
            oe.setGoodsOrderDate(nyr);
            oe.setSupplierId(goodsDto.getSupplierId());
            oe.setIsReturnGoods("否");
            oe.setOrder(o);
            orderGoodsService.saveOrderGoods(oe);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
