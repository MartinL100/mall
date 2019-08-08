package com.lovo.csc.entity.dto;

import com.lovo.csc.entity.CenterOrderGoods;

import javax.persistence.*;
import java.util.List;


public class OrderDto {

    private String orderNum;//订单编号

    private String orderDate;//下单时间

    private String userName;//下单用户
    private double orderMoney;//订单金额
    private double payMoney;//实付金额

    private String payMethod;//支付方式
    private List<GoodsDto> goodsList;

    public List<GoodsDto> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsDto> goodsList) {
        this.goodsList = goodsList;
    }

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

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }


}
