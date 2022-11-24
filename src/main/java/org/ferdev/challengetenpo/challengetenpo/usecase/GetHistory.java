package org.ferdev.challengetenpo.challengetenpo.usecase;

import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;

import java.util.List;

@FunctionalInterface
public interface GetHistory {
    List<EndpointHistory> apply(int page);
}
