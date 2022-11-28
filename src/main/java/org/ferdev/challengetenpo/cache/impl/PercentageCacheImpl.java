package org.ferdev.challengetenpo.cache.impl;

import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.cache.PercentageCache;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@Slf4j
public class PercentageCacheImpl implements PercentageCache {

    BigDecimal randomResult;

    @Scheduled(fixedDelayString = "${percentage.refresh-time-seconds}000")
    @Retryable(value = Throwable.class,
            backoff = @Backoff(random = true, delay = 1000, multiplier = 2))
    @Override
    public void refreshCache() {
        Random random = new Random();
        int low = 1;
        int high = 1000;
        randomResult = BigDecimal.valueOf(random.nextInt(high - low) + low);
        log.info(String.format("%s", randomResult));
    }

    @Override
    public BigDecimal getPercentage() {
        return randomResult;
    }
}
