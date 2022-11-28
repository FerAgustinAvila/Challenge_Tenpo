package org.ferdev.challengetenpo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "endpoint_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointHistory {
    private static final long serialVersionUID = 7614019218872703375L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp createdAt;

    private String response;

    private int codeResponse;

    private String endpoint;

}
