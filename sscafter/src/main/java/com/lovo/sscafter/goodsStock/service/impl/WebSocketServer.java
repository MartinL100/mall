package com.lovo.sscafter.goodsStock.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.lovo.sscafter.util.MqUtil2;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket2")
@Component
public class WebSocketServer {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) throws InterruptedException, IOException {
        this.session = session;
       System.out.println("dahfkjFBkajfbakjfh");

        //去队列中去数据，并推送到前端
//        while(true){
//            String message=   MqUtil2.queue2.take();
//
//            this.sendMessage(message);
//        }
    }
    @OnClose
    public void onClose() {
        //log.info("链接关闭");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        //log.info("收到信息"+message);
        //  this.sendMessage("我是后台");
    }

    /**
     *
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



}
