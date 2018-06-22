package com.dt.controller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class MessageHandler implements WebSocketHandler {

    private static final Map<String, WebSocketSession> users;//标识用户连接

    private static final String USER_KEY = "current_user";

    static {
        users = new ConcurrentHashMap<String, WebSocketSession>();
    }

    //关闭websocket连接时调用
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            users.remove(userId);
        }
    }

    //建立websocket连接时调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            users.put(userId, session);
            session.sendMessage(new TextMessage("建立Socket连接成功！"));
        }
    }

    //客户端调用websocket.send时候，会调用该方法
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        WebSocketMessage message2 = new TextMessage("服务端接收到了您的消息：" + message);
        session.sendMessage(message2);
    }

    //传输异常时
    @Override
    public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    //发送消息给特定用户
    public void sendMessageToUser(String userId, String contents) {
        WebSocketSession session = users.get(userId);
        TextMessage message = new TextMessage(contents);
        System.out.println("-----开始推送===="+contents+"======"+users.get(userId));
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //发送消息给所有用户
    public void sendMessageToAllUsers(String contents) {
        Set<String> userIds = users.keySet();
        for (String userId : userIds) {
            this.sendMessageToUser(userId, contents);
        }
    }

    private String getUserId(WebSocketSession session) {
        try {
            String userId = (String) session.getAttributes().get(USER_KEY);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}