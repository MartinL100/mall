package com.lovo.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="center_order_goods")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//转化json时忽略实体为空的字段
public class CenterOrderGoods {
    @Id
    @Column(name = "center_id",length = 48)
    @GenericGenerator(name="guuid",strategy = "uuid")
    @GeneratedValue(generator = "guuid")
    private String centerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_num")
    @JsonIgnore
    private OrderEntity order;//订单号
    @ManyToOne
    @JoinColumn(name="goods_id")
    private GoodsEntity goods;//商品编号
    @Column(length = 48)
    private String goodsNum;//订单数量
    @Column(length = 48)
    private String goodsPrice;//商品价格

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

//    public OrderEntity getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderEntity order) {
//        this.order = order;
//    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
