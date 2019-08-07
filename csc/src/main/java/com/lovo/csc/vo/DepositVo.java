package com.lovo.csc.vo;

public class DepositVo {
    private  String  makeTime;//月份
    private  Double  countMoney;//当月使用总金额

    public String getMakeTime() {
        return makeTime;
    }

    public DepositVo() {
        super();
    }

    public DepositVo(String makeTime, Double countMoney) {
        this.makeTime = makeTime;
        this.countMoney = countMoney;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public Double getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(Double countMoney) {
        this.countMoney = countMoney;
    }

    @Override
    public String toString() {
        return "DepositVo{" +
                "makeTime='" + makeTime + '\'' +
                ", countMoney=" + countMoney +
                '}';
    }
}
