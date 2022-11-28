package org.ferdev.challengetenpo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "addition")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Addition {
    private static final long serialVersionUID = 7614019218872703375L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long a;

    private Long b;

    private Long percentage;

    private Long result;

    private Timestamp createdAt;
}
