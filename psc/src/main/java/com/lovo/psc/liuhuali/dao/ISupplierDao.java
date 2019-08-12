package com.lovo.psc.liuhuali.dao;

import com.lovo.psc.entity.SupplierEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ISupplierDao extends CrudRepository<SupplierEntity,String> {

    /***
     * 按供应商名字查询供应商是否存在
     * @param supplierName
     * @return
     */
    @Query("from SupplierEntity where supplierName=?1")
    public SupplierEntity getSupplierEntityByName(String supplierName);

}
