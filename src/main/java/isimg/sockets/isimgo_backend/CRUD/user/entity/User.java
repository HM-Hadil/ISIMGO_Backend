package isimg.sockets.isimgo_backend.CRUD.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import isimg.sockets.isimgo_backend.CRUD.user.core.EntityWithSelfAssignedId;
import isimg.sockets.isimgo_backend.CRUD.user.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class User implements   UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String resume;
    private Boolean isFriend;
    @OneToMany
    private List<Invitation> invitations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<Publications> publications;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    //return a list of roles
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
