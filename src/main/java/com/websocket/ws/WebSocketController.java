package com.websocket.ws;

import com.websocket.ws.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final WebSocketService webSocketService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message) {
        webSocketService.notifyFrontend(message.getMessageContent());
    }

}
