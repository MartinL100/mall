package com.lovo.sscafter.orderManagement.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="sys_goods_after")
public class OrderForGoodsEntity {
    //商品id
    @Id
    @Column(name="goods_id",length = 48)
    @GenericGenerator(name = "goodsId", strategy = "uuid")
    @GeneratedValue(generator = "goodsId")
    private String goodsId;
    //商品名
    @Column(name="goods_name",length = 48)
    private String goodsName;
    //商品规格
    @Column(name="goods_norms",length = 48)
    private String goodsNorms;
    //商品类型
    @Column(name="goods_type",length = 48)
    private String goodsType;
    //商品数量
    @Column(name="goods_num")
    private int goodsNum;
    //商品售价
    @Column(name="goods_price",columnDefinition = "double")
    private float goodsPrice;
    //商品单位
    @Column(name="goods_unit",length = 48)
    private String goodsUnit;
    //商品状态  0就是正常,1就是退货中,2就是已退货
    @Column(name="goods_status")
    private int goodsStatus;
    //订单对象
    @ManyToOne()
    @JoinColumn(name = "order_num")
    private OrderManagementEntity orderObj;
    //库存商品外键
    @Column(name="stock_goodsId",length = 48)
    private String stockGoodsId;
    //商品利润
    @Column(name="order_profit",columnDefinition = "double")
    private float orderProfit;
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

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public OrderManagementEntity getOrderObj() {
        return orderObj;
    }

    public void setOrderObj(OrderManagementEntity orderObj) {
        this.orderObj = orderObj;
    }

    public float getOrderProfit() {
        return orderProfit;
    }

    public void setOrderProfit(float orderProfit) {
        this.orderProfit = orderProfit;
    }

    public String getStockGoodsId() {
        return stockGoodsId;
    }

    public void setStockGoodsId(String stockGoodsId) {
        this.stockGoodsId = stockGoodsId;
    }
}
