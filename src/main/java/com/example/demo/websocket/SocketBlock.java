package com.example.demo.websocket;

import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by 八级大狂风 on 2019/4/28.
 */
public class SocketBlock {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new Connection(), "/lsh");
    }
}
