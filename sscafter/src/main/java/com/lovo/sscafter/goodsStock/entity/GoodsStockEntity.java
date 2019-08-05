package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_goods_stock")
public class GoodsStockEntity {
    @Id
    @Column(name="goods_id",length = 48)
    @GenericGenerator(name = "goodsId", strategy = "uuid")
    @GeneratedValue(generator = "goodsId")
    private String goodsId;//商品编号
    @Column(name="goods_name",length = 48)
    private String goodsName;//商品名称
    @Column(name = "goods_type",length = 48)
   private String goodsType;//商品类型
    @Column(name = "goods_norms",length = 48)
    private String goodsNorms;//商品规格
    @Column(name = "goods_unit")
private String goodsUnit;//商品单位
    @Column(name = "goods_num")
private Long goodsNum;//商品库存
    @Column(name = "goods_min_num")
   private long goodsMinNum;//最低库存量
    @Column(name = "tag")
private  String tag;//是否正在采购
     @OneToMany(mappedBy = "goods",fetch = FetchType.LAZY)
    private List<ReturnGoodsEntity>  returnGoodsEntityList;
    @OneToMany(mappedBy = "goods",fetch = FetchType.LAZY)
    private List<SupplyEntity> supplyEntityList;
    @OneToMany(mappedBy = "goods",fetch = FetchType.LAZY)
    private List<OrderGoodsEntity> orderGoodsEntityList;


    public List<ReturnGoodsEntity> getReturnGoodsEntityList() {
        return returnGoodsEntityList;
    }

    public void setReturnGoodsEntityList(List<ReturnGoodsEntity> returnGoodsEntityList) {
        this.returnGoodsEntityList = returnGoodsEntityList;
    }

    public List<SupplyEntity> getSupplyEntityList() {
        return supplyEntityList;
    }

    public void setSupplyEntityList(List<SupplyEntity> supplyEntityList) {
        this.supplyEntityList = supplyEntityList;
    }

    public List<OrderGoodsEntity> getOrderGoodsEntityList() {
        return orderGoodsEntityList;
    }

    public void setOrderGoodsEntityList(List<OrderGoodsEntity> orderGoodsEntityList) {
        this.orderGoodsEntityList = orderGoodsEntityList;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public long getGoodsMinNum() {
        return goodsMinNum;
    }

    public void setGoodsMinNum(long goodsMinNum) {
        this.goodsMinNum = goodsMinNum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
