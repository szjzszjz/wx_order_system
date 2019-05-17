package com.szjz.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author szjz
 * @date 2019/5/17 12:49
 */

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    public Session session;

    private static CopyOnWriteArrayList<WebSocket> webSockets = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【websocket消息】 有新的连接 总数:{}", webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        log.info("【websocket消息】 连接断开 总数:{}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】 收到客户端的消息:{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket websocket : webSockets) {
            log.info("【websocket消息】 广播消息 message={}", message);
            try {
                websocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
