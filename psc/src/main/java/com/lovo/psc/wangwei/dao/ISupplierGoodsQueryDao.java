package com.lovo.psc.wangwei.dao;

import com.lovo.psc.entity.SupplierGoodsEntity;

import java.util.List;

public interface ISupplierGoodsQueryDao {
    public List<SupplierGoodsEntity> pageList(String goodsName, String supplierId, String goodsType,String codeId, int pageNumber, int pageSize);


   public long countPage(String goodsName, String supplierId, String goodsType,String codeId);
}
