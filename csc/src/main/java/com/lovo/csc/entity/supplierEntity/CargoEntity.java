package com.lovo.csc.entity.supplierEntity;

import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.supplierEntity.SupplyEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 供应商报价表
 */
@Entity
@Table(name = "sys_cargo")
public class CargoEntity {
    //供应商报价ID
    @Id
    @Column(length = 48)
    @GenericGenerator(name = "myuuid", strategy = "uuid")
    @GeneratedValue(generator = "myuuid")
    private String cargoId;
    // 报价  数据库转decimal类型
    @Column()
    private BigDecimal supplyPrice;
    //供应商供货状态(供应商返回)
    @Column(length = 48)
    private String cargoStatus;
    //投标审核人
    @Column(length = 48)
    private String tenderPeople;
    //投标时间
    @Column(length = 48)
    private String tenderDate;
    //采购审核人
    @Column(length = 48)
    private String purchasePeople;
    //采购时间
    @Column(length = 48)
    private String purchaseDate;
    //供货商ID
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;
    //供货订单货物ID
    @ManyToOne
    @JoinColumn(name = "supply_id")
    private SupplyEntity supplyId;

    public String getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(String cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public SupplyEntity getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(SupplyEntity supplyId) {
        this.supplyId = supplyId;
    }

    public SupplierEntity getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(SupplierEntity supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public String getTenderPeople() {
        return tenderPeople;
    }

    public void setTenderPeople(String tenderPeople) {
        this.tenderPeople = tenderPeople;
    }

    public String getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(String tenderDate) {
        this.tenderDate = tenderDate;
    }

    public String getPurchasePeople() {
        return purchasePeople;
    }

    public void setPurchasePeople(String purchasePeople) {
        this.purchasePeople = purchasePeople;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
