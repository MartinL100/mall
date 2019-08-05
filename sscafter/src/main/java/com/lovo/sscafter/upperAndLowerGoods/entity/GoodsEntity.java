package com.lovo.sscafter.upperAndLowerGoods.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_goods")
public class GoodsEntity {
    @Id
    //商品ID
    @Column(name="goodsId",length = 48)
    private  String goodsId;
   //商品名
   @Column(name="goodsName",length=48)
    private String goodsName;
   //商品类型
    @Column(name="goodsType",length = 48)
    private  String goodsType;
   //商品规格
    @Column(name="goodsNorms",length = 48)
    private  String goodsNorms;
   //商品单位
    @Column(name="goodsUnit",length = 48)
    private  String goodsUnit;
   //商品进价
    @Column(name="goodsBid")
    private  Float goodsBid;
   //商品图片地址
    @Column(name="goodsPath",length = 48)
    private  String goodsPath;
   //商品售价
    @Column(name="goodsPrice")
    private  Float goodsPrice;
   //商品折扣1
    @Column(name="goodsDiscount")
    private  int goodsDiscount;
   //商品数量
    @Column(name="goodsNum")
    private  int goodsNum;
   //商品状态
    @Column(name="goodsState",length = 48)
    private  String goodsState;
    //预售状态
    @Column(name="goodsBooking",length = 48)
    private  String goodsBooking;
    //促销状态
    @Column(name="promotionState",length = 48)
    private  String  promotionState;
   //上架时间
    @Column(name="shelfTime",length = 48)
    private  String shelfTime;
   //下架时间
    @Column(name="lowerTime",length = 48)
    private  String lowerTime;

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

    public Float getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(Float goodsBid) {
        this.goodsBid = goodsBid;
    }

    public String getGoodsPath() {
        return goodsPath;
    }

    public void setGoodsPath(String goodsPath) {
        this.goodsPath = goodsPath;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(int goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(String goodsState) {
        this.goodsState = goodsState;
    }

    public String getGoodsBooking() {
        return goodsBooking;
    }

    public void setGoodsBooking(String goodsBooking) {
        this.goodsBooking = goodsBooking;
    }

    public String getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(String promotionState) {
        this.promotionState = promotionState;
    }

    public String getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(String shelfTime) {
        this.shelfTime = shelfTime;
    }

    public String getLowerTime() {
        return lowerTime;
    }

    public void setLowerTime(String lowerTime) {
        this.lowerTime = lowerTime;
    }
}
