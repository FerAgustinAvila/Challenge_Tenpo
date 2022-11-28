package org.ferdev.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.adapter.repository.UserRepository;
import org.ferdev.challengetenpo.dto.auth.SignupRequest;
import org.ferdev.challengetenpo.entity.User;
import org.ferdev.challengetenpo.usecase.SaveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SaveUserImpl implements SaveUser {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public SaveUserImpl(UserRepository userRepository,
                        PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User apply(SignupRequest userIncoming) {

        User user = User.builder()
                .email(userIncoming.getEmail())
                .username(userIncoming.getUsername())
                .password(encoder.encode(userIncoming.getPassword()))
                .build();

        userRepository.save(user);
        return user;
    }
}
