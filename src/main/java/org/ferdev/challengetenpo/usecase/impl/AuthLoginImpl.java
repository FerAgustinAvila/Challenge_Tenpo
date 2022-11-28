package org.ferdev.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.Constants;
import org.ferdev.challengetenpo.dto.MessageResponse;
import org.ferdev.challengetenpo.dto.auth.LoginRequest;
import org.ferdev.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.exception.AppErrorException;
import org.ferdev.challengetenpo.service.impl.UserDetailsImpl;
import org.ferdev.challengetenpo.usecase.AuthLogin;
import org.ferdev.challengetenpo.util.event.EventPublisher;
import org.ferdev.challengetenpo.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AuthLoginImpl implements AuthLogin {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final EventPublisher eventPublisher;

    @Autowired
    public AuthLoginImpl(AuthenticationManager authenticationManager,
                         JwtUtil jwtUtil,
                         @Qualifier("AsyncEventPublisher") EventPublisher eventPublisher) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String apply(LoginRequest user) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            eventPublisher.publishEvent(
                    EndpointHistory.builder()
                            .endpoint("/auth/login")
                            .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                            .codeResponse(401)
                            .build());

            MessageResponse messageResponse = MessageResponse.builder().message(Constants.PASSWORD_INCORRECT).build();
            throw new AppErrorException(
                    HttpStatus.UNAUTHORIZED,
                    messageResponse.toString(),
                    e
            );
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return jwtUtil.generateJwtCookie(userDetails);
    }
}
