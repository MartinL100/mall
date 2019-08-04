package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;

import java.util.List;

/**
 * 退货订单service接口
 */
public interface IReturnOrderService {

    /**
     * 添加退货订单对象
     * @param orderEntity 退货订单对象
     */
    public void savaReturnOrder(ReturnOrderEntity orderEntity);

}