package org.ferdev.challengetenpo.cache;

import java.math.BigDecimal;

public interface PercentageCache {

    void refreshCache();

    BigDecimal getPercentage();
}
