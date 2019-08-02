package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

}
