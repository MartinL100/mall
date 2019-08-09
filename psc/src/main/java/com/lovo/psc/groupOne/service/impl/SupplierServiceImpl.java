package com.lovo.psc.groupOne.service.impl;

import com.lovo.psc.groupOne.dao1.ISupplierDao1;
import com.lovo.psc.entity.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lovo.psc.groupOne.service.ISupplierService;

@Service(value= "supplierService1")
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private ISupplierDao1 supplierDao1;

    @Override
    public SupplierEntity login(String supplierName, String supplierPwd) {
        return supplierDao1.findBySupplierNameAndSupplierPwd(supplierName,supplierPwd);
    }

    @Override
    public SupplierEntity findBySupplierId(String supplierId) {
        return supplierDao1.findBySupplierId(supplierId);
    }
}
