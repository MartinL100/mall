package com.lovo.csc.service.closeService.impl;

import com.lovo.csc.dao.closeDao.IIndentCloseDao;
import com.lovo.csc.dao.closeDao.IIndentCloseDao1;
import com.lovo.csc.dao.closeDao.IIndentSupplyDao;
import com.lovo.csc.entity.supplierEntity.IndentEntity;
import com.lovo.csc.service.closeService.IIndentCloseService;
import com.lovo.csc.vo.IndentSupplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository(value = "indentCloseService")
public class IndentCloseServiceImpl implements IIndentCloseService {

    @Autowired
    private IIndentCloseDao indentCloseDao;
    @Autowired
    private IIndentCloseDao1 indentCloseDao1;
    @Autowired
    private IIndentSupplyDao indentSupplyDao;

    @Override
    public void save(IndentEntity indentEntity) {
        indentCloseDao.save(indentEntity);
    }

    @Override
    public List<IndentEntity> findIndentList(int pageNumber, int pageSize, String startDate, String endDate, String indentStatus) {
        List<IndentEntity>list=indentCloseDao1.findIndentList(pageNumber,pageSize,startDate,endDate,indentStatus);
        return list;
    }

    @Override
    public long countIndentList(  String startDate, String endDate,String indentStatus){
        return indentCloseDao1.countIndentList(  startDate,  endDate,indentStatus);
    }

    @Override
    public List<IndentSupplyDto>findSupplyListByIndentId(int pageNumber, int pageSize,String indentId){
        return indentSupplyDao.findIndentSupplyListByIndentId(pageNumber,pageSize,indentId);
    }

    public long countIndentSupplyListByIndentId(String indentId){
        return indentSupplyDao.countIndentSupplyListByIndentId(indentId);
    }




    public IndentEntity findByIndentId(String indentId){
        return indentCloseDao.findByIndentId(indentId);
    }


}
