package com.lovo.csc.service.salesReturnService;

import com.lovo.csc.entity.salesReturnEntity.GoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;

import java.util.List;

public interface IOrderGoodsService {
    /**
     * 保存
     * @param orderGoodsEntity
     */
    public OrderGoodsEntity savaOrderGoods(OrderGoodsEntity orderGoodsEntity);

    /**
     * 修改状态
     * @param orderGoodsEntity
     * @param goodsState
     * @return
     */
    public String updateOrderGoods(OrderGoodsEntity orderGoodsEntity,String goodsState);
    /**
     * 根据订单状态查询退货订单信息
     * @param goodsState 订单状态
     * @param orderId 订单号
     * @param orderName 商品名
     * @return
     */
    public List<OrderGoodsEntity> findByOrderId(String goodsState, String orderId, String orderName, int pageNumber, int pageSize);

    /**
     * 查询总行数
     * @param goodsState
     * @param orderId
     * @param orderName
     * @return
     */
    public long getPageListOrderGoodsEntityCount(String goodsState, String orderId, String orderName);


//    /**
//     * 查询
//     * @param orderId
//     * @return
//     */
//    public List<GoodsEntity> findByOrderGoods(String orderId);
    /**
     * 根据商品id查询退货订单信息
     * @param goodsId 商品id
     * @return
     */
    public List<GoodsEntity> findByGoods(String goodsId, int pageNumber, int pageSize);

    /**
     * 查询总行数
     * @return
     */
    public long getPageListGoodsCount(String goodsId);
}
