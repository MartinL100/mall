package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.SupplierEntity;

import java.util.List;

public interface ISupplierService {
    /**
     * 保存修改
     * @param supplier 供应商ID
     */
    public void save(SupplierEntity supplier);
    /**
     *根据供应商ID查询供应商信息
     * @param supplierId
     * @return
     */
    public SupplierEntity findBySupplierId(String supplierId);
    /**
     * 根据审核未审核分页查询供应商信息
     * @param pageNumber 当前页
     * @param pageSize 每页显示的行数
     * @param supplierTag  供应商状态（未审核/已审核）
     * @return
     */
    public List<SupplierEntity> findAll(int pageNumber, int pageSize, String supplierTag);
    /**
     * 查询总行数
     * @param supplierTag  供应商状态（未审核/已审核）
     * @return
     */
    public long countAll(String supplierTag);
}
