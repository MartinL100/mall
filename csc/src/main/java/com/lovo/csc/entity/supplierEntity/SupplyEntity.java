package com.lovo.csc.entity.supplierEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供货中间表表
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
    //供货数量
    @Column()
    private int supplyNum;
    //供货订单ID
    @ManyToOne
    @JoinColumn(name = "indent_id")
    private IndentEntity indentId;
    //采购商品状态
    @Column(length = 48)
    private String indentStatus;

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

    public IndentEntity getIndentId() {
        return indentId;
    }

    public void setIndentId(IndentEntity indentId) {
        this.indentId = indentId;
    }

    public String getIndentStatus() {
        return indentStatus;
    }

    public void setIndentStatus(String indentStatus) {
        this.indentStatus = indentStatus;
    }
}
