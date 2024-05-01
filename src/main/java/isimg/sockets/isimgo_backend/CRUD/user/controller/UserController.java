package isimg.sockets.isimgo_backend.CRUD.user.controller;

import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.result.UserResult;
import isimg.sockets.isimgo_backend.CRUD.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResult> findById( @PathVariable Long id) {
        log.info("Endpoint '.../{id}' (GET) called user- id {}", id);

        var data = userService.findUserById(id);

        log.info("Endpoint '.../{id}' (GET) finished - id {}", id);

        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/getByEmail/{email}")
    public ResponseEntity<Optional<User>> findByEmail(@PathVariable String email) {
        log.info("Endpoint '.../{email}' (GET) called user- email {}", email);

        var data = userService.findUserByemail(email);

        log.info("Endpoint '.../{email}' (GET) finished - email {}", email);

        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }
}
