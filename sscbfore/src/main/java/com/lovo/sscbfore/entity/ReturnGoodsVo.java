package com.lovo.sscbfore.entity;

/**
 * @author che
 * @title: ReturnGoodsVo
 * @projectName mall
 * @description: 退货VO
 * @date 2019/8/3  16:00
 */
public class ReturnGoodsVo {

    /**
     * 商品id
     */
    private int goodsId;


    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private int goodsNum;

    /**
     * 商品价格
     */
    private String goodsPrice;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
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

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
