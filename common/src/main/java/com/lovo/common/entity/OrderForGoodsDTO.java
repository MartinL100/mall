package com.lovo.common.entity;



public class OrderForGoodsDTO {
    //商品id
    private String goodsId;
    //商品名
    private String goodsName;
    //商品规格
    private String goodsNorms;
    //商品类型
    private String goodsType;
    //商品数量
    private int goodsNum;
    //商品售价
    private float goodsPrice;
    //商品单位
    private String goodsUnit;
    //商品状态  0就是正常,1就是退货中,2就是已退货
    private int goodsStatus;
    //订单对象
    private OrderManagementDTO orderObj;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public OrderManagementDTO getOrderObj() {
        return orderObj;
    }

    public void setOrderObj(OrderManagementDTO orderObj) {
        this.orderObj = orderObj;
    }
}
