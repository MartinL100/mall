package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.SupplierEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISupplierDao extends CrudRepository<SupplierEntity,String> {
    /**
     * 保存修改
     * @param supplier 供应商ID
     */
    public SupplierEntity save(SupplierEntity supplier);
    /**
     *根据供应商ID查询供应商信息
     * @param supplierId
     * @return
     */
    public SupplierEntity findBySupplierId(String supplierId);
}
