package com.lovo.psc.groupOne.service.impl;

import com.lovo.psc.groupOne.dao.ISupplierDao;
import com.lovo.psc.entity.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lovo.psc.groupOne.service.ISupplierService;

@Service(value="supplierService")
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private ISupplierDao supplierDao;

    @Override
    public SupplierEntity login(String supplierName, String supplierPwd) {
        return supplierDao.findBySupplierNameAndSupplierPwd(supplierName,supplierPwd);
    }

    @Override
    public SupplierEntity findBySupplierId(String supplierId) {
        return supplierDao.findBySupplierId(supplierId);
    }
}
