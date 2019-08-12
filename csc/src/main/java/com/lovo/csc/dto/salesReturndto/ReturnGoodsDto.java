package com.lovo.csc.dto.salesReturndto;

public class ReturnGoodsDto {
    private String returnGoodsId;//退货订单号
    private String returnGoodsDate;//退货日期
    private String supplierId;//供应商id
    private String supplierName;
    private String goodsId;//商品编号
    private String goodsName;//商品名
    private String goodsType;//商品类型
    private String goodsNorms;//商品规格
    private String goodsUnit;//单位
    private String returnGoodsCause;//退货原因
    private Long returnGoodsNum;//退货数量
    private float goodsBid;//进货价格


    public ReturnGoodsDto() {

    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public ReturnGoodsDto(String returnGoodsId, String returnGoodsDate, String supplierId, String goodsId, String goodsName, String goodsType, String goodsNorms, String goodsUnit, String returnGoodsCause, Long returnGoodsNum, float goodsBid) {
        this.returnGoodsId = returnGoodsId;
        this.returnGoodsDate = returnGoodsDate;
        this.supplierId = supplierId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsNorms = goodsNorms;
        this.goodsUnit = goodsUnit;
        this.returnGoodsCause = returnGoodsCause;
        this.returnGoodsNum = returnGoodsNum;
        this.goodsBid = goodsBid;
    }

    public String getReturnGoodsId() {
        return returnGoodsId;
    }

    public void setReturnGoodsId(String returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
    }

    public String getReturnGoodsDate() {
        return returnGoodsDate;
    }

    public void setReturnGoodsDate(String returnGoodsDate) {
        this.returnGoodsDate = returnGoodsDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getReturnGoodsCause() {
        return returnGoodsCause;
    }

    public void setReturnGoodsCause(String returnGoodsCause) {
        this.returnGoodsCause = returnGoodsCause;
    }

    public Long getReturnGoodsNum() {
        return returnGoodsNum;
    }

    public void setReturnGoodsNum(Long returnGoodsNum) {
        this.returnGoodsNum = returnGoodsNum;
    }

    public float getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(float goodsBid) {
        this.goodsBid = goodsBid;
    }
}
