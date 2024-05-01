package isimg.sockets.isimgo_backend.CRUD.user.services;

import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.mapper.UserMapper;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import isimg.sockets.isimgo_backend.CRUD.user.result.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepo userRepo;


    //get user by id
    public UserResult findUserById(Long id) {
        return userMapper.entityToUser(userRepo.findById(id).orElse(null));
    }
    private final SimpMessageSendingOperations messageTemplate;
    public Optional<User>  findUserByemail(String email){
        return this.userRepo.findByEmail(email);
    }

    // Other methods...

    public void associateUserWithWebSocketSession(User user, WebSocketSession session) {
        session.getAttributes().put("userId", user.getId()); // Assuming user ID is stored in the user object
    }
}

