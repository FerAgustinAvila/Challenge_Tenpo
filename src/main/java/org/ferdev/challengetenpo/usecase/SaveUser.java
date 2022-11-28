package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.dto.auth.SignupRequest;
import org.ferdev.challengetenpo.entity.User;

@FunctionalInterface
public interface SaveUser {
    User apply(SignupRequest user);
}
