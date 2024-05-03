package isimg.sockets.isimgo_backend.CRUD.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

    @Entity
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Publications {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String content;

        @Column(nullable = false)
        private LocalDateTime createdAt;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        @JsonBackReference

        private User user;



}
