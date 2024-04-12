package isimg.sockets.isimgo_backend.CRUD.user.controller;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.request.InvRequest;
import isimg.sockets.isimgo_backend.CRUD.user.result.InvResult;
import isimg.sockets.isimgo_backend.CRUD.user.services.InvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/inv")
public class InvController {
    private final InvService invService;

    //send invitation
    @PostMapping("/invitations/send")
    public ResponseEntity<InvResult> sendInvitation(@RequestBody InvRequest invRequest) {
        var response =invService.sendInvitation(invRequest);
        log.info("Endpoint '/appointments' (POST) called - request {}", invRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //accepet the invitation
    @PutMapping("/PutAcceptinv/{invitationId}")
    public ResponseEntity<Invitation> acceptInvitation(@PathVariable Long invitationId) {
       var response= invService.acceptInvitation(invitationId);
        log.info("Endpoint '.../{id inv}' (PUT) called - id inv {}, account {}", invitationId);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // get the invitation of reciever by status sent "liste d'invitation"
    @GetMapping("/recievedInvSent/{recieverId}")
    public ResponseEntity<List<Invitation>> getReceivedInvitations(@PathVariable Long recieverId) {
        List<Invitation> invitations = invService.getSentInvitationsByReceiver(recieverId);
        return new ResponseEntity<>(invitations,HttpStatus.OK);
    }

    //get the liste of invitation by status accepted "liste d'amis"
    @GetMapping("/recievedInvAccepted/{recieverId}")
    public ResponseEntity<List<Invitation>> getAcceptedInvitationsReciever(@PathVariable Long recieverId) {
        List<Invitation> invitations = invService.getAcceptInvitationsByReceiver(recieverId);
        return new ResponseEntity<>(invitations,HttpStatus.OK);
    }

    //get the liste of invitation sendder by status accepted "liste d'amis"
    @GetMapping("/senderInvAccepted/{senderId}")
    public ResponseEntity<List<Invitation>> getAcceptedInvitationsSender(@PathVariable Long senderId) {
        List<Invitation> invitations = invService.getAcceptInvitationsBySender(senderId);
        return new ResponseEntity<>(invitations,HttpStatus.OK);
    }
    @DeleteMapping("/deleteInv/{invitationId}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Long invitationId) {
        invService.deleteInvitation(invitationId);
        return ResponseEntity.ok().build();
    }


}
