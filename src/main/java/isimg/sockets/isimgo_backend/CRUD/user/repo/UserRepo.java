package isimg.sockets.isimgo_backend.CRUD.user.repo;

import isimg.sockets.isimgo_backend.CRUD.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
