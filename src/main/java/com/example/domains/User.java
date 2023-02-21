package com.example.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String authorities; //Ex.: "ROLE_ADMIN,ROLE_USER"

    @Builder.Default
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired = true;

    @Builder.Default
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked = true;

    @Builder.Default
    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired = true;

    @Builder.Default
    @Column(name = "enabled")
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
