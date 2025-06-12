package egovframework.example.sample.web;

import egovframework.example.sample.service.CCTVMessage;
import egovframework.example.sample.service.DainService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final DainService dainService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/cctv")
    public void sendCctvEvent(Message<CCTVMessage> message){
        System.out.println(message.getPayload().getCctvId());
        template.convertAndSend("/sub/cctv/" + message.getPayload().getCctvId(), message.getPayload());
    }
}
