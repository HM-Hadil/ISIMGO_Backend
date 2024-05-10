package isimg.sockets.isimgo_backend.CRUD.security.config.auth.service;

import isimg.sockets.isimgo_backend.CRUD.user.exceptions.EmailAlreadyExistsException;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.request.AuthenticationRequest;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.request.RegisterRequest;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.response.AuthenticationResponse;
import isimg.sockets.isimgo_backend.CRUD.security.config.config.JwtService;
import isimg.sockets.isimgo_backend.CRUD.user.enums.Role;
import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import isimg.sockets.isimgo_backend.CRUD.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public AuthenticationResponse register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }
        var user = User.builder()
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .resume(req.getResume())
                .isFriend(false)
                .role(Role.USER)
                .build();

        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        // After registering the user, associate user details with WebSocket session

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    //security when no user  authenticated
    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        //so if the both of username and password correcte , generate a token
        var user=userRepo.findByEmail(req.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
