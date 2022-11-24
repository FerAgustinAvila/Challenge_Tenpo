package org.ferdev.challengetenpo.challengetenpo.usecase;

import org.ferdev.challengetenpo.challengetenpo.dto.auth.LoginRequest;

@FunctionalInterface
public interface AuthLogin {
    String apply(LoginRequest user);
}
