package org.ferdev.challengetenpo.challengetenpo.exception.impl;

import org.ferdev.challengetenpo.challengetenpo.Constants;
import org.ferdev.challengetenpo.challengetenpo.dto.MessageResponse;
import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.challengetenpo.exception.UnauthorizedHandler;
import org.ferdev.challengetenpo.challengetenpo.util.ConvertObjectToJson;
import org.ferdev.challengetenpo.challengetenpo.util.event.EventPublisher;
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
public class UnauthorizedHandlerImpl implements UnauthorizedHandler {

    private final EventPublisher eventPublisher;
    private final ConvertObjectToJson convertObjectToJson;

    @Autowired
    public UnauthorizedHandlerImpl(
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
                        .codeResponse(401)
                        .build());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        MessageResponse messageResponse = MessageResponse.builder().message(Constants.EXPIRED_JWT).build();
        response.getWriter().write(convertObjectToJson.apply(messageResponse));
    }
}
