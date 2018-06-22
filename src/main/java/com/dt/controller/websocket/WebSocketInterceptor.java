package com.dt.controller.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    private static final String USER_KEY = "current_user";

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception e) {
        super.afterHandshake(request, response, handler, e);
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
                                   Map<String, Object> attrs) throws Exception {

        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest)request;
            //获取参数
            String user = serverHttpRequest .getServletRequest().getParameter("user");
            System.out.println("获取到的User："+user);
            attrs.put(USER_KEY, user);
        }
        return true;
    }

}