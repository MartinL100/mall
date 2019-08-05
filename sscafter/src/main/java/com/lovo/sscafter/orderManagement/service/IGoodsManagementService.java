package com.lovo.sscafter.orderManagement.service;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;

import java.util.List;

public interface IGoodsManagementService {
    /**
     * 根据订单号查询商品集合
     * @param orderNum 订单号
     * @param currentPage 当前页
     * @param rows 每页要显示的行数
     * @return 商品集合
     */
    public List<OrderForGoodsEntity> findGoods(String orderNum, int currentPage, int rows);

    /**
     * 根据订单号查询商品总行数
     * @param orderNum 订单号
     * @return 商品总行数
     */
    public int findGoodsRows(String orderNum);
}
