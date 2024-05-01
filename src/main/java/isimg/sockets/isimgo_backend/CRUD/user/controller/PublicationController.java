package isimg.sockets.isimgo_backend.CRUD.user.controller;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Publications;
import isimg.sockets.isimgo_backend.CRUD.user.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/publications")
@CrossOrigin(origins = "http://localhost:4200")

public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Publications> createPublication(@RequestBody Long userId, @RequestBody String content) {
        Publications createdPublication = publicationService.createPublication(userId, content);
        return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Publications>> getPublicationsByUserId(@PathVariable Long userId) {
        List<Publications> publications = publicationService.getAllPublicationsByUserId(userId);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

}
