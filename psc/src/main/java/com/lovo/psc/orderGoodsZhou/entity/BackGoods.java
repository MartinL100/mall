package com.lovo.psc.orderGoodsZhou.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 退货实体类
 */
@Entity
@Table(name = "back_goods")
public class BackGoods {
    @Id
    @GenericGenerator(name = "buuid",strategy = "uuid")
    @GeneratedValue(generator = "buuid")
    @Column(length = 32)
    /**退货编号*/
    private String returnGoodsId;
    @Column(length = 48)
    /**退货时间*/
    private String returnGoodsDate;
    @Column(length = 64)
    /**退货原因*/
    private String returnGoodsCause;
    @Column(length = 48)
    /**商品名称*/
    private String goodsName;
    /**商品数量*/
    private int goodsNum;
    @Column(length = 48)
    /**商品规格*/
    private String goodsGauge;
    /**进货价格*/
    private float goodsBid;
    /**供应商对象*/
    @ManyToOne
    @JoinColumn(name = "supplierId")
    private SupplierEntity supplier;

    public String getReturnGoodsId() {
        return returnGoodsId;
    }

    public void setReturnGoodsId(String returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
    }

    public String getReturnGoodsDate() {
        return returnGoodsDate;
    }

    public void setReturnGoodsDate(String returnGoodsDate) {
        this.returnGoodsDate = returnGoodsDate;
    }

    public String getReturnGoodsCause() {
        return returnGoodsCause;
    }

    public void setReturnGoodsCause(String returnGoodsCause) {
        this.returnGoodsCause = returnGoodsCause;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsGauge() {
        return goodsGauge;
    }

    public void setGoodsGauge(String goodsGauge) {
        this.goodsGauge = goodsGauge;
    }

    public float getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(float goodsBid) {
        this.goodsBid = goodsBid;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public BackGoods(String returnGoodsDate, String returnGoodsCause, String goodsName, int goodsNum, String goodsGauge, float goodsBid, SupplierEntity supplier) {
        this.returnGoodsDate = returnGoodsDate;
        this.returnGoodsCause = returnGoodsCause;
        this.goodsName = goodsName;
        this.goodsNum = goodsNum;
        this.goodsGauge = goodsGauge;
        this.goodsBid = goodsBid;
        this.supplier = supplier;
    }

    public BackGoods() {
    }
}
