package com.lovo.sscafter.goodsStock.service;

import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import com.lovo.sscafter.goodsStock.dto.ReturnGoodsDto;

import java.util.List;

public interface IReturnGoodsService {

    public List<ReturnGoodsDto> findOrderGoods(String goodsName,
                                               String goodsType, String startDate, String endDate, int currentPage, int rows);
}
