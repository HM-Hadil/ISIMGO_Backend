package isimg.sockets.isimgo_backend.sockets.config;

import isimg.sockets.isimgo_backend.sockets.chat.ChatMessage;
import isimg.sockets.isimgo_backend.sockets.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
//for logging info when users leaves the chat
@Slf4j
public class WSEventListener {
    private final  SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handelWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username !=null){
            log.info("User disconnectd : {} ",username);
            var chatmessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public",chatmessage);
        }


    }
}
