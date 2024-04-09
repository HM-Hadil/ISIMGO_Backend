package isimg.sockets.isimgo_backend.CRUD.user.repo;

import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
