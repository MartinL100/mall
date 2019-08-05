package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import org.springframework.data.domain.Pageable;
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
}
