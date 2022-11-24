package org.ferdev.challengetenpo.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.challengetenpo.adapter.repository.UserRepository;
import org.ferdev.challengetenpo.challengetenpo.usecase.ExistUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistUsernameImpl implements ExistUsername {

    private final UserRepository userRepository;

    @Autowired
    public ExistUsernameImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean apply(String username) {
        return userRepository.existsByUsername(username);
    }
}
