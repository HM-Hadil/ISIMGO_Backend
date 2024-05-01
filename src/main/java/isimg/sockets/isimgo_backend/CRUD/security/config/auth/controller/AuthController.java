package isimg.sockets.isimgo_backend.CRUD.security.config.auth.controller;

import isimg.sockets.isimgo_backend.CRUD.security.config.auth.request.AuthenticationRequest;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.response.AuthenticationResponse;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.request.RegisterRequest;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:4200")

//automatically generates a constructor that initializes all final fields.
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest req)  {
        return ResponseEntity.ok(authService.register(req));

    }
    @PostMapping("/athenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest req){
        return ResponseEntity.ok(authService.authenticate(req));

    }
}
