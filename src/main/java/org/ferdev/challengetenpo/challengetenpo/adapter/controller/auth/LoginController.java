package org.ferdev.challengetenpo.challengetenpo.adapter.controller.auth;

import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.challengetenpo.dto.MessageResponse;
import org.ferdev.challengetenpo.challengetenpo.dto.auth.LoginRequest;
import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.challengetenpo.usecase.AuthLogin;
import org.ferdev.challengetenpo.challengetenpo.usecase.ExistUsername;
import org.ferdev.challengetenpo.challengetenpo.util.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    public static final String AUTH_LOGIN = "/auth/login";
    private final AuthLogin authLogin;
    private final ExistUsername existUsername;
    private final EventPublisher eventPublisher;

    @Autowired
    public LoginController(AuthLogin authLogin,
                           ExistUsername existUsername,
                           @Qualifier("AsyncEventPublisher") EventPublisher eventPublisher) {
        this.authLogin = authLogin;
        this.existUsername = existUsername;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest userIncoming) {

        if (!existUsername.apply(userIncoming.getUsername())) {
            eventPublisher.publishEvent(
                    EndpointHistory.builder()
                            .endpoint("/auth/login")
                            .codeResponse(404)
                            .build());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: User not found!"));
        }

        String jwt = authLogin.apply(userIncoming);
        MessageResponse messageResponse = MessageResponse.builder().message(String.format("JWT: %s", jwt)).build();

        eventPublisher.publishEvent(
                EndpointHistory.builder()
                        .endpoint(AUTH_LOGIN)
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                        .codeResponse(200)
                        .response(messageResponse.getMessage())
                        .build());

        return ResponseEntity.ok().body(messageResponse);
    }
}
