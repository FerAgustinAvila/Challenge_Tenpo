package org.ferdev.challengetenpo.usecase.impl;

import org.ferdev.challengetenpo.adapter.repository.EndpointHistoryRepository;
import org.ferdev.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.usecase.EndpointHistoryListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EndpointHistoryListenerImpl implements EndpointHistoryListener {

    private final EndpointHistoryRepository endpointHistoryRepository;

    @Autowired
    public EndpointHistoryListenerImpl(EndpointHistoryRepository endpointHistoryRepository) {
        this.endpointHistoryRepository = endpointHistoryRepository;
    }

    @EventListener
    @Override
    public void handleEndpointHistory(EndpointHistory endpointHistory) {
        endpointHistoryRepository.save(endpointHistory);
    }
}
