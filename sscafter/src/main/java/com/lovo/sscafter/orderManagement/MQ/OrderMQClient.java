package com.lovo.sscafter.orderManagement.MQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.orderManagement.entity.DTO.ReturnGoodsDTO;
import com.lovo.sscafter.orderManagement.service.IGoodsManagementService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMQClient {

    IGoodsManagementService iGoodsManagementService;

    @JmsListener(destination = "orderReturnMQ")
    public void orderMQ(String orderReturn){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ReturnGoodsDTO returnGoodsDTO =objectMapper.readValue(orderReturn, ReturnGoodsDTO.class);
            iGoodsManagementService.updateOrderReturn(returnGoodsDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
