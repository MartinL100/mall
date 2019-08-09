package com.lovo.csc.entity.salesReturnEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_goodss")
public class GoodsEntity {
    @Id
    @GenericGenerator(name = "guuid", strategy = "uuid")
    @GeneratedValue(generator = "guuid")
    //商品id
    @Column(length =32)
    private String goodsId;
    //退货单id
    @Column(length = 32)
    private String goodssId;
    @ManyToOne
    @JoinColumn(name="g_id")
    private OrderGoodsEntity orderGoods;

    //商品名
    @Column(length =32)
    private String goodsName;
    //商品数量
    @Column(length =32)
    private long goodsNum;
    //商品价格
    @Column(length =32)
    private  float goodsPrice;


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodssId() {
        return goodssId;
    }

    public void setGoodssId(String goodssId) {
        this.goodssId = goodssId;
    }

    public OrderGoodsEntity getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(OrderGoodsEntity orderGoods) {
        this.orderGoods = orderGoods;
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

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
