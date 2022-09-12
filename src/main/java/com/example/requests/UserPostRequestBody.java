package com.example.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserPostRequestBody {

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
