package org.ferdev.challengetenpo.exception.impl;

import org.ferdev.challengetenpo.Constants;
import org.ferdev.challengetenpo.dto.MessageResponse;
import org.ferdev.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.exception.UserNotFoundHandler;
import org.ferdev.challengetenpo.util.ConvertObjectToJson;
import org.ferdev.challengetenpo.util.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserNotFoundHandlerImpl implements UserNotFoundHandler {

    private final EventPublisher eventPublisher;
    private final ConvertObjectToJson convertObjectToJson;

    @Autowired
    public UserNotFoundHandlerImpl(
            @Qualifier("AsyncEventPublisher") EventPublisher eventPublisher,
            ConvertObjectToJson convertObjectToJson) {
        this.eventPublisher = eventPublisher;
        this.convertObjectToJson = convertObjectToJson;
    }

    @Override
    public void apply(HttpServletResponse response, HttpServletRequest request) throws IOException {
        eventPublisher.publishEvent(
                EndpointHistory.builder()
                        .endpoint(request.getRequestURI())
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                        .codeResponse(404)
                        .build());

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType("application/json");
        MessageResponse messageResponse = MessageResponse.builder().message(Constants.NO_USER).build();
        response.getWriter().write(convertObjectToJson.apply(messageResponse));
    }
}
