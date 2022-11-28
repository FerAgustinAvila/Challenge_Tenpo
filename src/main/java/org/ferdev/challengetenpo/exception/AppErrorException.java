package org.ferdev.challengetenpo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;
    private final String reason;

    public AppErrorException(HttpStatus status,
                             String reason,
                             Throwable cause) {
        super(reason, cause);
        this.status = status.value();
        this.reason = reason;
    }
}
