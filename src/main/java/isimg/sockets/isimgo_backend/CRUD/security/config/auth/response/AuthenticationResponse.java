package isimg.sockets.isimgo_backend.CRUD.security.config.auth.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    //the onely data send back to the user
    private String token;
}
