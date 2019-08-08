package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IGoodsTypeCrudDao extends CrudRepository<GoodsTypeEntity,String> {

    @Query("update GoodsTypeEntity set typeName=?1 where typeId=?2")
    @Modifying
    @Transactional
    public void updateGoodstype(String typeName,String typeId);


    @Query("from GoodsTypeEntity where typeKey=?1")
    public GoodsTypeEntity findTypeByKey(String typeKey);

}
