package com.lovo.psc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供货中间表实体类
 */
@Entity
@Table(name = "sys_supply")
public class SupplyCenterEntity {
    @Id
    @GenericGenerator(name = "scuuid",strategy = "uuid")
    @GeneratedValue(generator = "scuuid")
    @Column(length = 32)
    /**供货中间表编号*/
    private String supplyId;
    @Column(length = 48)
    /**商品名字*/
    private String goodsName;
    @Column(length = 48)
    /**商品单位*/
    private String goodsNorms;
    @Column(length = 48)
    /**商品类型*/
    private String goodsType;
    @Column(length = 48)
    /**商品单位*/
    private String goodsUnit;
    /**供货数量*/
    private Long supplyNum;
    /**供货报价*/
    private float supplyPrice;
    @Column(length = 48)
    /**报价标志*/
    private String bjTag;
    @Column(length = 48)
    /**中标标志*/
    private String zbTag;
    @Column(length = 48)
    /**供货标志*/
    private String ghTag;

    @Column(length = 48)
    private String cargoId;
    /**订单对象*/
    @ManyToOne
    @JoinColumn(name = "indentId")
    private IndentEntity indentEntity;
    /**供应商商品对象*/
    @ManyToOne
    @JoinColumn(name = "codeId")
    private SupplierGoodsEntity supplierGoodsEntity;

    @ManyToOne
    @JoinColumn(name="supplierId")
    private SupplierEntity supplier;

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
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

    public Long getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(Long supplyNum) {
        this.supplyNum = supplyNum;
    }

    public float getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(float supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public String getBjTag() {
        return bjTag;
    }

    public void setBjTag(String bjTag) {
        this.bjTag = bjTag;
    }

    public String getZbTag() {
        return zbTag;
    }

    public void setZbTag(String zbTag) {
        this.zbTag = zbTag;
    }

    public String getGhTag() {
        return ghTag;
    }

    public void setGhTag(String ghTag) {
        this.ghTag = ghTag;
    }

    public IndentEntity getIndentEntity() {
        return indentEntity;
    }

    public void setIndentEntity(IndentEntity indentEntity) {
        this.indentEntity = indentEntity;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        cargoId = cargoId;
    }

    public SupplierGoodsEntity getSupplierGoodsEntity() {
        return supplierGoodsEntity;
    }



        public void setSupplierGoodsEntity(SupplierGoodsEntity supplierGoodsEntity) {
        this.supplierGoodsEntity = supplierGoodsEntity;
    }

    public SupplyCenterEntity(String goodsName, String goodsNorms, String goodsType, String goodsUnit, Long supplyNum, float supplyPrice, String bjTag, String zbTag, String ghTag, IndentEntity indentEntity, SupplierGoodsEntity supplierGoodsEntity) {
        this.goodsName = goodsName;
        this.goodsNorms = goodsNorms;
        this.goodsType = goodsType;
        this.goodsUnit = goodsUnit;
        this.supplyNum = supplyNum;
        this.supplyPrice = supplyPrice;
        this.bjTag = bjTag;
        this.zbTag = zbTag;
        this.ghTag = ghTag;
        this.indentEntity = indentEntity;
        this.supplierGoodsEntity = supplierGoodsEntity;
    }

    public SupplyCenterEntity() {
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }
}
