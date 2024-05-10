package isimg.sockets.isimgo_backend.CRUD.security.config.auth.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class AuthenticationResponse {
    //the onely data send back to the user
    private String token;
}
