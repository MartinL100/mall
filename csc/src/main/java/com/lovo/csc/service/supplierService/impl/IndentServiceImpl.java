package com.lovo.csc.service.supplierService.impl;

import com.lovo.csc.dao.supplierDao.IBaseDao;
import com.lovo.csc.dao.supplierDao.IIndentDao;
import com.lovo.csc.entity.IndentEntity;
import com.lovo.csc.service.supplierService.IIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service(value = "indentService")
public class IndentServiceImpl implements IIndentService {
    @Autowired
    private IIndentDao indentDao;
    @Autowired
    private IBaseDao baseDao;
    @Override
    public void save(IndentEntity indent) {
        indentDao.save(indent);
    }

    @Override
    public IndentEntity findByIndentId(String indentId) {
        return indentDao.findByIndentId(indentId);
    }

    @Override
    public List<IndentEntity> findIndent(int page, int rows, String indentId, String startDate, String endDate, String indentStatus) {
        return baseDao.findAllIndent((page-1)*rows,rows,indentId,startDate,endDate,indentStatus);
    }

    @Override
    public long countAll(String indentId, String startDate, String endDate, String indentStatus) {
        return baseDao.countIndent(indentId,startDate,endDate,indentStatus);
    }
}
