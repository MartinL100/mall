package com.lovo.csc.entity.salesReturnEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_order_goods")
public class OrderGoodsEntity {
    @Id
    @GenericGenerator(name = "ouuid", strategy = "uuid")
    @GeneratedValue(generator = "ouuid")
    //退货单id
    @Column(length = 32)
    private String orderId;
    //审核人
    @Column(length = 32)
    private String goodsAudit;
    //审核时间
    @Column(length = 32)
    private String goodsDate;
    @Column(length = 48)
    private String auditType;
//    //审核状态
//    @Column(length = 32)
//    private String goodsAuditState;

    //退货单状态
    @Column(length = 32)
    private String goodsState;
//    //订单号
//    @OneToMany(mappedBy="orderGoods")
//    private List<GoodsEntity> orderNum;
    //审核意见
    @Column(length = 32)
    private String auditOpinion;
    //退货用户
    @Column(length = 32)
    private String orderName;

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsAudit() {
        return goodsAudit;
    }

    public void setGoodsAudit(String goodsAudit) {
        this.goodsAudit = goodsAudit;
    }

    public String getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(String goodsDate) {
        this.goodsDate = goodsDate;
    }

//    public String getGoodsAuditState() {
//        return goodsAuditState;
//    }
//
//    public void setGoodsAuditState(String goodsAuditState) {
//        this.goodsAuditState = goodsAuditState;
//    }

    public String getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(String goodsState) {
        this.goodsState = goodsState;
    }

//    public List<GoodsEntity> getOrderNum() {
//        return orderNum;
//    }
//
//    public void setOrderNum(List<GoodsEntity> orderNum) {
//        this.orderNum = orderNum;
//    }
}
