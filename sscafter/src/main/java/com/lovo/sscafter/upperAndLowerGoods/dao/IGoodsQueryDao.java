package com.lovo.sscafter.upperAndLowerGoods.dao;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;

import java.util.List;

public interface IGoodsQueryDao {
     // 动态查询商品信息
    public List<GoodsEntity>  getGoodsList(int page,int rows, String goodsState, String goodsType,
                                           String goodsBooking,String goodsName);
    // 动态查询商品总行数
    public long  getGoodsCount( String goodsState, String goodsType,
                                           String goodsBooking,String goodsName);


}
