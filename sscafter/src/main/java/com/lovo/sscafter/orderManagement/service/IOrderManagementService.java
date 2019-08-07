package com.lovo.sscafter.orderManagement.service;

import com.lovo.common.entity.OrderDTO;
import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;

import java.util.List;
import java.util.Map;

public interface IOrderManagementService {
    /**
     * 删除订单(修改订单删除状态)
     * @param orderNum 订单号
     * @return 是否修改成功
     */
    public boolean updateOrderDelType(String orderNum);
    /**
     * 根据条件动态查询订单信息，升序排序
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
     * 根据信息动态查询总行数，升序排序
     * @param orderDate 下单时间
     * @param orderType 订单类型
     * @param userName 用户名
     * @return
     */
    public int findOrderRows(String orderDate,int orderType,String userName);

    /**
     * 接受订单对象,保存到数据库,返回商品集合
     * @param orderDTO 订单对象
     */
    public List<OrderForGoodsEntity> receiveOrder(OrderDTO orderDTO);

    /**
     * 保存订单集合
     * @param goodslist 订单集合
     */
    public void receiveOrder2(List<OrderForGoodsEntity> goodslist);

    /**
     * 根据订单号修改订单类型为0
     * @param orderId
     */
    public void updateOrderType(String orderId);

    /**
     * 根据月查询需要的日期格式
     * @param mouth
     * @return
     */
    public List<Map> findDate(String mouth);
}
