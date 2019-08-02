package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 退货订单实体
 */
@Entity
@Table(name="sys_returnorder")
public class ReturnOrderEntity {
    //退货订单号
    @Id
    private String returnOrderId;
    //退货订单状态（1：等待退货处理 2：已经收货  3：退款完成）
    private int returnStatus;
    //退货时间
    private String returnTime;

    //订单编号
    private String oderNum;

    //商品集合
    @OneToMany(mappedBy = "returnOderId",fetch=FetchType.EAGER)
    private List<ReturnGoodsEntity> listGoods;

    public String getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(String returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public List<ReturnGoodsEntity> getListGoods() {
        return listGoods;
    }

    public void setListGoods(List<ReturnGoodsEntity> listGoods) {
        this.listGoods = listGoods;
    }

    public String getOderNum() {
        return oderNum;
    }

    public void setOderNum(String oderNum) {
        this.oderNum = oderNum;
    }
}
