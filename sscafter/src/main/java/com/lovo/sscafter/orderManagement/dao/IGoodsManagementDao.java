package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface IGoodsManagementDao extends CrudRepository<OrderForGoodsEntity,String> {
    /**
     * 根据订单号,页面对象查询商品集合
     * @param orderNum 订单号
     * @param page 页面对象
     * @return
     */
    @Query("from OrderForGoodsEntity g left join g.orderObj o where o.orderNum = ?1")
    public List<OrderForGoodsEntity> findGoods(String orderNum, Pageable page);

    /**
     * 根据订单号,查询商品总行数
     * @param orderNum 订单号
     * @return
     */
    @Query("select count(o.orderNum) from OrderForGoodsEntity g left join g.orderObj o where o.orderNum = ?1")
    public int findGoodsRows(String orderNum);

    /**
     * 根据商品id修改商品状态
     * @param goodsId
     * @param goodsStatus
     */
    @Query("update OrderForGoodsEntity set goodsStatus = ?3 where goodsId=?2 and orderObj.orderNum = ?1")
    @Modifying
    public void updateOrderReturn(String orderNum,String goodsId,int goodsStatus);

    /**
     * 根据商品id查询商品利润
     * @param goodsId
     * @return
     */
    @Query("select orderProfit from OrderForGoodsEntity where goodsId =?1")
    public float findOrderProfit(String goodsId);
}
