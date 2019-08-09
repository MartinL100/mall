package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderGoodsDao{

    public List<OrderGoodsDTO> findOrderGoods(String goodsName,
                                              String goodsType, String startDate, String endDate, int currentPage, int rows);

    public long findOrderGoodsCount(String goodsName, String goodsType, String startDate, String endDate);

    //根据商品id查找进货价格
    public float findGoodsBidByGoodsId(String goodsId);



}
