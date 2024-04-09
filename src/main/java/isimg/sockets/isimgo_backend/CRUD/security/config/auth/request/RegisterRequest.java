package isimg.sockets.isimgo_backend.CRUD.security.config.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstanme;
    private String lastname;
    private String email;
    private String password;
    private String resume;
}
