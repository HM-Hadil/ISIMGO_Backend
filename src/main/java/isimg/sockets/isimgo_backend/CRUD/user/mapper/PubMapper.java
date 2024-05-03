package isimg.sockets.isimgo_backend.CRUD.user.mapper;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.entity.Publications;
import isimg.sockets.isimgo_backend.CRUD.user.result.InvResult;
import isimg.sockets.isimgo_backend.CRUD.user.result.PubResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component

public class PubMapper {

    private final UserMapper userMapper;

    public PubResult entityToPub(Publications publications) {
        if (publications == null) {
            return null;
        }
        return PubResult.builder()
                .withId(publications.getId())
                .withUserpub(userMapper.entityToUser(publications.getUser()))
                .withContent(publications.getContent())
                .build();



    }
}