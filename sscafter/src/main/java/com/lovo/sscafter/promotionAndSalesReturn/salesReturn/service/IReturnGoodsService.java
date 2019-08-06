package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;


import java.util.List;

/**
 * 商品service接口
 */
public interface IReturnGoodsService {

    public void sava(ReturnGoodsEntity goodsEntity);

    /**
     * 根据退货单编号查询全部退货商品信息
     * @param returnOrderId 退货订单编号
     * @return  商品集合
     */
    public List<ReturnGoodsEntity> findByReturnOderId(String returnOrderId);
}
