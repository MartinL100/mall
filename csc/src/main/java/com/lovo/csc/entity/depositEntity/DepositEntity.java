package com.lovo.csc.entity.depositEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_deposit")
public class DepositEntity {
    @Id
    @Column(length = 32)
    @GenericGenerator(name = "deposituuid", strategy = "uuid")
    @GeneratedValue(generator = "deposituuid")
    private String depositId; //预存款ID
    @Column(length = 48)
    private String userName;//用户名
    private Double countDepositMoney;//累计预存款金额
    private Double leftDepositMoney;//剩余预存款金额
    @Column(length = 32)
    //等级（0为大众会员--累计金额1万以下，1为黄金会员--累计金额1-5万，2为砖石会员---累计金额5万以上）
    // 大众会员无折扣，黄金会员9折，砖石会员8折
    private String depositMoneyLevel;
    @OneToMany(mappedBy = "deposit")
    private List<DepositInfoEntity> depositInfo;


    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getCountDepositMoney() {
        return countDepositMoney;
    }

    public void setCountDepositMoney(Double countDepositMoney) {
        this.countDepositMoney = countDepositMoney;
    }

    public Double getLeftDepositMoney() {
        return leftDepositMoney;
    }

    public void setLeftDepositMoney(Double leftDepositMoney) {
        this.leftDepositMoney = leftDepositMoney;
    }

    public String getDepositMoneyLevel() {
        return depositMoneyLevel;
    }

    public void setDepositMoneyLevel(String depositMoneyLevel) {
        this.depositMoneyLevel = depositMoneyLevel;
    }
}
