package com.lovo.csc.entity.salesReturnEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 供应商实体类
 */
@Entity
@Table(name = "sys_supplier")
public class SupplierEntity1 {
    @Id
    @GenericGenerator(name = "suuid",strategy = "uuid")
    @GeneratedValue(generator = "suuid")
    @Column(length = 32)
    /**供应商编号*/
    private String supplierId;
    @Column(length = 48)
    /**供应商名称*/
    private String supplierName;
    @Column(length = 48)
    /**负责人*/
    private String supplierPrincipal;
    @Column(length = 48)
    /**负责人电话*/
    private String supplierTel;
    @Column(length = 48)
    /**供应商地址*/
    private String supplierAddress;
    @Column(length = 48)
    /**供应商类型*/
    private String supplierType;
    @Column(length = 48)
    /**供应商等级*/
    private String supplierLevel;
    @Column(length = 48)
    /**供应商审核状态*/
    private String supplierTag;
    @Column(length = 48)
    /**审核人*/
    private String checkName;
    @Column(length = 48)
    /**审核时间*/
    private String checkDate;
    /**商品对象集合 */
   // @OneToMany(mappedBy = "supplier")
   // private List<SupplierGoodsEntity> supplierGoodsList;
  //  @OneToMany(mappedBy = "supplier")
 //   private List<SupplyCenterEntity> supplyCenterList;
   // @OneToMany(mappedBy = "supplier")
  //  private List<BackGoods> backGoodsList;

//    public List<BackGoods> getBackGoodsList() {
//        return backGoodsList;
//    }
//    public void setBackGoodsList(List<BackGoods> backGoodsList) {
//        this.backGoodsList = backGoodsList;
//    }
//
//    public List<SupplyCenterEntity> getSupplyCenterList() {
//        return supplyCenterList;
//    }
//
//    public void setSupplyCenterList(List<SupplyCenterEntity> supplyCenterList) {
//        this.supplyCenterList = supplyCenterList;
//    }
//
//    public List<SupplierGoodsEntity> getSupplierGoodsList() {
//        return supplierGoodsList;
//    }
//
//    public void setSupplierGoodsList(List<SupplierGoodsEntity> supplierGoodsList) {
//        this.supplierGoodsList = supplierGoodsList;
//    }

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

    public String getSupplierTel() {
        return supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
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

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }


    public SupplierEntity1() {
    }
}
