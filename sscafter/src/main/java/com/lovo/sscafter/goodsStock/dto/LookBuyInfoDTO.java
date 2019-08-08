package com.lovo.sscafter.goodsStock.dto;

public class LookBuyInfoDTO {
    private String indentId;
    private String indentDate;
    private Long supplyNum;
   // private String supplierId;
    private String goodsName;
    private String goodsType;
    private String goodsNorms;
    private String goodsUnit;
   // private float goodsBid;

//    public float getGoodsBid() {
//        return goodsBid;
//    }
//
//    public void setGoodsBid(float goodsBid) {
//        this.goodsBid = goodsBid;
//    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getIndentDate() {
        return indentDate;
    }

    public void setIndentDate(String indentDate) {
        this.indentDate = indentDate;
    }

    public Long getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(Long supplyNum) {
        this.supplyNum = supplyNum;
    }

//    public String getSupplierId() {
//        return supplierId;
//    }
//
//    public void setSupplierId(String supplierId) {
//        this.supplierId = supplierId;
//    }

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

    public LookBuyInfoDTO(String indentId, String indentDate, Long supplyNum, String goodsName, String goodsType, String goodsNorms, String goodsUnit) {
        //this.goodsBid=goodsBid;
        this.indentId = indentId;
        this.indentDate = indentDate;
        this.supplyNum = supplyNum;
       // this.supplierId = supplierId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsNorms = goodsNorms;
        this.goodsUnit = goodsUnit;
    }
}
