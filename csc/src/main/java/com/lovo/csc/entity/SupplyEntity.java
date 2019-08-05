package com.lovo.csc.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 供货订单中间表
 */
@Entity
@Table(name = "sys_supply")
public class SupplyEntity {
    //供货中间表ID
    @Id
    @Column(length = 48)
    @GenericGenerator(name = "myuuid", strategy = "uuid")
    @GeneratedValue(generator = "myuuid")
    private String supplyId;
    //商品名
    @Column(length = 48)
    private String goodsName;
    //商品规格
    @Column(length = 48)
    private String goodsNorms;
    //商品类型
    @Column(length = 48)
    private String goodsType;
    //商品单位
    @Column(length = 48)
    private String goodsUnit;
    //供应数量
    @Column()
    private int supplyNum;
    // 报价  数据库转decimal类型
    @Column()
    private BigDecimal supplyPrice;
    //投标审核人
    @Column(length = 48)
    private String tenderPeople;
    //投标时间
    @Column(length = 48)
    private String tenderDate;
    //采购审核人
    @Column(length = 48)
    private String purchasePeople;
    //采购人
    @Column(length = 48)
    private String purchaseDate;
    //采购商品状态
    @Column(length = 48)
    private String indentStatus;
    //供货订单ID
    @ManyToOne
    @JoinColumn(name = "indent_id")
    private IndentEntity indentId;
    //供货商ID
    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;

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

    public int getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(int supplyNum) {
        this.supplyNum = supplyNum;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public String getTenderPeople() {
        return tenderPeople;
    }

    public void setTenderPeople(String tenderPeople) {
        this.tenderPeople = tenderPeople;
    }

    public String getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(String tenderDate) {
        this.tenderDate = tenderDate;
    }

    public String getPurchasePeople() {
        return purchasePeople;
    }

    public void setPurchasePeople(String purchasePeople) {
        this.purchasePeople = purchasePeople;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getIndentStatus() {
        return indentStatus;
    }

    public void setIndentStatus(String indentStatus) {
        this.indentStatus = indentStatus;
    }

    public IndentEntity getIndentId() {
        return indentId;
    }

    public void setIndentId(IndentEntity indentId) {
        this.indentId = indentId;
    }

    public SupplierEntity getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(SupplierEntity supplierId) {
        this.supplierId = supplierId;
    }
}
