package com.lovo.common.entity;

import java.util.List;
//后端订单实体类
public class OrderManagementDTO {
    //订单编号
    private String orderNum;
    //订单类型0就是正常情况,1就是下单未付款,2为有退货
    private String orderType;
    //下单时间
    private String orderDate;
    //下单用户
    private String userName;
    //订单金额
    private float orderMoney;
    //实付金额
    private float payMoney;
    //支付方式
    private String payMethod;
    //下单地址外键
    private String orderAddress;
    //利润
    private float goodsProfit;
    //删除状态
    private int orderDelType;
    //商品集合
    private List<OrderForGoodsDTO> goodsNum;
    //商品数量
    private int goodsSize;

    public int getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(int goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
        this.orderMoney = orderMoney;
    }

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public float getGoodsProfit() {
        return goodsProfit;
    }

    public void setGoodsProfit(float goodsProfit) {
        this.goodsProfit = goodsProfit;
    }


    public int getOrderDelType() {
        return orderDelType;
    }

    public void setOrderDelType(int orderDelType) {
        this.orderDelType = orderDelType;
    }
}
