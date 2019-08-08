package com.lovo.psc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 供货订单实体类
 */
@Entity
@Table(name = "sys_indent")
public class IndentEntity {
    @Id
    @GenericGenerator(name = "iuuid",strategy = "uuid")
    @GeneratedValue(generator = "iuuid")
    @Column(length = 32)
    /**订单编号*/
    private String indentId;
    @Column(length = 48)
    /**订单生成日期*/
    private String indentDate;
    @Column(length = 48)
    /**订单状态*/
    private String indentStatus;
    @Column(length = 48)
    /**结算时间*/
    private String closeTime;
    /**订单总额*/
    private float orderMoney;
    /**中间表集合*/
    @OneToMany(mappedBy = "indentEntity")
    private List<SupplyCenterEntity> supplyCenterList;
    @ManyToOne
    @JoinColumn(name = "supplierId")
    private SupplierEntity supplier;

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getIndentDate() {
        return indentDate;
    }

    public void setIndentDate(String indentDate) {
        this.indentDate = indentDate;
    }

    public String getIndentStatus() {
        return indentStatus;
    }

    public void setIndentStatus(String indentStatus) {
        this.indentStatus = indentStatus;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
        this.orderMoney = orderMoney;
    }

//    public List<SupplyCenterEntity> getSupplyCenterList() {
//        return supplyCenterList;
//    }
//
//    public void setSupplyCenterList(List<SupplyCenterEntity> supplyCenterList) {
//        this.supplyCenterList = supplyCenterList;
//    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public IndentEntity(String indentDate, String indentStatus, String closeTime, float orderMoney, List<SupplyCenterEntity> supplyCenterList, SupplierEntity supplier) {
        this.indentDate = indentDate;
        this.indentStatus = indentStatus;
        this.closeTime = closeTime;
        this.orderMoney = orderMoney;
        this.supplyCenterList = supplyCenterList;
        this.supplier = supplier;
    }

    public IndentEntity() {
    }
}
