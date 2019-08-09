package com.lovo.sscafter.goodsStock.service;

import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;

import java.util.List;

public interface IOrderGoodsService {
    public List<OrderGoodsDTO> findOrderGoods(String goodsName,
                                              String goodsType, String startDate, String endDate, int currentPage, int rows);
    public long findOrderGoodsCount(String goodsName, String goodsType, String startDate, String endDate);

    public void updateIsReturnGoods(String status, String orderGoodsId);

   public void saveOrderGoods(OrderGoodsEntity orderGoodsEntity);

    //根据商品id查找进货价格
    public float findGoodsBidByGoodsId(String goodsId);
}
