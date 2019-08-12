package com.lovo.psc.groupOne.service;

import com.lovo.psc.entity.SupplyCenterEntity;

import java.util.List;

public interface ISupplyCenterService {
    /**
     * 保存商品采购信息
     * @param supplyCenter 商品采购对象
     */
    public void saveSupplyCenter(SupplyCenterEntity supplyCenter);

    /**
     * 根据供应商编号和报价状态查询商品采购信息
     * @param supplierId 供应商编号
     * @param bjTag 报价状态
     * @return 商品采购信息对象集合
     */
    public List<SupplyCenterEntity> findBySupplierIdAnAndBjTag(String supplierId, String bjTag);

    /**
     * 按商品采购编号修改报价
     */
    public void updatePrice(SupplyCenterEntity supplyCenter);

    public SupplyCenterEntity findBySupplyId(String supplyId);
}
