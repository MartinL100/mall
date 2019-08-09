package com.lovo.psc.wangwei.service;

import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.wangwei.dto.InderDTO;

import java.util.List;

public interface IIndenService {
    public long CountInerList(String goodsName,String indentId ,String zbTag,String ghTag,String supplyId,String startDate,String endDate);


    public List<InderDTO> findInerList(String goodsName, String indentId , String zbTag, String ghTag, String supplyId, String startDate, String endDate, int pageNumber, int pageSize);

    public List<IndentEntity>  findIner(String supplierName,String indentId ,String indentStatus,String startDate,String endDate,int pageNumber, int pageSize);

    public long CountIner(String supplierName,String indentId ,String indentStatus,String startDate,String endDate);
}
