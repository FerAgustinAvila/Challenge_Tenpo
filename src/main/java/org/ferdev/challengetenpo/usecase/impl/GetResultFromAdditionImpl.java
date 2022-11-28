package org.ferdev.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.adapter.repository.AdditionRepository;
import org.ferdev.challengetenpo.Constants;
import org.ferdev.challengetenpo.adapter.gateway.GetAddedPercentageGateway;
import org.ferdev.challengetenpo.dto.OperationBodyRequest;
import org.ferdev.challengetenpo.entity.Addition;
import org.ferdev.challengetenpo.usecase.GetResultFromAddition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class GetResultFromAdditionImpl implements GetResultFromAddition {

    private final GetAddedPercentageGateway getAddedPercentageGateway;
    private final AdditionRepository additionRepository;

    @Autowired
    public GetResultFromAdditionImpl(GetAddedPercentageGateway getAddedPercentageGateway, AdditionRepository additionRepository) {
        this.getAddedPercentageGateway = getAddedPercentageGateway;
        this.additionRepository = additionRepository;
    }

    @Override
    public Map<String, BigDecimal> apply(OperationBodyRequest operation) {
        Map<String, BigDecimal> resultResponse = getAddedPercentageGateway.apply(operation.getA(), operation.getB());
        additionRepository.save(Addition
                .builder()
                        .a(operation.getA().longValue())
                        .b(operation.getB().longValue())
                        .result(resultResponse.get(Constants.MAP_RESULT_KEY).longValue())
                        .percentage(resultResponse.get(Constants.MAP_PERCENTAGE_KEY).longValue())
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        return resultResponse;
    }
}
