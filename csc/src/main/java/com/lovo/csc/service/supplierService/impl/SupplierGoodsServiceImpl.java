package com.lovo.csc.service.supplierService.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.dao.supplierDao.ISupplierDao;
import com.lovo.csc.dao.supplierDao.ISupplierGoodsDao;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.supplierEntity.SupplierGoodsEntity;
import com.lovo.csc.service.supplierService.ISupplierGoodsService;
import com.lovo.csc.vo.suppliervo.SupplierGoodsVO;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value = "supplierGoodsService")
public class SupplierGoodsServiceImpl implements ISupplierGoodsService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
//    @Autowired
//    private ActiveMQQueue returnSupplierGoodsAudit;
    @Autowired
    private ISupplierGoodsDao supplierGoodsDao;
    @Autowired
    private ISupplierDao supplierDao;
    @Override
    public void save(SupplierGoodsEntity supplierGoods) {
        supplierGoodsDao.save(supplierGoods);
    }

    @Override
    public void deleteByCodeId(String codeId) {
        supplierGoodsDao.deleteByCodeId(codeId);
    }

    @Override
    public List<SupplierGoodsEntity> findBySuppliegrId(String supplierId) {
        return supplierGoodsDao.findBySuppliegrId(supplierId);
    }

    @Override
    public List<SupplierGoodsEntity> findSupplierGoods(String goodsName, String goodsNorms) {
        return supplierGoodsDao.findSupplierGoods(goodsName,goodsNorms);
    }
    @Override
    public void AJAXSupplierGoods(String codeArray,String supplierStatusArray){
        String[] code=codeArray.split(",");
        List<SupplierGoodsVO> list=new ArrayList<>();
        for (String str:code) {
            SupplierGoodsEntity s=supplierGoodsDao.findByCodeId(str);
            s.setSupplierStatus(supplierStatusArray);
            supplierGoodsDao.save(s);
            list.add(new SupplierGoodsVO(str,supplierStatusArray));
        }
        SupplierGoodsEntity s=supplierGoodsDao.findByCodeId(code[0]);
        s.getSupplierId().setSupplierTag("已审核");
        supplierDao.save(s.getSupplierId());
        ActiveMQQueue queue=new ActiveMQQueue("returnSupplierGoodsAudit1");
        ObjectMapper mapper=new ObjectMapper();
        try {
            String v=mapper.writeValueAsString(list);
            jmsMessagingTemplate.convertAndSend(queue,v);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SupplierGoodsEntity findByCodeId(String codeId) {
        return supplierGoodsDao.findByCodeId(codeId);
    }
}
