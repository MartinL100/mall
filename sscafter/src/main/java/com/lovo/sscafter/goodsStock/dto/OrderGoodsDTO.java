package com.lovo.sscafter.goodsStock.dto;




public class OrderGoodsDTO {
    private String orderId;
    private String goodsOrderDate;
    private String supplierId;
    private String goodsName;
    private String goodsType;
    private String goodsNorms;
    private String goodsUnit;
    private Long goodsNum;
    private float goodsBid;
    private String isReturnGoods;

    public OrderGoodsDTO(String orderId, String goodsOrderDate, String supplierId, String goodsName, String goodsType, String goodsNorms, String goodsUnit, Long goodsNum, float goodsBid, String isReturnGoods) {
        this.orderId = orderId;
        this.goodsOrderDate = goodsOrderDate;
        this.supplierId = supplierId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsNorms = goodsNorms;
        this.goodsUnit = goodsUnit;
        this.goodsNum = goodsNum;
        this.goodsBid = goodsBid;
        this.isReturnGoods = isReturnGoods;
    }

    public OrderGoodsDTO() {
        super();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsOrderDate() {
        return goodsOrderDate;
    }

    public void setGoodsOrderDate(String goodsOrderDate) {
        this.goodsOrderDate = goodsOrderDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public float getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(float goodsBid) {
        this.goodsBid = goodsBid;
    }

    public String getIsReturnGoods() {
        return isReturnGoods;
    }

    public void setIsReturnGoods(String isReturnGoods) {
        this.isReturnGoods = isReturnGoods;
    }
}
