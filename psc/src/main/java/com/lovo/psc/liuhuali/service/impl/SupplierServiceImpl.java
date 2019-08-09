package com.lovo.psc.liuhuali.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.psc.liuhuali.dao.ISupplierDao;
import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.liuhuali.service.ISupplierService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;



@Service(value = "supplierService3")
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    //注入MQ
    @Autowired
    private ActiveMQQueue ZCMQ;
    @Autowired
    private ISupplierDao supplierDao;



    //保存注册用户
    public void savaSupplier(SupplierEntity supplier) throws JsonProcessingException {
        supplierDao.save(supplier);
        //向MQ中发送数据
        jmsMessagingTemplate.convertAndSend(ZCMQ, new ObjectMapper().writeValueAsString(supplier));

    }

    @Override
    public void updateSupplier(SupplierEntity supplier) {
        supplierDao.save(supplier);

    }


    @Override
    public SupplierEntity getSupplierEntityByName(String supplierName) {
        return supplierDao.getSupplierEntityByName(supplierName);
    }

    @Override
    public SupplierEntity findBySupplierId(String supplierId) {
        return supplierDao.findById(supplierId).get();
    }


}

