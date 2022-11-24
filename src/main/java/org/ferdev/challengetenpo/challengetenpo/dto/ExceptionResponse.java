package org.ferdev.challengetenpo.challengetenpo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ExceptionResponse {
    private LocalDateTime dateTime;
    private String message;
    private List<String> details;
}