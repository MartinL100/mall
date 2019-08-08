package com.lovo.sscafter.orderManagement.entity.DTO;

public class ReturnGoodsDTO {
    //订单号
    private String orderNum;
    //商品id
    private String goodsId;
    //商品状态
    private int goodsStatus;

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

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}
