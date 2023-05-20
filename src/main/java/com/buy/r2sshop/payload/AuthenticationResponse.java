package com.buy.r2sshop.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;

    public void setUsername(String username) {
    }

    public void setRoles(Set<String> roles) {
    }
}
