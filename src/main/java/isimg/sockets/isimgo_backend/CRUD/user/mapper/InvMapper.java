package isimg.sockets.isimgo_backend.CRUD.user.mapper;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.result.InvResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InvMapper {
    private final UserMapper userMapper;

    public InvResult entityToInv(Invitation invitation){
        if (invitation == null) {
            return null;
        }
        return InvResult.builder()
                .withId(invitation.getId())
                .withReciever(userMapper.entityToUser(invitation.getReceiver()))
                .withSender(userMapper.entityToUser(invitation.getSender()))
                .withStatus(invitation.getStatus())
                .build();
    }
}
