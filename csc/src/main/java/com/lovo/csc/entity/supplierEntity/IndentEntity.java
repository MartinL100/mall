package com.lovo.csc.entity.supplierEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 供货订单表
 */
@Entity
@Table(name = "sys_indent")
public class IndentEntity {
    //供货订单ID
    @Id
    @Column(length=48)
//    @GenericGenerator(name="myuuid",strategy="uuid")
//    @GeneratedValue(generator="myuuid")
    private String indentId;
    //订单生产日期
    @Column(length=48)
    private String indentDate;
    //订单状态
    @Column(length=48)
    private String indentStatus;
    //结算时间
    @Column(length=48)
    private String closeTime;
    //订单金额  数据库转decimal类型
    @Column()
//    @ColumnDefault(value = "decimal")
    private BigDecimal orderMoney;

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

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }
}
