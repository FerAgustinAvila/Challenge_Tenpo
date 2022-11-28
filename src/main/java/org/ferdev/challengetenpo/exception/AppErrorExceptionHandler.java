package org.ferdev.challengetenpo.exception;

import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class AppErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleValidationException(ValidException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(exception.getMessages()));
    }

    @ExceptionHandler(AppErrorException.class)
    public ResponseEntity<?> handleAppErrorException(AppErrorException exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(exception.getReason(), null, exception.getStatus());
    }
}
