package org.ferdev.challengetenpo.challengetenpo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OperationBodyRequest {

    @Digits(integer = 100, fraction = 0)
    private BigDecimal a;

    @Digits(integer = 100, fraction = 0)
    private BigDecimal b;
}
