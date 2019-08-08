package com.lovo.csc.service.promotionService.impl;

import com.lovo.csc.dao.promotiondao.IFindSuperSaleAuditDao;
import com.lovo.csc.dao.promotiondao.ISuperSaleAuditDao;
import com.lovo.csc.entity.promotionentity.SuperSaleAudit;
import com.lovo.csc.service.promotionService.ISuperSaleAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("superSaleAuditService")

public class SuperSaleServiceImpl implements ISuperSaleAuditService {

    @Autowired
    private ISuperSaleAuditDao superSaleAuditDao;
    @Autowired
    IFindSuperSaleAuditDao findSuperSaleAuditDao;
    @Override
    public void saveSuperSaleAudit(SuperSaleAudit superSaleAudit) {
            superSaleAuditDao.save(superSaleAudit);
    }
    @Override
    public List<SuperSaleAudit> findSuperSaleAudits
            (String startTime, String endTime, String auditResult,
             String goodsName,String firstResult,String maxResults) {
       //返回查询结果
        return  findSuperSaleAuditDao.findSuperSale(startTime,endTime,auditResult,goodsName, firstResult, maxResults);
    }

    @Override
    @Transactional
    public void updateSuperSaleAudit(String auditMan, String auditTime, String auditResult, int id) {
        //执行更改
        superSaleAuditDao.updateSuperSaleAudit(auditMan,auditTime,auditResult,id);
    }

    @Override
    public SuperSaleAudit findSuperSaleAuditById(int id) {
        return superSaleAuditDao.findSuperSaleAuditById(id);
    }
}
