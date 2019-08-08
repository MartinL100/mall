package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;

import java.util.List;

public interface ISupplyDao {

    public List<LookBuyInfoDTO> findOrderGoods(String goodsName,
  String goodsType, String startDate, String endDate, int currentPage, int rows);


    public long findOrderGoodsCount(String goodsName,
  String goodsType, String startDate, String endDate);
}
