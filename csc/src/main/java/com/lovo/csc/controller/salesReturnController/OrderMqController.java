package com.lovo.csc.controller.salesReturnController;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.dto.salesReturndto.ReturnGoodsDto;
import com.lovo.csc.entity.salesReturnEntity.*;
import com.lovo.csc.service.salesReturnService.IScopeOrderService;
import com.lovo.csc.util.WebSocketScopeServer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    @JmsListener(destination = "returnGoodsMQTwo5 ")
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
            s.setOrderState("未审核");
            s.setOrderOpinion("没意见");
            OrderEntity o=new OrderEntity();
            o.setGoodsName(dto.getGoodsName());
            o.setGoodsBid(dto.getGoodsBid());
            o.setGoodsNum(dto.getReturnGoodsNum());
            o.setGoodsId(dto.getGoodsId());
            o.setScopeOrder(s);

            List<String> list = new ArrayList<>();

            list.add(dto.getSupplierId());
              ObjectMapper mapper = new ObjectMapper();
             String idStr= mapper.writeValueAsString(list);
            String supplyName= restTemplate.getForEntity
                    ("http://psc/findSupplierNameBySupplierId/"+idStr,String.class).getBody();
               s.setSupplierName(supplyName);
                        scopeOrderService.sava(s);


            //发送给供货商退货订单信息
            Object obj = JSONArray.toJSON(s);
            String json = obj.toString();
//            System.out.println(json);
            ActiveMQQueue gueue=new ActiveMQQueue("backReturnScopeGoodsMq");
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
