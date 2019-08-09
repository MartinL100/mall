package com.lovo.csc.controller.salesReturnController;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.dto.salesReturndto.ReturnGoodsDto;
import com.lovo.csc.entity.salesReturnEntity.*;
import com.lovo.csc.service.salesReturnService.IScopeOrderService;
import com.lovo.csc.util.HttpPostUtil;
import com.lovo.csc.util.WebSocketScopeServer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;

@RestController
public class OrderMqController {
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IScopeOrderService scopeOrderService;
    private static int initNum=1;



    //监听供货商MQ 如果有新数据则保存到数据库中
    //并实现服务器主推
    @JmsListener(destination = "returnGoodsMQ ")
//    @RequestMapping("savaOrderGoodsMessage.lovo")
    public String savaOrderGoods(String message) {

        ReturnGoodsDto dto=null;
        try {
            dto=new ObjectMapper().readValue(message,ReturnGoodsDto.class);

            ScopeOrderEntity s=new ScopeOrderEntity();
            s.setOrderId(dto.getReturnGoodsId());
            s.setCloseTime(dto.getReturnGoodsDate());
            s.setGoodsSales(dto.getReturnGoodsCause());
            s.setOrderAudit("秦某某");
            OrderEntity o=new OrderEntity();
            o.setGoodsName(dto.getGoodsName());
            o.setGoodsBid(dto.getGoodsBid());
            o.setGoodsNum(dto.getReturnGoodsNum());
            o.setGoodsId(dto.getGoodsId());
            o.setScopeOrder(s);

//            String goods= HttpPostUtil.post("http://psc/findGoodsId/'"+dto.getReturnGoodsId()+"'");
            SupplierEntity1 goods= restTemplate.getForEntity
                    ("http://psc/findGoodsId/'"+dto.getReturnGoodsId()+"'",SupplierEntity1.class).getBody();
               s.setSupplierName(goods.getSupplierName());
                        scopeOrderService.sava(s);

            Object obj = JSONArray.toJSON(s);
            String json = obj.toString();


            ActiveMQQueue gueue=new ActiveMQQueue("backMq");
           jmsMessagingTemplate.convertAndSend(gueue,json);



        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null==dto){
            return "{'message':'没有信息'}";
        }
        try {
            WebSocketScopeServer.blockingQueue.put(dto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{'message':'您新的审核请求，请及时处理'}";
    }

}
