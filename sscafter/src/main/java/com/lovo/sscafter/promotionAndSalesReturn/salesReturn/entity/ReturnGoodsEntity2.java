package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity;

import javax.persistence.*;


/**
 * 退货商品实体
 */
@Entity
@Table(name="sys_returngoods")
public class ReturnGoodsEntity2 {
    //商品id
    @Id
    @Column(name = "goodsId")
    private String goodsId;
    //商品名
    private String goodsName;
    //商品数量
    private int goodsNum;
    //商品价格
    private float goodsPrice;
    //退货用户。
    private String userName;
    //商品订单id
    private String goodsOrderId;
    //退货订单状态
    private int returnOredrState;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsOrderId() {
        return goodsOrderId;
    }

    public void setGoodsOrderId(String goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }

    public int getReturnOredrState() {
        return returnOredrState;
    }

    public void setReturnOredrState(int returnOredrState) {
        this.returnOredrState = returnOredrState;
    }
}
