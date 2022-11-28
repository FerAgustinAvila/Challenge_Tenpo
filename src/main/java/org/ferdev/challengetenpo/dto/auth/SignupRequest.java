package org.ferdev.challengetenpo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be 3-20 characters in length. Must be a string. Use double quotes")
    @Pattern(regexp = "[a-zA-Z]+", message = "Only letters")
    private String username;

    @NotBlank(message = "Username cannot be empty")
    @Size(max = 50, message = "Email cannot have more than 50 characters in length. Must be an email. Use double quotes")
    @Email(message = "Must be an email.")
    private String email;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 6, max = 40, message = "Password must be 3-20 characters in length. Must be a string. Use double quotes")
    private String password;
}
