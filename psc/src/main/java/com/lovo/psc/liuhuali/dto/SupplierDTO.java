package com.lovo.psc.liuhuali.dto;

public class SupplierDTO {

    /**供应商编号*/
    private String supplierId;

    /**供应商等级*/
    private String supplierLevel;

    /**供应商审核状态*/
    private String supplierTag;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierLevel() {
        return supplierLevel;
    }

    public void setSupplierLevel(String supplierLevel) {
        this.supplierLevel = supplierLevel;
    }

    public String getSupplierTag() {
        return supplierTag;
    }

    public void setSupplierTag(String supplierTag) {
        this.supplierTag = supplierTag;
    }
}
