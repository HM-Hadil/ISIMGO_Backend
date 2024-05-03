package isimg.sockets.isimgo_backend.CRUD.user.services;

import isimg.sockets.isimgo_backend.CRUD.user.entity.Publications;
import isimg.sockets.isimgo_backend.CRUD.user.entity.User;
import isimg.sockets.isimgo_backend.CRUD.user.exceptions.UserNotFoundException;
import isimg.sockets.isimgo_backend.CRUD.user.mapper.PubMapper;
import isimg.sockets.isimgo_backend.CRUD.user.repo.PublicationRepo;
import isimg.sockets.isimgo_backend.CRUD.user.repo.UserRepo;
import isimg.sockets.isimgo_backend.CRUD.user.request.PublicationRequest;
import isimg.sockets.isimgo_backend.CRUD.user.result.PubResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final UserRepo userRepository;
    private final PubMapper pubMapper;
    private final PublicationRepo publicationRepo;

    public PubResult createPublication(PublicationRequest req) {
        // Find the user by userId
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new UserNotFoundException(req.getUserId() ));

        var pub = Publications.builder()
                .id(req.getId())
                .content(req.getContent())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();


        // Save the puserublication
        return pubMapper.entityToPub(publicationRepo.save(pub));
    }

    public List<Publications> getAllPublicationsByUserId(Long userId) {
        // Find publications by user id
        return publicationRepo.findPublicationsByUserId(userId);
    }
}
