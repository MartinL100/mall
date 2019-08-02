package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;

import java.util.List;

public interface IOrderTrendsDao {
    /**
     * 根据条件动态查询订单信息
     * @param orderDate 下单时间
     * @param orderType 订单类型
     * @param currentPage 当前页面
     * @param rows 每页显示的行数
     * @param userName 用户名
     * @return
     */
    public List<OrderManagementEntity> findTrendsOrderInfo(String orderDate,int orderType,
                                                           int currentPage,int rows,String userName);
}
