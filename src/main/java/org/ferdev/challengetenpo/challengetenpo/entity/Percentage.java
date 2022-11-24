package org.ferdev.challengetenpo.challengetenpo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "percentage")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Percentage {
    private static final long serialVersionUID = 7614019218872703375L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long percentageValue;
}
