package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IOrderGoodsCrudDao  extends CrudRepository<OrderGoodsEntity,String > {

    @Query("update OrderGoodsEntity og set og.isReturnGoods=?1 where og.id=?2")
   @Modifying
    @Transactional
    public void updateIsReturnGoods(String status,String orderGoodsId);

}
