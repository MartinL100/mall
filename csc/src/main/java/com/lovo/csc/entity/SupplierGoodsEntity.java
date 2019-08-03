package com.lovo.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供应商供应商品中间表
 */
@Entity
@Table(name = "sys_supplierGoods")
public class SupplierGoodsEntity {
    //供应商品表ID
    @Id
    @Column(length=48)
    @GenericGenerator(name="myuuid",strategy="uuid")
    @GeneratedValue(generator="myuuid")
    private String codeId;
    //商品名
    @Column(length=48)
    private String goodsName;
    //商品规格
    @Column(length=48)
    private String goodsNorms;
    //商品类型
    @Column(length=48)
    private String goodsType;
    //商品单位
    @Column(length=48)
    private String goodsUnit;
    //供应商品状态
    @Column(length=48)
    private String supplierStatus;
    //进行的操作（添加/删除）
    @Column(length=48)
    private String supplierType;
    //供应商ID
    @ManyToOne
    @JoinColumn(name="supplier_id")
    private SupplierEntity supplierId;

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
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

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public SupplierEntity getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(SupplierEntity supplierId) {
        this.supplierId = supplierId;
    }
}
