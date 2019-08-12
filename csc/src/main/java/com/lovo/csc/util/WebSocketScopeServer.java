package com.lovo.csc.util;


import com.lovo.csc.dto.salesReturndto.ReturnGoodsDto;
import com.lovo.csc.dto.salesReturndto.ScopeOrderDto;
import com.lovo.csc.vo.clientvo.ResgisterMessageVo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@ServerEndpoint(value = "/websocketScope")
@Component
public class WebSocketScopeServer {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public  static BlockingQueue<ReturnGoodsDto> blockingQueue =new LinkedBlockingQueue(100);

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }
    @OnClose
    public void onClose() {

    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
   @OnMessage
    public void onMessage(String message, Session session) throws IOException {
       this.sendMessage(message);
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


    public void getMQMessage() throws JMSException, IOException {
        boolean bl = true;
        ScopeOrderDto dto=null;
        while(bl) {
            try {
               blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null!=dto){
                sendMessage("您有新审核信息");
            }
        }
    }


}
