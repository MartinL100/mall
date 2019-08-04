package com.lovo.sscafter.orderManagement.service;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;

import java.util.List;

public interface IGoodsManagementService {
    public List<OrderForGoodsEntity> findGoods(String orderNum, int currentPage, int rows);
}
