package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.SupplierGoodsEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISupplierGoodsService {
    /**
     * 添加修改供应商供应商品
     * @param supplierGoods 供应商供应商品
     */
    public void save(SupplierGoodsEntity supplierGoods);
    /**
     * 根据供应商供应商品ID删除
     * @param codeId 供应商供应商品ID
     * @return
     */
    public void deleteByCodeId(String codeId);

    /**
     * 根据供应商ID查询该供应商供应的商品
     * @param supplierId
     * @return
     */
    public List<SupplierGoodsEntity> findBySuppliegrId(String supplierId);
}
