package com.lovo.psc.groupOne.dao;

import com.lovo.psc.entity.SupplyCenterEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISupplyCenterDao extends CrudRepository<SupplyCenterEntity,String> {

    /**
     * 按商品采购订单编号查询
     * @param supplyId
     * @return
     */
    public SupplyCenterEntity findBySupplyId(String supplyId);

    /**
     * 按供应商编号查询未报价的采购信息
     * @param supplierId
     * @param bjTag
     * @return
     */
    @Query("from SupplyCenterEntity as sc join  sc.supplier as sl where sl.supplierId=?1 and sc.bjTag=?2")
    public List<SupplyCenterEntity> findBySupplierIdAndBjTag(String supplierId, String bjTag);

    /**
     * 按商品才后订单编号修改商品报价,并将报价状态改为已报价
     * @param price 报价
     * @param supplyId 商品采购订单编号
     */
    @Query("update SupplyCenterEntity set supplyPrice=?1,bjTag='已报价' where supplyId=?2")
    @Modifying
    public void updatePrice(Float price, String supplyId);


























}
