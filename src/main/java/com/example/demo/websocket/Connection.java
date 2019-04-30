package com.example.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * Created by 八级大狂风 on 2019/4/28.
 */
@Component
public class Connection extends TextWebSocketHandler{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        String payload = message.getPayload();
        logger.info("receive msg: {}", payload);
        session.sendMessage(new TextMessage("hi websocket"));
    }
}
