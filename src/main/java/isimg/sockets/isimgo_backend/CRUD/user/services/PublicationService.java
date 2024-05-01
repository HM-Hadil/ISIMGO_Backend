package isimg.sockets.isimgo_backend.CRUD.user.services;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Publications;
import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.exceptions.UserNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.repo.PublicationRepo;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final UserRepo userRepository;
    private final PublicationRepo publicationRepo;

    public Publications createPublication(Long userId, String content) {
        // Find the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId ));

        // Create a new publication
        Publications publication = new Publications();
        publication.setContent(content);
        publication.setCreatedAt(LocalDateTime.now());
        publication.setUser(user);

        // Save the publication
        return publicationRepo.save(publication);
    }

    public List<Publications> getAllPublicationsByUserId(Long userId) {
        // Find publications by user id
        return publicationRepo.findAllById(Collections.singleton(userId));
    }
}
