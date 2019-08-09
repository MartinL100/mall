package com.lovo.csc.vo.suppliervo;

import java.math.BigDecimal;

public class SupplyTenderVo {
    private String cargoId;
    private BigDecimal supplyPrice;

    public SupplyTenderVo(String cargoId, BigDecimal supplyPrice) {
        this.cargoId = cargoId;
        this.supplyPrice = supplyPrice;
    }

    public SupplyTenderVo() {
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
}
