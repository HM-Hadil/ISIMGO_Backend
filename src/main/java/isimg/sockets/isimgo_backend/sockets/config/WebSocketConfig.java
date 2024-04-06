package isimg.sockets.isimgo_backend.sockets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //STOMP : Simple Text Oriented Messaging Protocol
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //ws : web socket
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    //to add application destination prefixe
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
