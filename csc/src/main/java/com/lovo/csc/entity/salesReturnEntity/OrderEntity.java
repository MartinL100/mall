package com.lovo.csc.entity.salesReturnEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_orders")
public class OrderEntity {
    @javax.persistence.Id
    @GenericGenerator(name = "guuid", strategy = "uuid")
    @GeneratedValue(generator = "guuid")
    //商品id
    @Column(length = 32)
    private String Id;
    //订单号
    @ManyToOne
    @JoinColumn(name="o_id")
    private ScopeOrderEntity scopeOrder;

    //商品编号
    @Column(length =32)
    private  String goodsId;
    //商品名
    @Column(length =32)
    private String goodsName;
    //进货数量
    @Column(length =32)
    private  long goodsNum;
    //进货价格
    @Column(length =32)
    private  float goodsBid;



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ScopeOrderEntity getScopeOrder() {
        return scopeOrder;
    }

    public void setScopeOrder(ScopeOrderEntity scopeOrder) {
        this.scopeOrder = scopeOrder;
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

    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public float getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(float goodsBid) {
        this.goodsBid = goodsBid;
    }
}
