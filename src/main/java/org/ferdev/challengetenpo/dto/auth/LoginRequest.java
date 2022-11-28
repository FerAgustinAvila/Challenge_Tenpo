package org.ferdev.challengetenpo.dto.auth;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Username cannot be empty")
    private String password;
}
