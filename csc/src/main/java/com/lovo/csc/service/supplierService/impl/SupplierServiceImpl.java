package com.lovo.csc.service.supplierService.impl;

import com.lovo.csc.dao.supplierDao.IBaseDao;
import com.lovo.csc.dao.supplierDao.ISupplierDao;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.service.supplierService.ISupplierService;
import com.lovo.csc.vo.suppliervo.SupplierVO;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "supplierService")
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue SHHMQ;
    @Autowired
    private ISupplierDao supplierDao;
    @Autowired
    private IBaseDao baseDao;
    @Override
    public void save(SupplierEntity supplier) {
        supplierDao.save(supplier);
    }

    @Override
    public SupplierEntity findBySupplierId(String supplierId) {
        return supplierDao.findBySupplierId(supplierId);
    }

    @Override
    public List<SupplierEntity> findAll(int page, int rows,String supplierName,String supplierType,String supplierLevel, String supplierTag) {
        return baseDao.findAllSupplier((page-1)*rows,rows,supplierName,supplierType,supplierLevel,supplierTag);
    }

    @Override
    public long countAll(String supplierName,String supplierType,String supplierLevel, String supplierTag) {
        return baseDao.countSupplier(supplierName,supplierType,supplierLevel,supplierTag);
    }

    @Override
    public void AJAXSupplier(SupplierVO vo) {
        SupplierEntity s=supplierDao.findBySupplierId(vo.getSupplierId());
        s.setSupplierLevel(vo.getSupplierLevel());
        s.setSupplierTag(vo.getSupplierTag());
        supplierDao.save(s);
        jmsMessagingTemplate.convertAndSend(SHHMQ,vo);
    }
}
