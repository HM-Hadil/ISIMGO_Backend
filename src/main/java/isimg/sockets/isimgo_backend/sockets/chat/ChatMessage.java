package isimg.sockets.isimgo_backend.sockets.chat;

import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}
