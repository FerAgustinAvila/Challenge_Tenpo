package org.ferdev.challengetenpo.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.challengetenpo.adapter.repository.EndpointHistoryRepository;
import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.challengetenpo.usecase.GetHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetHistoryImpl implements GetHistory {

    private final EndpointHistoryRepository endpointHistoryRepository;

    public static final int PAGINATION_SIZE = 5;

    @Autowired
    public GetHistoryImpl(EndpointHistoryRepository endpointHistoryRepository) {
        this.endpointHistoryRepository = endpointHistoryRepository;
    }

    @Override
    public List<EndpointHistory> apply(int page) {
        Page<EndpointHistory> endpointHistories = endpointHistoryRepository
                .findAllOrderById(PageRequest.of(page - 1, PAGINATION_SIZE));

        return new ArrayList<>(endpointHistories.getContent());
    }
}
