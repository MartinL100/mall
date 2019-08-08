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

    public void updateGoodsMinNum(long goodsMinNum,String goodsId);

    public void upDateGoodsTag(String tag,String goodsId);

    public String findSupplyIdByGoodsId(String goodsId);

    public GoodsStockEntity findByNameTypeAnAndNorms(String name,String type,String norms);

    public GoodsStockEntity findGoodsStockEntityByGoodsId(String goodsId);
}
