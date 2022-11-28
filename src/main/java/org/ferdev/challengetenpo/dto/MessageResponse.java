package org.ferdev.challengetenpo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private String message;

    @Override
    public String toString() {
        return "Message: " + message;
    }
}