package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.SupplierGoodsEntity;
import org.springframework.data.jpa.repository.Query;

public interface ISupplierGoodsService {
    /**
     * 添加修改供应商供应商品
     * @param supplierGoods 供应商供应商品
     */
    public void save(SupplierGoodsEntity supplierGoods);
    /**
     * 根据供应商供应商品ID删除
     * @param supplierGoodsId 供应商供应商品ID
     * @return
     */
    public void deleteBySupplierGoodsId(String supplierGoodsId);
}
