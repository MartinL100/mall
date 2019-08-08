package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IOrderManagementDao extends CrudRepository<OrderManagementEntity,String> {
    /**
     * 删除订单
     * @param orderNum
     * @return
     */
    @Query ("update OrderManagementEntity set orderDelType = 1 " +
            "where orderNum = ?1")
    @Modifying
    public int updateOrderDelType(String orderNum);

    /**
     * 修改订单状态
     * @param orderNum
     * @param orderType
     */
    @Query("update OrderManagementEntity set orderType = ?2 " +
            "where orderNum = ?1")
    @Modifying
    public void updateOrderType(String orderNum,String orderType);

    /**
     * 根据订单号修改订单利润
     * @param orderNum
     * @param goodsProfit
     */
    @Query("update OrderManagementEntity  set goodsProfit = goodsProfit - ?2 where orderNum=?1")
    @Modifying
    public void updateOrderProfit(String orderNum,float goodsProfit);





}
