package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.dto.OperationBodyRequest;

import java.math.BigDecimal;
import java.util.Map;

@FunctionalInterface
public interface GetResultFromAddition {
    Map<String, BigDecimal> apply(OperationBodyRequest operation);
}
