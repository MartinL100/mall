package com.lovo.sscafter.orderManagement.service;

import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;

import java.util.List;

public interface IOrderManagementService {
    /**
     * 删除订单(修改订单删除状态)
     * @param orderNum 订单号
     * @return 是否修改成功
     */
    public boolean updateOrderDelType(String orderNum);
    /**
     * 根据条件动态查询订单信息
     * @param orderDate 下单时间
     * @param orderType 订单类型
     * @param currentPage 当前页面
     * @param rows 每页显示的行数
     * @param userName 用户名
     * @return
     */
    public List<OrderManagementEntity> findTrendsOrderInfo
            (String orderDate, int orderType, int currentPage,
             int rows, String userName);

    /**
     * 根据信息动态查询
     * @param orderDate 下单时间
     * @param orderType 订单类型
     * @param userName 用户名
     * @return
     */
    public int findOrderRows(String orderDate,int orderType,String userName);
}
