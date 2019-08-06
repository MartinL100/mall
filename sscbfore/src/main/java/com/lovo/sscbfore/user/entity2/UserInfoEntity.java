package com.lovo.sscbfore.user.entity2;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="sys_info")
public class UserInfoEntity {
    @Id
    @GenericGenerator(name = "messageUuid", strategy = "uuid")
    @GeneratedValue(generator = "messageUuid")
    @Column(name="message_id",length = 32)
    /**消息ID*/
    private String messageId;
    @Column(name="message_info", columnDefinition="TEXT")
    /**消息正文*/
    private String messageInfo;
    @Column(name="message_date",length = 32)
    /**消息时间*/
    private String messageDate;
    @Column(name="message_status")
    /**消息状态*/
    private int messageStatus=0;
    @ManyToOne
    @JoinColumn(name="user_name")
    /**用户消息对象*/
    private UserEntity userInfo;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public UserEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity userInfo) {
        this.userInfo = userInfo;
    }
}
