package com.lovo.csc.entity.depositEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sys_deposit_info")
public class DepositInfoEntity {
    @Id
    @Column(length = 32)
    @GenericGenerator(name = "infouuid", strategy = "uuid")
    @GeneratedValue(generator = "infouuid")
    private String infoId;//明细ID
    private Double makeMoney;//操作金额
    private int infoTag;//操作标记（0--表示存款，1--表示消费，2--表示退款）
    @Column(length = 32)
    private String infoTime;//操作时间
    private String makeTime;//按月查询标记
    @ManyToOne
    @JoinColumn(name = "depositId")
    private DepositEntity deposit;

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public DepositEntity getDeposit() {
        return deposit;
    }

    public void setDeposit(DepositEntity deposit) {
        this.deposit = deposit;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }

    public int getInfoTag() {
        return infoTag;
    }

    public void setInfoTag(int infoTag) {
        this.infoTag = infoTag;
    }

    public String getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(String infoTime) {
        this.infoTime = infoTime;
    }
}
