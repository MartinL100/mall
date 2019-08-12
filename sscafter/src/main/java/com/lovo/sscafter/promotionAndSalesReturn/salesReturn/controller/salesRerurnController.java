package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity2;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.IReturnGoodsService;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.IReturnOrderService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * 退货编号
 */
@RestController//只返回数据
@RequestMapping("findPutaway")
public class salesRerurnController {

    @Autowired
    private IReturnGoodsService goodsService;

    @Autowired
    private IReturnOrderService orderService;


    //调用atvicmq的模板
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;



    /**
     * 根据退货单单号查询商品
     * @param oderNum 退货单单号
     * @return 商品集合json
     */
    @RequestMapping("/findGoodsByreturnOderNum/{returnOderNum}")
    public String findGoodsByreturnOderNum(@PathVariable("returnOderNum")String oderNum) throws JsonProcessingException {
    List<ReturnGoodsEntity2> list=goodsService.findByReturnOderId(oderNum);
    ObjectMapper mapper=new ObjectMapper();
    //集合转换未字符串
      String json=  mapper.writeValueAsString(list);
        return json;
}




//监听退货订单发过来的数据,并显示到页面
    @RequestMapping("/show")
    @JmsListener(destination = "orderReturnMQ")
    public Map<String,Object> lister(String json) throws IOException {
        //将字符串转换为map集合
        ObjectMapper mapper=new ObjectMapper();
            Map<String,Object> map= mapper.readValue(json,Map.class);
            //退货单编号
          String returnId= (String) map.get("returnId");
          //商品id
            String goodsId=(String)map.get("goodsId");

        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date= time.format(new Date());

        ReturnOrderEntity orderEntity=new ReturnOrderEntity();
        //退货订单号
        orderEntity.setReturnOrderId(returnId);
        //退货订单状态
        orderEntity.setReturnStatus(1);
        //订单号
        orderEntity.setOderNum(goodsId);
        //当前时间格式化
        orderEntity.setReturnTime(date);

        orderService.sava(orderEntity);

        List<ReturnOrderEntity> orderEntities=new ArrayList<>();
        orderEntities.add(orderEntity);
        int nowPage=0;
        int total=100;

        Map<String,Object> map2=new HashMap<>();

        map2.put("rows",orderEntities);
        map2.put("page",nowPage);
        map2.put("total",total);
        return map2;
    }

    //监听退货商品的MQ
    @JmsListener(destination = "goodsReturnMQ")
    public void getGoodsReturnMQ(String json) throws IOException {
        //将字符转换为集合,然后保存到字符串
        ObjectMapper mapper=new ObjectMapper();

       List<ReturnGoodsEntity2> goodsEntities= mapper.readValue(json,List.class);
        for (ReturnGoodsEntity2 goods: goodsEntities ) {
            goodsService.sava(goods);
        }
        int nowPage=0;
        int total=100;

    }

    //点击确认收货
    @RequestMapping("")
    public void updae(String json) throws IOException {
        //得到页面的退货订单id，根据id将商品状态改为已收货
        //将字符串转换为集合
        ObjectMapper mapper=new ObjectMapper();
      List<String> list=  mapper.readValue(json,List.class);
        for (String f:list) {
            //将状态修改为  已收货
            goodsService.updatGoodsState(f,2);
        }
        //创建消息队列
        ActiveMQQueue queue=new ActiveMQQueue("oderMQ");
        //将队列放入到MQ中
        jmsMessagingTemplate.convertAndSend(queue,json);
    }

    //监听审核MQ
    @JmsListener(destination = "returnAuditMQ")
    public void listerReturnAuditMQ(String json) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        List<String> list= mapper.readValue(json,List.class);
        for (String f:list) {
            //将退货状态修改为已收货
            goodsService.updatGoodsState(f,3);
        }
    }
}
