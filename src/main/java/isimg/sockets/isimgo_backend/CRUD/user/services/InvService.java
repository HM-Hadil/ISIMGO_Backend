package isimg.sockets.isimgo_backend.CRUD.user.services;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.exceptions.InvitationNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.enums.InvitationStatus;
import isimg.sockets.isimgo_backend.CRUD.user.exceptions.UserNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.mapper.InvMapper;
import isimg.sockets.isimgo_backend.CRUD.user.repo.InvRepo;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import isimg.sockets.isimgo_backend.CRUD.user.request.InvRequest;
import isimg.sockets.isimgo_backend.CRUD.user.result.InvResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvService {
    private final UserRepo userRepository;
    private final InvRepo invRepo;
    private final InvMapper invMapper;

    //send invitation
    public InvResult sendInvitation(InvRequest request) {
        // Retrieve sender and receiver from the database
        User sender = userRepository.findById(request.getSenderId()).orElseThrow(() -> new UserNotFoundException(request.getSenderId()));
        User receiver = userRepository.findById(request.getRecieverId()).orElseThrow(() -> new UserNotFoundException(request.getRecieverId()));

        // Create and save the invitation
        var invitation = Invitation.builder()
                .sender(sender)
                .receiver(receiver)
                .status(InvitationStatus.SENT)
                .build();


        return invMapper.entityToInv(invRepo.save(invitation));
    }

    //accept the invitation

    public Invitation acceptInvitation(Long invitationId) {
        Invitation invitation = invRepo.findById(invitationId)
                .orElseThrow(() -> new InvitationNotFoundException(invitationId));
        invitation.setStatus(InvitationStatus.ACCEPTED);
              return  invRepo.save(invitation);
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
    //get the list of invitations by sender and wehere status is accepted "list d'amis"
    public List<Invitation> getAcceptInvitationsBySender(Long senderId) {
        // Query the database to fetch sent invitations for the sender user
        return invRepo.findBySenderIdAndStatus(senderId, InvitationStatus.ACCEPTED);
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
