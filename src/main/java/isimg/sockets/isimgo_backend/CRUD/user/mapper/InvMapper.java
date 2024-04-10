package isimg.sockets.isimgo_backend.CRUD.user.mapper;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.result.InvResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InvMapper {

    public InvResult entityToInv(Invitation invitation){
        if (invitation == null) {
            return null;
        }
        return InvResult.builder()
                .withId(invitation.getId())
                .withReciever(invitation.getReceiver())
                .withSender(invitation.getSender())
                .withStatus(invitation.getStatus())
                .build();
    }
}
