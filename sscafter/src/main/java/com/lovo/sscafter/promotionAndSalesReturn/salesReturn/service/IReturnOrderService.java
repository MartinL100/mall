package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;

import java.util.List;

/**
 * 退货订单service接口
 */
public interface IReturnOrderService {

    /**
     * 添加退货订单号
     * @param orderEntity 退货订单对象
     */
    public void savaReturnOrder(ReturnOrderEntity orderEntity);

    /**
     * 根据退货单编号查询全部退货商品信息
     * @param returnOrderId 退货订单编号
     * @return  商品集合
     */
    public List<ReturnGoodsEntity> findGoodsByReturnOderNum(String returnOrderId);
}
