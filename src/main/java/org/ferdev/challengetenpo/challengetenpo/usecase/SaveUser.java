package org.ferdev.challengetenpo.challengetenpo.usecase;

import org.ferdev.challengetenpo.challengetenpo.dto.auth.SignupRequest;
import org.ferdev.challengetenpo.challengetenpo.entity.User;

@FunctionalInterface
public interface SaveUser {
    User apply(SignupRequest user);
}
