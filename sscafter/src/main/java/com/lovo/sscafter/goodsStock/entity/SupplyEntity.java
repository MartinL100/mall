package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_supply")
public class SupplyEntity {
    @Id
    @Column(name="supply_id",length = 48)
    @GenericGenerator(name = "supplyId", strategy = "uuid")
    @GeneratedValue(generator = "supplyId")
    private String supplyId;
    @Column(name = "supply_num",length = 48)
    private long supplyNum;

    @ManyToOne
    @JoinColumn
    private GoodsStockEntity goods;
    @ManyToOne
    @JoinColumn
    private IndentEntity indent;


    public IndentEntity getIndent() {
        return indent;
    }

    public void setIndent(IndentEntity indent) {
        this.indent = indent;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public long getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(long supplyNum) {
        this.supplyNum = supplyNum;
    }


    public GoodsStockEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsStockEntity goods) {
        this.goods = goods;
    }
}
