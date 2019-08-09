package com.lovo.sscafter.goodsStock.dto;

public class SendGoodsDto {
    private String indentId;
    private  String codeId;
    private String supplierId;
    private String goodsName;
    private  String goodsNorms;
    private String goodsType;
    private Long supplyNum;
    private String goodsUnit;
    private float supplyPrice;


    public float getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(float supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNoms() {
        return goodsNorms;
    }

    public void setGoodsNoms(String goodsNoms) {
        this.goodsNorms = goodsNoms;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Long getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(Long supplyNum) {
        this.supplyNum = supplyNum;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }


    public SendGoodsDto() {
    }
}
