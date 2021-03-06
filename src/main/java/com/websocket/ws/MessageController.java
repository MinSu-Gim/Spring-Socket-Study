package com.websocket.ws;

import com.websocket.ws.dto.Message;
import com.websocket.ws.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;


@Slf4j
@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) {

        log.info("받은 메시지: " + message.getMessageContent());
        return new ResponseMessage(message.getMessageContent());
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message, final Principal principal) {

        return new ResponseMessage("Sending private message to user " + principal.getName()
                + ": " + message.getMessageContent());
    }
}
