package isimg.sockets.isimgo_backend.CRUD.security.config;

import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepo.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found !"));
    }
}
