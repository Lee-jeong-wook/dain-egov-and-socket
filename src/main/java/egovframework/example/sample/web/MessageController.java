package egovframework.example.sample.web;

import egovframework.example.sample.service.CCTVMessage;
import egovframework.example.sample.service.DainService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final DainService dainService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/cctv")
    public void sendCctvEvent(Message<CCTVMessage> message) throws Exception {
        CCTVMessage cctvMessage = new CCTVMessage();
        cctvMessage.setCctvId(message.getPayload().getCctvId());
        cctvMessage.setCctvLocname(dainService.getLocWithCCTV(dainService.getCCtv(message.getPayload().getCctvId()).getLocId()).getLocName());
        cctvMessage.setEvent(message.getPayload().getEvent());
        template.convertAndSend("/sub/cctv/" + message.getPayload().getCctvId(), cctvMessage);
    }
}
