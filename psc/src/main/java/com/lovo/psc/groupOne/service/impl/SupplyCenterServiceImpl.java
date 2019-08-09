package com.lovo.psc.groupOne.service.impl;

import com.lovo.psc.groupOne.dao1.ISupplyCenterDao1;
import com.lovo.psc.entity.SupplyCenterEntity;
import com.lovo.psc.groupOne.service.ISupplyCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="supplyCenterService")
public class SupplyCenterServiceImpl implements ISupplyCenterService {
    @Autowired
    private ISupplyCenterDao1 supplyCenterDao1;
    @Override
    public void saveSupplyCenter(SupplyCenterEntity supplyCenter) {
        supplyCenterDao1.save(supplyCenter);
    }

    @Override
    public List<SupplyCenterEntity> findBySupplierIdAnAndBjTag(String supplierId, String bjTag) {
        return supplyCenterDao1.findBySupplierIdAndBjTag(supplierId,bjTag);
    }

    @Override
    public void updatePrice(SupplyCenterEntity supplyCenter) {
        supplyCenterDao1.save(supplyCenter);
    }

    @Override
    public SupplyCenterEntity findBySupplyId(String supplyId) {
        return supplyCenterDao1.findBySupplyId(supplyId);
    }
}
