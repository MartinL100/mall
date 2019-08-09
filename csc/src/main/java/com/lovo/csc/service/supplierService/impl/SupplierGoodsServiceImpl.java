package com.lovo.csc.service.supplierService.impl;

import com.lovo.csc.dao.supplierDao.ISupplierGoodsDao;
import com.lovo.csc.entity.SupplierGoodsEntity;
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
    @Autowired
    private ActiveMQQueue returnSupplierGoodsAudit;
    @Autowired
    private ISupplierGoodsDao supplierGoodsDao;
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
        jmsMessagingTemplate.convertAndSend(returnSupplierGoodsAudit,list);
    }

    @Override
    public SupplierGoodsEntity findByCodeId(String codeId) {
        return supplierGoodsDao.findByCodeId(codeId);
    }
}
