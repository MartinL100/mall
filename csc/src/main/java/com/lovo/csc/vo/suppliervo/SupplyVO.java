package com.lovo.csc.vo.suppliervo;

public class SupplyVO {
    private String indentId;
    private String indentDate;
    private String cargoId;
    private String goodsName;
    private String goodsNorms;
    private String goodsType;
    private String goodsUnit;
    private String goodsNum;
    private String supplierId;

    public SupplyVO() {
    }

    public SupplyVO(String indentId, String indentDate, String cargoId, String goodsName, String goodsNorms, String goodsType, String goodsUnit, String goodsNum, String supplierId) {
        this.indentId = indentId;
        this.indentDate = indentDate;
        this.cargoId = cargoId;
        this.goodsName = goodsName;
        this.goodsNorms = goodsNorms;
        this.goodsType = goodsType;
        this.goodsUnit = goodsUnit;
        this.goodsNum = goodsNum;
        this.supplierId = supplierId;
    }

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

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
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

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
