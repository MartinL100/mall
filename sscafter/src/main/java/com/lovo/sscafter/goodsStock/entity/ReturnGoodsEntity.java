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
   @ManyToOne
   @JoinColumn
    private GoodsStockEntity goods;
}
