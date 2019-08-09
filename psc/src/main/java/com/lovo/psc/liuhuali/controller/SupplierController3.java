package com.lovo.psc.liuhuali.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.liuhuali.service.ISupplierService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 供应商Controller
 */
@Controller
public class SupplierController3 {
    @Autowired
    private ISupplierService supplierService3;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
        @RequestMapping("savaSupplier")
        @ResponseBody
    public String savaSupplier(SupplierEntity supplier, HttpServletRequest servletRequest) throws JsonProcessingException {

        //把注册用户保存到数据库0
            supplier.setSupplierTag("0");
            supplierService3.savaSupplier(supplier);

        ObjectMapper obj=new ObjectMapper();
        String result= obj.writeValueAsString(supplier);
        //发送到MQ进行审核
        ActiveMQQueue queue=new ActiveMQQueue("ZCMQ");
        jmsMessagingTemplate.convertAndSend(queue,result);

        return "ok";
    }



    //判断供应商用户名是否存在
    @RequestMapping("/findSupplier")
    @ResponseBody
    public String findSupplier(String supplierName){

        SupplierEntity supplier1= supplierService3.getSupplierEntityByName(supplierName);
            if(null==supplier1){
                return "1";
            }
        return "2";
    }

    public void update111(){

    }

}
