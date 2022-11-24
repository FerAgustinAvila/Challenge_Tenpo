package org.ferdev.challengetenpo.challengetenpo.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointHistoryDto {

    private Timestamp createdAt;

    private String response;

    private String endpoint;

    private int codeResponse;

}
