package com.lovo.sscafter.orderManagement.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_order_after")
public class OrderManagementEntity {
    //订单编号
    @Id
    @Column(name="order_num",length = 48)
    private String orderNum;
    //订单类型0就是正常情况,1就是下单未付款,2为有退货
    @Column(name="order_type",length = 48)
    private String orderType;
    //下单时间
    @Column(name="order_date",length = 48)
    private String orderDate;
    //下单用户
    @Column(name="user_name",length = 48)
    private String userName;
    //订单金额
    @Column(name="order_money",columnDefinition = "double")
    private float orderMoney;
    //实付金额
    @Column(name="pay_money",columnDefinition = "double")
    private float payMoney;
    //支付方式
    @Column(name="pay_method",length = 48)
    private String payMethod;
    //下单地址外键
    @Column(name="order_address")
    private int orderAddress;
    //利润
    @Column(name="goods_profit",columnDefinition = "double")
    private float goodsProfit;
    //删除状态
    @Column(name="order_delType")
    private int orderDelType;
    //商品集合
    @OneToMany(mappedBy = "orderObj")
    private List<OrderForGoodsEntity> goodsNum;
    //商品数量
    @Column(name="goods_size")
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

    public int getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(int orderAddress) {
        this.orderAddress = orderAddress;
    }

    public float getGoodsProfit() {
        return goodsProfit;
    }

    public void setGoodsProfit(float goodsProfit) {
        this.goodsProfit = goodsProfit;
    }

    public List<OrderForGoodsEntity> getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(List<OrderForGoodsEntity> goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getOrderDelType() {
        return orderDelType;
    }

    public void setOrderDelType(int orderDelType) {
        this.orderDelType = orderDelType;
    }
}
