package com.lovo.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_order")
public class OrderEntity {
    @Id
    @Column(name="order_num",length = 48)
    private String orderNum;//订单编号
    @Column(length = 48)
    private String orderDate;//下单时间
    @Column(length = 48)
    private String userName;//下单用户
    private double orderMoney;//订单金额
    private double payMoney;//实付金额
    @Column(length = 48)
    private String payMethod;//支付方式
    private int tag;//订单状态
    @Transient
    private String tagStr;

    @OneToMany(mappedBy ="order",fetch = FetchType.LAZY)

    private List<CenterOrderGoods> orderGoods;

    public String getTagStr() {
        return tagStr;
    }

    public void setTagStr(String tagStr) {
        this.tagStr = tagStr;
    }

    public List<CenterOrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<CenterOrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
