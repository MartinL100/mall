package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "goods_orde")
public class OrderGoodsEntity {
    @Id
    @Column(name="id",length = 48)
    @GenericGenerator(name = "id", strategy = "uuid")
    @GeneratedValue(generator = "id")
private String id;
    @Column(name="goods_bid",length = 48)
    private float goodsBid;//进货价格
    @Column(name="goods_num",length = 48)
    private  long goodsNum;//进货数量
    private String isReturnGoods;//是否正在退货
    @Column(name="supplier_id",length = 48)
    private String supplierId;
    @Column(name = "goods_order_date",length = 48)
    private String goodsOrderDate;//到货日期
@ManyToOne
@JoinColumn
    private GoodsStockEntity goods;
@ManyToOne
@JoinColumn
    private OrderEntity order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(float goodsBid) {
        this.goodsBid = goodsBid;
    }

    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getIsReturnGoods() {
        return isReturnGoods;
    }

    public void setIsReturnGoods(String isReturnGoods) {
        this.isReturnGoods = isReturnGoods;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsOrderDate() {
        return goodsOrderDate;
    }

    public void setGoodsOrderDate(String goodsOrderDate) {
        this.goodsOrderDate = goodsOrderDate;
    }

    public GoodsStockEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsStockEntity goods) {
        this.goods = goods;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
