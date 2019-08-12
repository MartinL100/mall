package com.lovo.psc.liuhuali.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.psc.entity.SupplierEntity;

public interface ISupplierService {
    /**
     * 保存
     * @param supplier
     */
    public  void savaSupplier(SupplierEntity supplier) throws JsonProcessingException;


    /**
     * 修改用户，发送会MQ
     * @param supplier
     */
    public void updateSupplier(SupplierEntity supplier);

    /***
     * 按名查询供应商是否存在
     * @param supplierName
     * @return
     */
    public SupplierEntity getSupplierEntityByName(String supplierName);

    public SupplierEntity findBySupplierId(String supplierId);

}
