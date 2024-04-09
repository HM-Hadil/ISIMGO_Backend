package isimg.sockets.isimgo_backend.CRUD.user.mapper;

import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.result.UserResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserMapper {

    public UserResult entityToUser(User user) {
        if (user == null) {
            return null;
        }

        return UserResult.builder()
                .withId(user.getId())
                .withFirstname(user.getFirstname())
                .withLastname(user.getLastname())
                .withEmail(user.getEmail())
                .withResume(user.getResume())
                .withRole(user.getRole())
                .build();

    }
}
