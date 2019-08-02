package com.lovo.sscafter.orderManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="sys_order_after")
public class OrderManagementEntity {
    //订单编号
    private String orderNum;
    //订单类型
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
    private int orderAddress;
    //商品集合
//    List<> int goodsNum;

    /*
    *  order_money          DOUBLE,
   pay_money            DOUBLE,
   pay_method           VARCHAR(48),
   order_delType        INT,
   order_address        INT,
   goods_num            INT,*/
}
