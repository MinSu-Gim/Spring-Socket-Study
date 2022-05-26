package com.websocket.ws;

import com.websocket.ws.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    private final WebSocketService webSocketService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message) {
        webSocketService.notifyFrontend(message.getMessageContent());
    }
    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable String id, @RequestBody Message message) {
        log.info("받은 id: " + id);
        log.info("받은 msg: " + message.getMessageContent());
        webSocketService.notifyUser(id, message.getMessageContent());
    }
}
