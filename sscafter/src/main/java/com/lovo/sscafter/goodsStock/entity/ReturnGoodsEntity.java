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
    private String supplierId;//供应商编号
    private Long returnGoodsNum;//退货数量

    public Long getReturnGoodsNum() {
        return returnGoodsNum;
    }

    public void setReturnGoodsNum(Long returnGoodsNum) {
        this.returnGoodsNum = returnGoodsNum;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getReturnGoodsCause() {
        return returnGoodsCause;
    }

    public void setReturnGoodsCause(String returnGoodsCause) {
        this.returnGoodsCause = returnGoodsCause;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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
