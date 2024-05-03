package isimg.sockets.isimgo_backend.CRUD.user.controller;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Publications;
import isimg.sockets.isimgo_backend.CRUD.user.request.PublicationRequest;
import isimg.sockets.isimgo_backend.CRUD.user.result.PubResult;
import isimg.sockets.isimgo_backend.CRUD.user.services.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/publications")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j

public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<PubResult> createPublication(@RequestBody PublicationRequest publicationRequest) {
        var response =publicationService.createPublication(publicationRequest);
        log.info("Endpoint '/appointments' (POST) called - request {}", publicationRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Publications>> getPublicationsByUserId(@PathVariable Long userId) {
        List<Publications> publications = publicationService.getAllPublicationsByUserId(userId);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

}
