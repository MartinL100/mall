package com.lovo.csc.entity;

import com.lovo.csc.entity.supplierEntity.AuditEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供应商表
 */
@Entity
@Table(name = "sys_supplier")
public class SupplierEntity {
    //供应商ID
    @Id
    @Column(length=48)
//    @GenericGenerator(name="myuuid",strategy="uuid")
//    @GeneratedValue(generator="myuuid")
    private String supplierId;
    //供应商名称
    @Column(length=48)
    private String supplierName;
    //负责人
    @Column(length=48)
    private String supplierPrincipal;
    //负责人电话
    @Column(length=48)
    private String principalTel;
    //供应商地址
    @Column
    private String supplierAddress;
    //供应商类型
    @Column(length=48)
    private String supplierType;
    //供应商等级
    @Column(length=48)
    private String supplierLevel;
    //供应商状态
    @Column(length=48)
    private String supplierTag;
    //审核时间
    @Column(length=48)
    private String checkDate;
    //审核人
    @OneToOne
    @JoinColumn(name="audit_id")
    private AuditEntity checkId;
    @Column(length=48)
    private String checkName;

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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPrincipal() {
        return supplierPrincipal;
    }

    public void setSupplierPrincipal(String supplierPrincipal) {
        this.supplierPrincipal = supplierPrincipal;
    }

    public String getPrincipalTel() {
        return principalTel;
    }

    public void setPrincipalTel(String principalTel) {
        this.principalTel = principalTel;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public AuditEntity getCheckId() {
        return checkId;
    }

    public void setCheckId(AuditEntity checkId) {
        this.checkId = checkId;
    }
}
