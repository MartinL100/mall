package com.lovo.sscafter.goodsStock.dto;




public class OrderGoodsDTO {
private String orderGoodsId;
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
    private String supplierName;
    private String goodsId;



    public String getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(String orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public OrderGoodsDTO(String orderGoodsId ,String goodsId,String orderId, String goodsOrderDate, String supplierId, String goodsName, String goodsType, String goodsNorms, String goodsUnit, Long goodsNum, float goodsBid, String isReturnGoods) {
       this.orderGoodsId=orderGoodsId;
        this.goodsId=goodsId;
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
