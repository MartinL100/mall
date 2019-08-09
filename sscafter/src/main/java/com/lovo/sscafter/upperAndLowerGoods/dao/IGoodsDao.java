package com.lovo.sscafter.upperAndLowerGoods.dao;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface IGoodsDao extends CrudRepository<GoodsEntity,String> {

    /**
     * 上架
     */
    @Query("update GoodsEntity g set g.goodsState=?2 , g.shelfTime=?3 where g.goodsId=?1")
    @Modifying
    @Transactional
    public void updateStateUpper(String goodsId, String goodsUpper, String shelfTime);

    /**
     * 下架
     * @param goodsId
     */
    @Query("update GoodsEntity g set g.goodsState=?2 , g.lowerTime=?3 where g.goodsId=?1")
    @Modifying
    @Transactional
    public void updateStateLower(String goodsId, String goodsLower, String lowerTime);

    /**
     * 预售
     * @param goodsId
     */
    @Query("update GoodsEntity g set g.goodsBooking=?2 where g.goodsId=?1")
    @Modifying
    @Transactional
    public void updateStateBooking(String goodsId, String Booking);

    /**
     * 未预售
     * @param goodsId
     */
    @Query("update GoodsEntity g set g.goodsBooking=?2 where g.goodsId=?1")
    @Modifying
    @Transactional
    public void updateStateBooking1(String goodsId, String Booking1);


    /**
     * 修改下架时间
     * @param goodsId
     * @param lowerTime
     */
    @Query("update GoodsEntity g set g.lowerTime=?2 where g.goodsId=?1")
    @Modifying
    @Transactional
    public void updateGoodsLowerTime(String goodsId, String lowerTime);

}
