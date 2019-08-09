package com.lovo.psc.orderGoodsZhou.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.psc.orderGoodsZhou.dto.IndentDto;
import com.lovo.psc.orderGoodsZhou.dto.SendGoodsDto;
import com.lovo.psc.orderGoodsZhou.service.IIdentService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MQProcessor {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IIdentService service;
    //监听报价审核并按发给过来的订单编号修改订单状态
    @JmsListener(destination = "priceMQ")
    public void firstProcessor(String message){
        ObjectMapper mapper=new ObjectMapper();
        IndentDto indentDto=null;
        try {
            indentDto=mapper.readValue(message, IndentDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.updateByIndentId(indentDto.getIndentId(),"未支付");
    }
    //监听结算审核修改订单状态和结算时
    @JmsListener(destination = "scopeMQ2")
    public void twoProcessor(String message){
        ObjectMapper mapper=new ObjectMapper();
        IndentDto indentDto=null;
        String jsonGoodsMQ=null;
        try {
            indentDto=mapper.readValue(message, IndentDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //新建goodsMQ,
        // 监听到id再通过id查数据把数据转换成json字符串再发送给goodsMQ，之后再执行修改操作
        ActiveMQQueue queue=new ActiveMQQueue("goodsMQ");
        //把数据转换成json字符串
        List<SendGoodsDto> list =service.sendGoodsMQ(indentDto.getIndentId());
        ObjectMapper mapper2=new ObjectMapper();
        try {
            jsonGoodsMQ=mapper2.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //执行发送
        jmsMessagingTemplate.convertAndSend(queue,jsonGoodsMQ);
        //执行修改
        service.updateByindentId2(indentDto.getIndentId(),"已支付",indentDto.getIndentDate());
    }
}
