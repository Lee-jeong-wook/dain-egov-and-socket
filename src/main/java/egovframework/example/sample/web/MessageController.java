package egovframework.example.sample.web;

import egovframework.example.sample.service.DainService;
import egovframework.example.sample.service.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final DainService dainService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/pub/cctv")
    public void sendCctvEvent(Message message){
        template.convertAndSend("/sub/cctv/" + message.getCctvId(), message);
    }
}
