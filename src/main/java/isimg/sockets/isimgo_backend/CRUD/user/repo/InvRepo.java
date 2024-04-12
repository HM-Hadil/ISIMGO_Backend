package isimg.sockets.isimgo_backend.CRUD.user.repo;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.enums.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InvRepo extends JpaRepository<Invitation,Long> {

    List<Invitation> findByReceiverIdAndStatus(Long receiver, InvitationStatus status);
    List<Invitation> findBySenderIdAndStatus(Long sender, InvitationStatus status);


}
