package com.lovo.common.entity;

import java.util.List;

/**
 * 商品订单dto
 */
public class OrderDTO {
    /**订单号*/
    private String orderNum;
    /**订单时间*/
    private String orderDate;
    /**下单用户名*/
    private String userName;
    /**订单金额*/
    private String orderMoney;
    /**实付金额*/
    private String payMoney;
    /**支付方式*/
    private String payMethod;
    /**商品集合*/
    private List<GoodsDTO> goodsDTOList;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public List<GoodsDTO> getGoodsDTOList() {
        return goodsDTOList;
    }

    public void setGoodsDTOList(List<GoodsDTO> goodsDTOList) {
        this.goodsDTOList = goodsDTOList;
    }
}
