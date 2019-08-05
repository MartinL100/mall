package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_return_goods")
public class ReturnGoodsEntity {
    @Id
    @Column(name="return_goods_id",length = 48)
    @GenericGenerator(name = "returnGoodsId", strategy = "uuid")
    @GeneratedValue(generator = "returnGoodsId")
    private String returnGoodsId;
    @Column(name="return_goods_date",length = 48)
    private String returnGoodsDate;
    private String returnGoodsCause;
   @ManyToOne
   @JoinColumn
    private GoodsStockEntity goods;


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

    public GoodsStockEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsStockEntity goods) {
        this.goods = goods;
    }
}
