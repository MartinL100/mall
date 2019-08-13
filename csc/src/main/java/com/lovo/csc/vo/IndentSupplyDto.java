package com.lovo.csc.vo;


import java.math.BigDecimal;

public class IndentSupplyDto {

    private String supplyId;
    //商品名
    private String goodsName;
    //商品规格
    private String goodsNorms;
    //商品类型
    private String goodsType;
    //商品单位
    private String goodsUnit;
    //供货数量
    private int supplyNum;
    //供货订单ID
    private String indentId;
    //报价
    private BigDecimal supplyPrice;

    public IndentSupplyDto(){

    }

    public IndentSupplyDto(String supplyId,String goodsName,String goodsNorms,String goodsType,String goodsUnit,int supplyNum,String indentId,BigDecimal supplyPrice){
        this.supplyId=supplyId;
        this.goodsName=goodsName;
        this.goodsNorms=goodsNorms;
        this.goodsType=goodsType;
        this.goodsUnit=goodsUnit;
        this.supplyNum=supplyNum;
        this.indentId=indentId;
        this.supplyPrice=supplyPrice;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
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

    public int getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(int supplyNum) {
        this.supplyNum = supplyNum;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

}
