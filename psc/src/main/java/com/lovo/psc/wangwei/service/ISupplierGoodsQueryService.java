package com.lovo.psc.wangwei.service;

import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplierGoodsEntity;

import java.util.List;

public interface ISupplierGoodsQueryService {

    public SupplierGoodsEntity  finByiD(String codeId);


    public SupplierEntity fininId(String ID);


    public void  update(String goodsName, String goodsNoms, String goodsType, String goodsUnit, int goodsNum,String codeId);

    /**
     * 添加商品
     * @param supplierGoodsEntity
     */
    public  void savaGoods(SupplierGoodsEntity supplierGoodsEntity);

    /**
     * 根据商品名称和规格删除商品
     * @param codeId
     * @param
     */
    public  void delgoods(String codeId);

    /**
     * 分页
     * @param pageNumber 当前页
     * @param pageSize 每页显示的行数
     * @param
     * @return
     */
    public List<SupplierGoodsEntity> getPageListGoods(String goodsName, String supplierName,String goodsType, String codeId,int pageNumber, int pageSize);

    /**
     * 查询总行数
     * @param
     * @return
     */
    public long getPageListGoodsCount(String goodsName, String supplyName, String zbTag,String indentId);
}
