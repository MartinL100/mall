package com.lovo.csc.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.controller.clientcontroller.AccountsController;
import com.lovo.csc.controller.clientcontroller.UserAuditController;
import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.entity.SysUserAuditInformationEntity;
import com.lovo.csc.service.clientService.IUserAuditService;
import com.lovo.csc.vo.clientvo.PreserveMessageVo;
import com.lovo.csc.vo.clientvo.ResgisterMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@ServerEndpoint(value = "/websocketTwo")
@Component
public class WebSocketServerTwo {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    @Autowired
    private JmsTemplate JmsTemplate;
    @Autowired
    private IUserAuditService userAuditService;
    public  static BlockingQueue<PreserveMessageVo> blockingQueue =new LinkedBlockingQueue(100);

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
          //  getMQMessage();

    }

    @OnClose
    public void onClose() {

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        this.sendMessage(message);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public void getMQMessage()  {
        boolean bl = true;
        PreserveMessageVo vo = null;
        while (bl) {
            try {
             blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != vo) {
                try {
                    sendMessage("您有新的账号"+vo.getAuditType()+"审核信息");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}