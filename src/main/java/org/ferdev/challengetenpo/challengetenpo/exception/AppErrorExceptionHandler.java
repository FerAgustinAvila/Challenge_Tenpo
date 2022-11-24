package org.ferdev.challengetenpo.challengetenpo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class AppErrorExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppErrorException.class)
    public ResponseEntity<?> handleAppErrorException(AppErrorException exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(exception.getReason(), null, exception.getStatus());
    }
}