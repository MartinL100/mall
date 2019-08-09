package com.lovo.psc.wangwei.dto;


public class InderDTO {

    private String goodsName;
    private String indentId;
    private String zbTag;
    private String ghTag;
    private String indentDate;
    private float orderMoney;
    private String goodsNorms;
    private String goodsType;
    private String goodsUnit;
    private Long supplyNum;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getZbTag() {
        return zbTag;
    }

    public void setZbTag(String zbTag) {
        this.zbTag = zbTag;
    }

    public String getGhTag() {
        return ghTag;
    }

    public void setGhTag(String ghTag) {
        this.ghTag = ghTag;
    }

    public String getIndentDate() {
        return indentDate;
    }

    public void setIndentDate(String indentDate) {
        this.indentDate = indentDate;
    }

    public float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
        this.orderMoney = orderMoney;
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

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Long getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(Long supplyNum) {
        this.supplyNum = supplyNum;
    }

    public InderDTO(String goodsName, String indentId, String zbTag, String ghTag, String indentDate, float orderMoney, String goodsNorms, String goodsType, String goodsUnit, Long supplyNum) {
        this.goodsName = goodsName;
        this.indentId = indentId;
        this.zbTag = zbTag;
        this.ghTag = ghTag;
        this.indentDate = indentDate;
        this.orderMoney = orderMoney;
        this.goodsNorms = goodsNorms;
        this.goodsType = goodsType;
        this.goodsUnit = goodsUnit;
        this.supplyNum = supplyNum;
    }

    public InderDTO() {
    }
}
