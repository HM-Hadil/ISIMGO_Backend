package isimg.sockets.isimgo_backend.CRUD.user.result;

import isimg.sockets.isimgo_backend.CRUD.user.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@ToString
public class UserResult {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String resume;

    private Role role;
}
