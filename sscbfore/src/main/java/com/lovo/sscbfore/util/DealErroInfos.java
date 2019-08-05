package com.lovo.sscbfore.util;

import com.lovo.common.entity.OrderDTO;

public class DealErroInfos {
    public static final String NOT_ENOUGH = "库存不足，请重新换购商品";

    /**
     * 生成商品订单号
     * @param orderDTO 订单对象
     * @return  订单号
     */
    public static final String getOrderNum(OrderDTO orderDTO){
        String orerNum=System.currentTimeMillis()+orderDTO.getPayMoney()+orderDTO.getUserName();
        return  orerNum;

    }
}
