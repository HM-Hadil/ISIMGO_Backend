package isimg.sockets.isimgo_backend.CRUD.user.request;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
@SuperBuilder
@Data
@Jacksonized
@Validated
@ToString
public class PublicationRequest {
    private Long id;
    private Long userId;
    private String content;
    private Date cretedOn;
}
