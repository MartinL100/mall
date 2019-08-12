package com.lovo.csc.controller.salesReturnController;

import com.alibaba.fastjson.JSONArray;

import com.lovo.csc.entity.salesReturnEntity.GoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;
import com.lovo.csc.service.depositService.IDepositService;
import com.lovo.csc.service.salesReturnService.IOrderGoodsService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@RestController
public class OrderController {
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private IDepositService depositService;


    //修改保存用户审核信息
    @RequestMapping("updateOrderGoods.lovo")
    public String updateOrderGoods(OrderGoodsEntity orderGoodsEntity, GoodsEntity goodsEntity, HttpServletRequest requst) {
        Long goodsNum = goodsEntity.getGoodsNum();
        Float goodsPrace = goodsEntity.getGoodsPrice();
        String str = depositService.salesReturn(orderGoodsEntity.getOrderName(), new Double(goodsNum * goodsPrace).doubleValue());
        String goodsState = "";
        String id = null;
        id = orderGoodsService.updateOrderGoods(orderGoodsEntity, goodsState);
        if (null != id && !"".equals(id)&&str.equals("退款成功")) {
            return "{'successMsg':'操作成功'}";
        }
        return "{'errorMsg':'操作失败'}";

    }


    //监听用户MQ 如果有新数据则保存到数据库中
    //并实现服务器主推
    @JmsListener(destination = "returnAuditMQ ")
//    @RequestMapping("savaOrderGoodsMessage.lovo")
    public String savaGoods(String message) {
        OrderGoodsEntity order = restTemplate.getForEntity("http://sscafter/findGoodsByreturnOderNum/'" + message + "'", OrderGoodsEntity.class).getBody();
        orderGoodsService.savaOrderGoods(order);


        // 发给后端
        Object obj = JSONArray.toJSON(order);
        String json = obj.toString();

        ActiveMQQueue gueue = new ActiveMQQueue("returnAuditMQ");
        jmsMessagingTemplate.convertAndSend(gueue, json);


        //发给前端
        Object o = JSONArray.toJSON(order);
        String jsons = obj.toString();

        ActiveMQQueue g = new ActiveMQQueue("returnSuccessMQ");
        jmsMessagingTemplate.convertAndSend(g, jsons);


        return "{'message':'您新的审核请求，请及时处理'}";
    }


}
