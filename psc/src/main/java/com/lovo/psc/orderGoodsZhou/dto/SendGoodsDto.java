package com.lovo.psc.orderGoodsZhou.dto;

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

    public SendGoodsDto(String indentId, String codeId, String supplierId, String goodsName, String goodsNorms, String goodsType, Long supplyNum, String goodsUnit, float supplyPrice) {
        this.indentId = indentId;
        this.codeId = codeId;
        this.supplierId = supplierId;
        this.goodsName = goodsName;
        this.goodsNorms = goodsNorms;
        this.goodsType = goodsType;
        this.supplyNum = supplyNum;
        this.goodsUnit = goodsUnit;
        this.supplyPrice = supplyPrice;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public float getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(float supplyPrice) {
        this.supplyPrice = supplyPrice;
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
