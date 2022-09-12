package com.example.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserPutRequestBody {

    private UUID id;

    private String username;

    private String password;

    private String authorities; //Ex.: "ROLE_ADMIN,ROLE_USER"

    @Builder.Default
    boolean isAccountNonExpired = true;

    @Builder.Default
    boolean isAccountNonLocked = true;

    @Builder.Default
    boolean isCredentialsNonExpired = true;

    @Builder.Default
    boolean isEnabled = true;

}
