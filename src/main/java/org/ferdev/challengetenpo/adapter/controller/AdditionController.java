package org.ferdev.challengetenpo.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.Constants;
import org.ferdev.challengetenpo.dto.MessageResponse;
import org.ferdev.challengetenpo.dto.OperationBodyRequest;
import org.ferdev.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.exception.ValidException;
import org.ferdev.challengetenpo.usecase.GetResultFromAddition;
import org.ferdev.challengetenpo.util.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/addition")
public class AdditionController {

    public static final String OPERATION = "/addition";

    private final GetResultFromAddition getResultFromAddition;
    private final EventPublisher eventPublisher;

    @Autowired
    public AdditionController(GetResultFromAddition getResultFromAddition,
                              @Qualifier("AsyncEventPublisher") EventPublisher eventPublisher) {
        this.getResultFromAddition = getResultFromAddition;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody OperationBodyRequest operationBodyRequest,
                                    BindingResult errors) {

        // Todo handle exception when input "21a" or 21a, for example
        if (errors.hasErrors()) {
            eventPublisher.publishEvent(
                    EndpointHistory.builder()
                            .endpoint(OPERATION)
                            .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                            .codeResponse(400)
                            .build());
            throw new ValidException(errors);
        }

        Map<String, BigDecimal> result = getResultFromAddition.apply(operationBodyRequest);

        MessageResponse messageResponse = MessageResponse.builder()
                .message(String
                        .format("The result is %s and the percentage %s",
                                result.get(Constants.MAP_RESULT_KEY),
                                result.get(Constants.MAP_PERCENTAGE_KEY)))
                .build();

        eventPublisher.publishEvent(
                EndpointHistory.builder()
                        .endpoint(OPERATION)
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                        .codeResponse(200)
                        .response(messageResponse.getMessage())
                        .build());

        return ResponseEntity.ok().body(messageResponse);
    }

}
