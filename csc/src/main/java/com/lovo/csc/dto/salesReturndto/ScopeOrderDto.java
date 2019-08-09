package com.lovo.csc.dto.salesReturndto;

import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;


public class ScopeOrderDto {

    //退货订单号
    private ScopeOrderEntity scopeOrder;
    //退货单id
    private String orderId;
    //结算时间
    private String closeTime;
    //供货商名称
    private String supplierName;
    //商品id
    private String Id;
    //商品编号
    private  String goodsId;
    //商品名
    private String goodsName;
    //进货数量
    private  String goodsNum;
    //进货价格
    private  String goodsBid;
    //退货原因
    private String goodsSales;
    //订单状态
    private String orderState;
    //审核意见
    private String orderOpinion;
    //审核人
    private String orderAudit;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOrderOpinion() {
        return orderOpinion;
    }

    public void setOrderOpinion(String orderOpinion) {
        this.orderOpinion = orderOpinion;
    }

    public String getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(String orderAudit) {
        this.orderAudit = orderAudit;
    }

    public ScopeOrderEntity getScopeOrder() {
        return scopeOrder;
    }

    public void setScopeOrder(ScopeOrderEntity scopeOrder) {
        this.scopeOrder = scopeOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

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

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(String goodsBid) {
        this.goodsBid = goodsBid;
    }

    public String getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(String goodsSales) {
        this.goodsSales = goodsSales;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
