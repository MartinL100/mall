package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_indent")
public class IndentEntity {
    @Id
    @Column(name="indent_id",length = 48)
    @GenericGenerator(name = "indentId", strategy = "uuid")
    @GeneratedValue(generator = "indentId")
    private String indentId;
@Column(name="indent_date",length = 48)
    private String indentDate;//采购订单日期
    @Column(name="indent_status",length = 48)
    private String indentStatus;//订单状态
    @OneToMany(mappedBy = "indent",fetch = FetchType.LAZY)
   private List<SupplyEntity> supplyEntityList;


    public List<SupplyEntity> getSupplyEntityList() {
        return supplyEntityList;
    }

    public void setSupplyEntityList(List<SupplyEntity> supplyEntityList) {
        this.supplyEntityList = supplyEntityList;
    }

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
}
