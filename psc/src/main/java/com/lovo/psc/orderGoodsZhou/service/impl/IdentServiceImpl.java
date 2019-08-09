package com.lovo.psc.orderGoodsZhou.service.impl;

import com.lovo.psc.orderGoodsZhou.dao.IIdentDao;
import com.lovo.psc.orderGoodsZhou.dao.IIdentDao2;
import com.lovo.psc.orderGoodsZhou.dto.SendGoodsDto;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.entity.SupplyCenterEntity;
import com.lovo.psc.orderGoodsZhou.service.IIdentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
@Service(value = "identService")
public class IdentServiceImpl implements IIdentService {
    @Autowired
    private IIdentDao identDao;
    @Autowired
    private IIdentDao2 identDao2;


    @Override
    public void updateByindentId2(String indentId, String indentStatus, String indentDate) {
        identDao.updateByindentId2(indentId, indentStatus, indentDate);
    }

    @Override
    public void updateByIndentId(String indentId, String indentStatus) {
        identDao.updateByIndentId(indentId, indentStatus);
    }

    @Override
    public List<IndentEntity> getNoPayList(String supplierId, String indentStatus, int pageNumber, int pageSize) {
        Pageable pageable= new PageRequest(pageNumber-1,pageSize);
        return identDao.getNoPayList(supplierId,indentStatus,pageable);
    }

    @Override
    public List<SupplyCenterEntity> getGoodsList(String indentId,int pageNumber, int pageSize) {
        Pageable pageable= new PageRequest(pageNumber-1,pageSize);
        return identDao.getGoodsList(indentId,pageable);
    }

    @Override
    public List<SupplyCenterEntity> getGoodsList2(String indentId) {
        return identDao.getGoodsList2(indentId);
    }

    @Override
    public List<IndentEntity> getYesPayList(String supplierId, String indentStatus, String startDate, String endDate, int pageNumber, int pageSize) {
        Pageable pageable= (Pageable) new PageRequest(pageNumber-1,pageSize);
        return identDao2.getYesPayList(supplierId,indentStatus,startDate,endDate,pageable);
    }

    @Override
    public List<SendGoodsDto> sendGoodsMQ(String indentId) {
        return identDao2.sendGoodsMQ(indentId);
    }

    @Override
    public void updateByCodeId(String codeId, int supplyNum) {
        identDao.updateByCodeId(codeId,supplyNum);
    }
}
