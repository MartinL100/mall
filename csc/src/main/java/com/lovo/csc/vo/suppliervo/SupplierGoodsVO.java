package com.lovo.csc.vo.suppliervo;

public class SupplierGoodsVO {
    private String codeId;
    private String supplierStatus;

    public SupplierGoodsVO(String codeId, String supplierStatus) {
        this.codeId = codeId;
        this.supplierStatus = supplierStatus;
    }

    public SupplierGoodsVO() {
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }
}
