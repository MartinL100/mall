package com.lovo.csc.vo.suppliervo;

public class TenderVO {
    private String indentId;
    private String cargoId;
    private int supplyNum;

    public TenderVO(String indentId, String cargoId, int supplyNum) {
        this.indentId = indentId;
        this.cargoId = cargoId;
        this.supplyNum = supplyNum;
    }

    public TenderVO() {
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public int getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(int supplyNum) {
        this.supplyNum = supplyNum;
    }
}
