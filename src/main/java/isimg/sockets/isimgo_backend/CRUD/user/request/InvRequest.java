package isimg.sockets.isimgo_backend.CRUD.user.request;

import isimg.sockets.isimgo_backend.CRUD.user.enums.InvitationStatus;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

@SuperBuilder
@Data
@Jacksonized
@Validated
@ToString
public class InvRequest {
    private final Long senderId;
    private final Long recieverId;
    private InvitationStatus status;

}
