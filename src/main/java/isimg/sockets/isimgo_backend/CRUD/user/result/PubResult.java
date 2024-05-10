package isimg.sockets.isimgo_backend.CRUD.user.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Builder(setterPrefix = "with")
@Getter
@Setter

@ToString
public class PubResult {
    private Long id;
    private String content;
    private UserResult userpub;
    private Date cretedOn;


}
