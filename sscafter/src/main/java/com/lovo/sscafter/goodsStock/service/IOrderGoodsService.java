package com.lovo.sscafter.goodsStock.service;

import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;

import java.util.List;

public interface IOrderGoodsService {
    public List<OrderGoodsDTO> findOrderGoods(String goodsName,
                                              String goodsType, String startDate, String endDate, int currentPage, int rows);
}
