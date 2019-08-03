package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.SupplierGoodsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ISupplierGoodsDao extends CrudRepository<SupplierGoodsEntity,String> {
    /**
     * 添加修改供应商供应商品
     * @param supplierGoods 供应商供应商品
     */
    public SupplierGoodsEntity save(SupplierGoodsEntity supplierGoods);
    /**
     * 根据供应商供应商品ID删除
     * @param codeId 供应商供应商品ID
     * @return
     */
    public void deleteByCodeId(String codeId);
}
