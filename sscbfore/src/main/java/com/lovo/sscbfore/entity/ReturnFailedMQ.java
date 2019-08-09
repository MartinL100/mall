package com.lovo.sscbfore.entity;

/**
 * @author che
 * @title: ReturnFailedMQ
 * @projectName mall
 * @description: 退货失败MQ实体类
 * @date 2019/8/9  9:24
 */
public class ReturnFailedMQ {

    /**
     * 退货单号
     */
    private String returnId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 退货信息
     */
    private String returnInfo;

    /**
     * 消息时间
     */
    private String messageTime;

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
