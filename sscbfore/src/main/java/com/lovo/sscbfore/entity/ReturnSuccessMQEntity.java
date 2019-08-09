package com.lovo.sscbfore.entity;

/**
 * @author che
 * @title: ReturnSuccessMQEntity
 * @projectName mall
 * @description: 退货成功MQ实体类
 * @date 2019/8/9  9:08
 */
public class ReturnSuccessMQEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 退货单Id
     */
    private String returnId;

    /**
     * 退货消息
     */
    private String returnMessage;

    /**
     * 消息时间
     */
    private String messageTime;

    /**
     * 退货单状态
     */
    private String returnStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}
