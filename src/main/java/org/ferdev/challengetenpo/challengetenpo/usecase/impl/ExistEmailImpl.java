package org.ferdev.challengetenpo.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.challengetenpo.adapter.repository.UserRepository;
import org.ferdev.challengetenpo.challengetenpo.usecase.ExistEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistEmailImpl implements ExistEmail {

    private final UserRepository userRepository;

    @Autowired
    public ExistEmailImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean apply(String email) {
        return userRepository.existsByEmail(email);
    }
}
