package isimg.sockets.isimgo_backend.CRUD.security.config.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    //the onely data send back to the user
    private String token;
}
