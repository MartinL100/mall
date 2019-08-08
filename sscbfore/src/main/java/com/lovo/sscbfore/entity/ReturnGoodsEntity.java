package com.lovo.sscbfore.entity;

/**
 * @author che
 * @title: ReturnGoodsEntity
 * @projectName mall
 * @description: 退货商品实体类
 * @date 2019/8/6  15:35
 */
public class ReturnGoodsEntity {

    /**
     * 退货商品id
     */
    private String goodsId;

    /**
     * 退货商品名称
     */
    private String goodsName;

    /**
     * 退货商品数量
     */
    private String goodsNum;

    /**
     * 退货商品价格
     */
    private String goodsPrice;


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