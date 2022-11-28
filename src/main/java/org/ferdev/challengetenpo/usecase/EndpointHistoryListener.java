package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.entity.EndpointHistory;

public interface EndpointHistoryListener {

    void handleEndpointHistory(EndpointHistory endpointHistoryEvent);
}
