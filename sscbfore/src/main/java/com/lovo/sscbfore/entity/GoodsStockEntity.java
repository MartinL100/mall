package com.lovo.sscbfore.entity;


public class GoodsStockEntity {


    private String goodsId;//商品编号
    private String goodsName;//商品名称
    private String goodsType;//商品类型
    private String goodsNorms;//商品规格
    private String goodsUnit;//商品单位
    private Long goodsNum;//商品库存
    private long goodsMinNum;//最低库存量
    private String tag;//是否正在采购
    private String tag1;//是否已添加

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public long getGoodsMinNum() {
        return goodsMinNum;
    }

    public void setGoodsMinNum(long goodsMinNum) {
        this.goodsMinNum = goodsMinNum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
