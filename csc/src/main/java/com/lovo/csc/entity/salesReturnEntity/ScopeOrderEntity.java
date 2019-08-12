package com.lovo.csc.entity.salesReturnEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_scope_order")
public class ScopeOrderEntity {
    @Id
    @GenericGenerator(name = "suuid", strategy = "uuid")
    @GeneratedValue(generator = "suuid")
    //退货单id
    @Column(length = 32)
    private String orderId;
//   // 订单号
//    @OneToMany(mappedBy="scopeOrder")
//    private List<OrderEntity> listscopeOrderId;
    //供货商名称
    @Column(length = 32)
    private String supplierName;
    //审核人
    @Column(length = 32)
    private String orderAudit;
    //结算时间
    @Column(length = 32)
    private String closeTime;
    //退货原因
    @Column(length = 32)
    private String goodsSales;
    //订单状态
    @Column(length = 32)
    private String orderState;
//    //审核状态
//    @Column(length = 32)
//    private String orderAuditState;
    //审核意见
    @Column(length = 32)
    private String orderOpinion;


    public String getOrderOpinion() {

        return orderOpinion;
    }

    public void setOrderOpinion(String orderOpinion) {
        this.orderOpinion = orderOpinion;
    }
//
//    public String getOrderAuditState() {
//        return orderAuditState;
//    }
//
//    public void setOrderAuditState(String orderAuditState) {
//        this.orderAuditState = orderAuditState;
//    }

    public String getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(String orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
//
//    public List<OrderEntity> getListscopeOrderId() {
//        return listscopeOrderId;
//    }
//
//    public void setListscopeOrderId(List<OrderEntity> listscopeOrderId) {
//        this.listscopeOrderId = listscopeOrderId;
//    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(String goodsSales) {
        this.goodsSales = goodsSales;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
