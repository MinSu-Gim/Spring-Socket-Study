package com.websocket.ws;

import com.websocket.ws.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyFrontend(final String message) {

        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {

        ResponseMessage response = new ResponseMessage(message);
        log.info("notify User id: " + id);
        log.info("notify User message: " + message);
        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }
}
