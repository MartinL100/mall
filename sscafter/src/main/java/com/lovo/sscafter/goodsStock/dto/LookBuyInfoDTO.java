package com.lovo.sscafter.goodsStock.dto;

public class LookBuyInfoDTO {
    private String indentId;//采购单号
    private String indentDate;//采购日期
    private Long supplyNum; //采购数量
    private String goodsName;//商品名称
    private String goodsType;//商品 类型
    private String goodsNorms;//商品规格
    private String goodsUnit;//商品单位

    public LookBuyInfoDTO() {
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

    public Long getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(Long supplyNum) {
        this.supplyNum = supplyNum;
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
