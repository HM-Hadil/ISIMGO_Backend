package isimg.sockets.isimgo_backend.CRUD.user.result;

import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.enums.InvitationStatus;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder(setterPrefix = "with")
@Getter
@Setter
@Jacksonized
@ToString
public class InvResult {
    private Long id;
    private UserResult sender;
    private UserResult reciever;
    private InvitationStatus status;

}
