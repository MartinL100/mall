package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity;

import javax.persistence.*;


/**
 * 退货商品实体
 */
@Entity
@Table(name="sys_returngoods")
public class ReturnGoodsEntity {
    //商品id
    @Id
    private String goodsId;
    //商品名
    private String goodsName;
    //商品数量
    private int goodsNum;
    //商品价格
    private float goodsPrice;
    //退货单编号
    @ManyToOne
    @JoinColumn(name = "returnOderId")
    private ReturnOrderEntity returnOderId;

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

    public ReturnOrderEntity getReturnOderId() {
        return returnOderId;
    }

    public void setReturnOderId(ReturnOrderEntity returnOderId) {
        this.returnOderId = returnOderId;
    }
}
