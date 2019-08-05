package com.lovo.sscafter.goodsStock.service;

import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;

import java.util.List;

public interface IGoodsStockService {
//分页动态查询所有商品
    public List<GoodsStockEntity> findAllGoodsStock(String goodsName, String goodsType, int currentPage, int rows);
    public long findAllGoodsStockCount(String goodsName,String goodsType);
    public boolean updateGoodsNum(String tag,long goodsNum,String goodsId);
    public long findGoodsNumId(String Id);

    public void saveGoods(GoodsStockEntity goods);

}
