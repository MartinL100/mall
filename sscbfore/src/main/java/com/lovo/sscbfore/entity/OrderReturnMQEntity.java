package com.lovo.sscbfore.entity;

/**
 * @author che
 * @title: OrderReturnMQEntity
 * @projectName mall
 * @description: 订单退货MQ
 * @date 2019/8/6  14:39
 */
public class OrderReturnMQEntity {

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 商品状态
     */
    private String goodsStatus;

    public OrderReturnMQEntity() {
    }

    public OrderReturnMQEntity(String orderNum, String goodsId, String goodsStatus) {
        this.orderNum = orderNum;
        this.goodsId = goodsId;
        this.goodsStatus = goodsStatus;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}