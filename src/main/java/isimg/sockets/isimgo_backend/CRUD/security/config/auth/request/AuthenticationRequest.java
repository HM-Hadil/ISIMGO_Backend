package isimg.sockets.isimgo_backend.CRUD.security.config.auth.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class AuthenticationRequest {
    private String email;
    private String password;
}
