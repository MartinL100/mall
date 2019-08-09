package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.controller;

import com.lovo.sscafter.util.MqUtil5;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket5")
@Component
public class Websocket5 {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws InterruptedException, IOException {
        this.session = session;

        //去队列中取数据，并推送到前端
        while (true) {
            String message = MqUtil5.queue5.take();
            sendMessage(message);
        }
    }

    @OnClose
    public void onClose() {
        //log.info("链接关闭");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {

     //  session.getBasicRemote().sendText(message);：这样写就不用sendMessage了
        //调用传入页面的方法
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
        //主动推送
        this.session.getBasicRemote().sendText(message);
    }
}

