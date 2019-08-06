package com.lovo.sscbfore.entity;

import java.util.List;

/**
 * @author che
 * @title: ReturnEntity
 * @projectName mall
 * @description: 退货单实体类
 * @date 2019/8/6  15:24
 */
public class ReturnEntity {

    /**
     * 退货单id
     */
    private String returnId;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 退货日期
     */
    private String returnDate;

    /**
     * 退货单状态
     */
    private int returnStatus = 1;

    /**
     * 退货商品集合
     */
    private List<ReturnGoodsEntity> returnGoodsList;

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    public List<ReturnGoodsEntity> getReturnGoodsList() {
        return returnGoodsList;
    }

    public void setReturnGoodsList(List<ReturnGoodsEntity> returnGoodsList) {
        this.returnGoodsList = returnGoodsList;
    }
}