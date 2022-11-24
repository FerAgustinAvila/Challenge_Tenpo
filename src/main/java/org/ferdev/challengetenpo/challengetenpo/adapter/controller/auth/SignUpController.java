package org.ferdev.challengetenpo.challengetenpo.adapter.controller.auth;

import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.challengetenpo.dto.MessageResponse;
import org.ferdev.challengetenpo.challengetenpo.dto.UserDto;
import org.ferdev.challengetenpo.challengetenpo.dto.auth.SignupRequest;
import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.challengetenpo.mapper.UserMapper;
import org.ferdev.challengetenpo.challengetenpo.usecase.ExistEmail;
import org.ferdev.challengetenpo.challengetenpo.usecase.ExistUsername;
import org.ferdev.challengetenpo.challengetenpo.usecase.SaveUser;
import org.ferdev.challengetenpo.challengetenpo.util.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SignUpController {

    public static final String AUTH_SIGNUP = "/auth/signup";

    private final ExistUsername existUsername;
    private final ExistEmail existEmail;
    private final SaveUser saveUser;
    private final UserMapper userMapper;
    private final EventPublisher eventPublisher;

    @Autowired
    public SignUpController(ExistUsername existUsername,
                            ExistEmail existEmail,
                            SaveUser saveUser,
                            UserMapper userMapper,
                            @Qualifier("AsyncEventPublisher") EventPublisher eventPublisher) {
        this.existUsername = existUsername;
        this.existEmail = existEmail;
        this.saveUser = saveUser;
        this.userMapper = userMapper;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest userIncoming) {

        if (existUsername.apply(userIncoming.getUsername())) {
            eventPublisher.publishEvent(
                EndpointHistory.builder()
                        .endpoint(AUTH_SIGNUP)
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                        .codeResponse(409)
                        .build());
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User already exists!"));
        }

        if (existEmail.apply(userIncoming.getEmail())) {
            eventPublisher.publishEvent(
                    EndpointHistory.builder()
                            .endpoint(AUTH_SIGNUP)
                            .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                            .codeResponse(409)
                            .build());
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already exists!"));
        }

        UserDto userSaved = userMapper.map(saveUser.apply(userIncoming));

        MessageResponse messageResponse = MessageResponse.builder()
                .message(String.format("The user %s has been saved successfully!", userSaved.getUsername()))
                .build();

        eventPublisher.publishEvent(
                EndpointHistory.builder()
                        .endpoint(AUTH_SIGNUP)
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                        .codeResponse(200)
                        .response(messageResponse.getMessage())
                        .build());
        return ResponseEntity.ok().body(messageResponse);
    }
}
