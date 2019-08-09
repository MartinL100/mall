package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;

import java.util.List;
import java.util.Map;

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
    public List<OrderManagementEntity> findTrendsOrderInfo(String orderDate, int orderType,
                                                           int currentPage, int rows, String userName);

    /**
     * 根据信息动态查询
     * @param orderDate 下单时间
     * @param orderType 订单类型
     * @param userName 用户名
     * @return
     */
    public int findOrderRows(String orderDate, int orderType, String userName);

    /**
     * 根据日期查询需要的日期
     * @param mouth
     * @return
     */

    public Map<String,String>  findDate(String mouth);
}
