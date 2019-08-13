package com.lovo.csc.vo.suppliervo;

public class SupplierVO {
    private String supplierId;
    private String supplierLevel;
    private String supplierTag;
    private String checkName;
    private String checkDate;
    public SupplierVO() {
    }

    public SupplierVO(String supplierId, String supplierLevel, String supplierTag) {
        this.supplierId = supplierId;
        this.supplierLevel = supplierLevel;
        this.supplierTag = supplierTag;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

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
