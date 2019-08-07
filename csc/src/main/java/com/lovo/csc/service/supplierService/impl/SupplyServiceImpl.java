package com.lovo.csc.service.supplierService.impl;

import com.lovo.csc.dao.supplierDao.IBaseDao;
import com.lovo.csc.dao.supplierDao.ISupplyDao;
import com.lovo.csc.entity.SupplyEntity;
import com.lovo.csc.service.supplierService.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "supplyService")
public class SupplyServiceImpl implements ISupplyService {
    @Autowired
    private ISupplyDao supplyDao;
    @Autowired
    private IBaseDao baseDao;
    @Override
    public void save(SupplyEntity supply) {
        supplyDao.save(supply);
    }

    @Override
    public List<SupplyEntity> findSupply(String indentId) {
        return null;
    }

    @Override
    public List<SupplyEntity> findAll(int page, int rows, String goodsName, String indentStatus) {
        return null;
    }

    @Override
    public long countAll(String goodsName, String indentStatus) {
        return 0;
    }
}
