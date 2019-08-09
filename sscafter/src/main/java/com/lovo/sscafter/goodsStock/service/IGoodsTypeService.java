package com.lovo.sscafter.goodsStock.service;

import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;

import java.util.List;

public interface IGoodsTypeService {
    public List<GoodsTypeEntity> findAll();
    public List<GoodsTypeEntity> findGoodsTypeByNmae(String typeName, int page, int rows);

    public long findGoodsTypeByNmaeCpunt(String typeName);

    public void saveGoodsType(GoodsTypeEntity goodsTypeEntity);

    public void delGoodstype(String id);
    public void updateGoodstype(String typeName, String typeId);

    public GoodsTypeEntity findTypeByKey(String typeKey);

}
