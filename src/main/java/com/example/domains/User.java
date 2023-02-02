package com.example.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private UUID id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String authorities; //Ex.: "ROLE_ADMIN,ROLE_USER"

    @Builder.Default
    @Column(name = "isAccountNonExpired")
    private boolean isAccountNonExpired = true;

    @Builder.Default
    @Column(name = "isAccountNonLocked")
    private boolean isAccountNonLocked = true;

    @Builder.Default
    @Column(name = "isCredentialsNonExpired")
    private boolean isCredentialsNonExpired = true;

    @Builder.Default
    @Column(name = "isEnabled")
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
