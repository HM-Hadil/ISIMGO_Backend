package isimg.sockets.isimgo_backend.CRUD.user.services;

import isimg.sockets.isimgo_backend.CRUD.user.mapper.UserMapper;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import isimg.sockets.isimgo_backend.CRUD.user.result.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
