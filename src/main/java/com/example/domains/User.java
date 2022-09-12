package com.example.domains;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private UUID id;

    private String username;

    private String password;

    private String authorities; //Ex.: "ROLE_ADMIN,ROLE_USER"

    @Builder.Default
    @Column(name = "isAccountNonExpired")
    boolean isAccountNonExpired = true;

    @Builder.Default
    @Column(name = "isAccountNonLocked")
    boolean isAccountNonLocked = true;

    @Builder.Default
    @Column(name = "isCredentialsNonExpired")
    boolean isCredentialsNonExpired = true;

    @Builder.Default
    @Column(name = "isEnabled")
    boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
