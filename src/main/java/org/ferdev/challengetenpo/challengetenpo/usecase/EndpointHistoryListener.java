package org.ferdev.challengetenpo.challengetenpo.usecase;

import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;

public interface EndpointHistoryListener {

    void handleEndpointHistory(EndpointHistory endpointHistoryEvent);
}
