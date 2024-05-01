package isimg.sockets.isimgo_backend.sockets.chat.controller;

import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.exceptions.UserNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.result.UserResult;
import isimg.sockets.isimgo_backend.CRUD.user.services.UserService;
import isimg.sockets.isimgo_backend.sockets.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import java.security.Principal;

@Controller
@RequiredArgsConstructor

public class ChatController {
    @Autowired
    private final UserService userService;
    //when new user connect
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        WebSocketSession session = (WebSocketSession) headerAccessor.getSessionAttributes();
        Long userId = (Long) session.getAttributes().get("userId");
        // Assuming you have a UserService to retrieve user details
        UserResult user = userService.findUserById(userId);
        chatMessage.setSender(user.getFirstname()); // Set sender as the username
        return chatMessage;

    }
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, Principal principal){
        // Check if the user is authenticated
        if (principal == null) {
            System.out.println("user unauthorized");
        }

        // Get the username of the authenticated user
        String sender = principal.getName();

        // Set the sender of the message
        chatMessage.setSender(sender);
        return chatMessage;

    }
}
