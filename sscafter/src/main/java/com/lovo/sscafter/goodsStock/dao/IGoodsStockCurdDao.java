package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IGoodsStockCurdDao extends CrudRepository<GoodsStockEntity,String> {
     @Query("update GoodsStockEntity g set g.goodsNum=g.goodsNum+?1 where g.goodsId=?2")
     @Modifying
     @Transactional
    public void updateGoodsNum(long goodsNum,String goodsId);


@Query("select g.goodsNum from GoodsStockEntity g where g.goodsId=?1")
     public long findGoodsNumId(String Id);

    @Query("update GoodsStockEntity g set g.goodsMinNum=?1 where g.goodsId=?2")
    @Modifying
    @Transactional
    public void updateGoodsMinNum(long goodsMinNum,String goodsId);

    @Query("update  GoodsStockEntity g set g.tag=?1 where g.goodsId=?2")
    @Modifying
    @Transactional
    public void upDateGoodsTag(String tag,String goodsId);

       @Query("from GoodsStockEntity where goodsName=?1 and goodsType=?2 and goodsNorms=?3")
       public GoodsStockEntity findByNameTypeAnAndNorms(String name,String type,String norms);

       public GoodsStockEntity findGoodsStockEntityByGoodsId(String goodsId);
}
