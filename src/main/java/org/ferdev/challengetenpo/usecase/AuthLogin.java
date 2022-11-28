package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.dto.auth.LoginRequest;

@FunctionalInterface
public interface AuthLogin {
    String apply(LoginRequest user);
}
