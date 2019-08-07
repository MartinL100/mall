package com.lovo.csc.service.supplierService.impl;

import com.lovo.csc.dao.supplierDao.ISupplierGoodsDao;
import com.lovo.csc.entity.SupplierGoodsEntity;
import com.lovo.csc.service.supplierService.ISupplierGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "supplierGoodsService")
public class SupplierGoodsServiceImpl implements ISupplierGoodsService {
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
}
