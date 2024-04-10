package isimg.sockets.isimgo_backend.CRUD.user.services;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.enums.InvitationNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.enums.InvitationStatus;
import isimg.sockets.isimgo_backend.CRUD.user.exceptions.UserNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.repo.InvRepo;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvService {
    private final UserRepo userRepository;
    private final InvRepo invRepo;

    //send invitation
    public void sendInvitation(Long senderId, Long receiverId) {
        // Retrieve sender and receiver from the database
        User sender = userRepository.findById(senderId).orElseThrow(() -> new UserNotFoundException(senderId));
        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new UserNotFoundException(receiverId));

        // Create and save the invitation
        var invitation = Invitation.builder()
                .sender(sender)
                .receiver(receiver)
                .status(InvitationStatus.SENT)
                .build();

        invRepo.save(invitation);
    }

    //accept the invitation

    public void acceptInvitation(Long invitationId) {
        Invitation invitation = invRepo.findById(invitationId)
                .orElseThrow(() -> new InvitationNotFoundException(invitationId));
        invitation.setStatus(InvitationStatus.ACCEPTED);
        invRepo.save(invitation);
    }

    //get the list of invitations by reciever and wehere status is sent
    public List<Invitation> getSentInvitationsByReceiver(Long receiverId) {
        // Query the database to fetch sent invitations for the receiver user
        return invRepo.findByReceiverIdAndStatus(receiverId, InvitationStatus.SENT);
    }
    //get the list of invitations by reciever and wehere status is accepted "list d'amis"
    public List<Invitation> getAcceptInvitationsByReceiver(Long receiverId) {
        // Query the database to fetch sent invitations for the receiver user
        return invRepo.findByReceiverIdAndStatus(receiverId, InvitationStatus.ACCEPTED);
    }

    //delete invitation

    public void deleteInvitation(Long invitationId) {
        // Check if the invitation exists
        Invitation invitation = invRepo.findById(invitationId)
                .orElseThrow(() -> new InvitationNotFoundException(invitationId));

        // Delete the invitation
        invRepo.delete(invitation);
    }

}
