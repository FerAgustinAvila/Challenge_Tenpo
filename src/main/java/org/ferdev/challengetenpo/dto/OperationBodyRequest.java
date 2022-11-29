package org.ferdev.challengetenpo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Constraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OperationBodyRequest {

    @Digits(integer = 3, fraction = 0, message = "More than 3 digits and decimals are not accepted")
    private BigDecimal a;

    @Digits(integer = 3, fraction = 0, message = "More than 3 digits and decimals are not accepted")
    private BigDecimal b;
}
