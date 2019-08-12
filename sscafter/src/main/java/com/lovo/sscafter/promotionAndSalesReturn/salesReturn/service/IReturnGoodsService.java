package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service;


import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * 商品service接口
 */
public interface IReturnGoodsService {

    public void sava(ReturnGoodsEntity2 goodsEntity);

    /**
     * 根据退货单编号查询全部退货商品信息
     * @param returnOrderId 退货订单编号
     * @return  商品集合
     */
    public List<ReturnGoodsEntity2> findByReturnOderId(String returnOrderId);

    /**
     * 根据商品
     * @param goodsId
     * @param returnOredrState
     */
    public void updat(String goodsId, String returnOredrState);

    /**
     * 根据退货订单号修改商品退货状态
     * @param orderId 退货订单编号
     * @param goodsState 商品退货状态
     */
    public void updatGoodsState(String orderId, int goodsState);
}
