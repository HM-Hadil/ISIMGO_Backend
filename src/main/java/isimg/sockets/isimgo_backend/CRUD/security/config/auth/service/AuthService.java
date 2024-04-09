package isimg.sockets.isimgo_backend.CRUD.security.config.auth.service;

import io.jsonwebtoken.Jwt;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.request.AuthenticationRequest;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.request.RegisterRequest;
import isimg.sockets.isimgo_backend.CRUD.security.config.auth.response.AuthenticationResponse;
import isimg.sockets.isimgo_backend.CRUD.security.config.config.JwtService;
import isimg.sockets.isimgo_backend.CRUD.user.Role;
import isimg.sockets.isimgo_backend.CRUD.user.User;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
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
    public AuthenticationResponse register(RegisterRequest req) {
        var user = User.builder()
                .firstanme(req.getFirstanme())
                .lastname(req.getLastname())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .resume(req.getResume())
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
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
