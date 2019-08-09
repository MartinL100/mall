package com.lovo.psc.wangwei.dao;

import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplierGoodsEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ISupplierGoodsDao extends CrudRepository<SupplierGoodsEntity,String> {

    @Query(" from  SupplierGoodsEntity  where codeId=?1 ")
public SupplierGoodsEntity findByID(String codeId);



    @Query(" from  SupplierEntity  where supplierId=?1 ")
    public SupplierEntity findInByID(String supplierId);




    @Query(" update    SupplierGoodsEntity set goodsName=?1,goodsNoms=?2, goodsTy=?3,goodsUnit=?4 ,goodsNum=?5 where codeId=?6 ")
    @Modifying
    @Transactional
    public void updateByID(String goodsName, String goodsNoms, String goodsType, String goodsUnit, int goodsNum, String codeId);

}
