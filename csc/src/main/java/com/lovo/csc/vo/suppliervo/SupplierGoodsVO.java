package com.lovo.csc.vo.suppliervo;

public class SupplierGoodsVO {
    private String supplierGoods;
    private String supplierStatus;

    public SupplierGoodsVO(String supplierGoods, String supplierStatus) {
        this.supplierGoods = supplierGoods;
        this.supplierStatus = supplierStatus;
    }

    public SupplierGoodsVO() {
    }

    public String getSupplierGoods() {
        return supplierGoods;
    }

    public void setSupplierGoods(String supplierGoods) {
        this.supplierGoods = supplierGoods;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }
}
