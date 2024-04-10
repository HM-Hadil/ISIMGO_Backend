package isimg.sockets.isimgo_backend.CRUD.user.controller;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Invitation;
import isimg.sockets.isimgo_backend.CRUD.user.services.InvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvController {
    private final InvService invService;

    @PostMapping("/invitations/send")
    public ResponseEntity<Void> sendInvitation(@RequestParam Long senderId, @RequestParam Long receiverId) {
        invService.sendInvitation(senderId, receiverId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/PutAcceptinv/{inviId}/")
    public ResponseEntity<Void> acceptInvitation(@PathVariable Long invitationId) {
        invService.acceptInvitation(invitationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/receivedInvSent/{receiverId}")
    public ResponseEntity<List<Invitation>> getReceivedInvitations(@PathVariable Long receiverId) {
        List<Invitation> invitations = invService.getSentInvitationsByReceiver(receiverId);
        return ResponseEntity.ok(invitations);
    }
    @GetMapping("/receivedInvAccepted/{receiverId}")
    public ResponseEntity<List<Invitation>> getAcceptedInvitations(@PathVariable Long receiverId) {
        List<Invitation> invitations = invService.getAcceptInvitationsByReceiver(receiverId);
        return ResponseEntity.ok(invitations);
    }
    @DeleteMapping("/deleteInv/{invitationId}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Long invitationId) {
        invService.deleteInvitation(invitationId);
        return ResponseEntity.ok().build();
    }


}
