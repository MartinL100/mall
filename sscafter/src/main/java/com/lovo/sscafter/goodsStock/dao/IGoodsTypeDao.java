package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;

import java.util.List;

public interface IGoodsTypeDao {

    public List<GoodsTypeEntity> findGoodsTypeByNmae(String typeName, int page, int rows);

    public long findGoodsTypeByNmaeCpunt(String typeName);
}
