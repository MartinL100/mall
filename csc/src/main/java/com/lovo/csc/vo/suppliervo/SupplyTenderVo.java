package com.lovo.csc.vo.suppliervo;

import java.math.BigDecimal;

public class SupplyTenderVo {
    private String cargoId;
    private BigDecimal price;

    public SupplyTenderVo(String cargoId, BigDecimal price) {
        this.cargoId = cargoId;
        this.price = price;
    }

    public SupplyTenderVo() {
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
