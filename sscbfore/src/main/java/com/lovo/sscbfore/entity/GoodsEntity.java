package com.lovo.sscbfore.entity;



public  class GoodsEntity {


    //商品ID
    private  String goodsId;
   //商品名
    private String goodsName;
   //商品类型
    private  String goodsType;
   //商品规格
    private  String goodsNorms;
   //商品单位
    private  String goodsUnit;
   //商品进价
    private  Float goodsBid;
   //商品图片地址
    private  String goodsPath;
   //商品售价
    private  Float goodsPrice;
   //商品折扣1
    private  int goodsDiscount;
   //商品数量
    private  int goodsNum;
   //商品状态
    private  String goodsState;
    //预售状态
    private  String goodsBooking;
    //促销状态
    private  String  promotionState;
   //上架时间
    private  String shelfTime;
   //下架时间
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
