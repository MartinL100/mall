package com.lovo.sscafter.goodsStock.service;

import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import com.lovo.sscafter.goodsStock.entity.SupplyEntity;

import java.util.List;

public interface ISupplyService {
    public List<LookBuyInfoDTO> findOrderGoods(String goodsName,
                                               String goodsType, String startDate, String endDate, int currentPage, int rows);

    public void saveSupply(SupplyEntity supplyEntity);

    public long findOrderGoodsCount(String goodsName,
                                    String goodsType, String startDate, String endDate);

}
