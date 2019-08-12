package com.lovo.common.entity;

/**
 * 用于生成商品订单的商品dto
 */
public class GoodssDTO {
    /**商品id*/
    private String goodsId;
    /**商品名*/
    private String goodsName;
    /**规格*/
    private String goodsNorms;
    /**商品售价*/
    private Float goodsPrice;
    /**商品数量*/
    private Long goodsNum;
    /**商品类型*/
    private String goodsType;
    /**商品单位*/
    private String goodsUnit;
   /**商品标志（查看是否被选中）*/
   private int choose;


    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

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

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
}
