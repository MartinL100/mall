package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_order_goods")
public class OrderEntity {
    @Id
    @Column(name="id2",length = 48)
    @GenericGenerator(name = "id2", strategy = "uuid")
    @GeneratedValue(generator = "id2")
  private String id2;
    @Column(name="order_id",length = 48)
    private String orderId;//供货批次单号


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }
}
