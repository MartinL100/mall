package com.lovo.csc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_goods_review")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class GoodsEntity {
    @Id
    @Column(name = "goods_id",length = 48)
    private String goodsId;//商品id
    @Column(length = 48)
    private String goodsName;//商品名
    @Column(length = 48)
    private String goodsNorms;//商品规格
    @Column(length = 48)
    private String goodsType;//商品类型

    @Column(length = 48)
    private String goodsUnit;//商品单位
//    @OneToMany(mappedBy ="goods",fetch = FetchType.LAZY)
//    @JsonIgnore
//
//    private List<CenterOrderGoods> orderGoods;

//    public List<CenterOrderGoods> getOrderGoods() {
//        return orderGoods;
//    }
//
//    public void setOrderGoods(List<CenterOrderGoods> orderGoods) {
//        this.orderGoods = orderGoods;
//    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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


}
