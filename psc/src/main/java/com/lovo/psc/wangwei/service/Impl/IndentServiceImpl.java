package com.lovo.psc.wangwei.service.Impl;

import com.lovo.psc.wangwei.dao.FindinderDao;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.wangwei.dto.InderDTO;
import com.lovo.psc.wangwei.service.IIndenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "indenService")
public class IndentServiceImpl implements IIndenService {
    @Autowired
    private FindinderDao findinderDao;


    @Override
    public long CountInerList(String goodsName, String indentId, String zbTag, String ghTag, String supplyId, String startDate, String endDate) {
        return findinderDao.CountInerList( goodsName,  indentId,  zbTag,  ghTag,  supplyId,  startDate,  endDate);
    }

    @Override
    public List<InderDTO> findInerList(String goodsName, String indentId, String zbTag, String ghTag, String supplierId, String startDate, String endDate, int pageNumber, int pageSize) {
        return findinderDao.findInerList( goodsName,  indentId,  zbTag,  ghTag,  supplierId,  startDate,  endDate,  pageNumber,  pageSize);
    }

    @Override
    public List<IndentEntity> findIner(String supplierName,String indentId ,String indentStatus, String startDate,String endDate, int pageNumber, int pageSize) {
        return findinderDao.findIner(  supplierName, indentId , indentStatus,  startDate, endDate,  pageNumber,  pageSize);
    }

    @Override
    public long CountIner(String supplierName,String indentId ,String indentStatus, String startDate,String endDate) {
        return findinderDao.CountIner(  supplierName, indentId , indentStatus,  startDate, endDate);
    }
}
