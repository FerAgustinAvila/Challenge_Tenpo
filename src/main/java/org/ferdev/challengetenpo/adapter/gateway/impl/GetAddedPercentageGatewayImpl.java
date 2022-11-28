package org.ferdev.challengetenpo.adapter.gateway.impl;

import org.ferdev.challengetenpo.adapter.gateway.GetAddedPercentageGateway;
import org.ferdev.challengetenpo.adapter.repository.PercentageRepository;
import org.ferdev.challengetenpo.Constants;
import org.ferdev.challengetenpo.cache.PercentageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetAddedPercentageGatewayImpl implements GetAddedPercentageGateway {

    private final WebClient externalService;
    private final PercentageCache percentageCache;
    private final PercentageRepository percentageRepository;

    private BigDecimal percentage;


    @Autowired
    public GetAddedPercentageGatewayImpl(WebClient externalService,
                                         PercentageCache percentageCache,
                                         PercentageRepository percentageRepository) {
        this.externalService = externalService;
        this.percentageCache = percentageCache;
        this.percentageRepository = percentageRepository;
    }

    @Override
    public Map<String, BigDecimal> apply(BigDecimal a, BigDecimal b) {

        try {
            percentage = percentageCache.getPercentage();
        } catch (Exception exception) {
            percentage = BigDecimal.valueOf(percentageRepository.findFirstByOrderByIdDesc().getPercentageValue());
        }

        /*BigDecimal resultIgnored = externalService
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(5))
                .filter(e -> e instanceof WebClientResponseException.GatewayTimeout)

                // This block of code retries the connection to the external service
                //        three times and returns an internal server error
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                    throw new AppErrorException(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            new HttpHeaders(),
                            Constants.EXTERNAL_SERVICE_ERROR,
                            new InternalError()
                    );
                }))
                .block();*/
        Map<String, BigDecimal> result = new HashMap<>();
        result.put(Constants.MAP_PERCENTAGE_KEY, percentage);
        result.put(Constants.MAP_RESULT_KEY, operationFromExternalService(a, b));

        return result;

    }

    private BigDecimal operationFromExternalService(BigDecimal a, BigDecimal b) {
        BigDecimal aPlusB = a.add(b);
        return aPlusB.add(
                aPlusB.multiply(
                                percentage
                        )
                        .divide(
                                new BigDecimal(100)
                        )
        );
    }
}
