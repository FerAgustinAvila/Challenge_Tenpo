package org.ferdev.challengetenpo.adapter.gateway;

import java.math.BigDecimal;
import java.util.Map;

@FunctionalInterface
public interface GetAddedPercentageGateway {
    Map<String, BigDecimal> apply(BigDecimal a, BigDecimal b);
}
