package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.entity.EndpointHistory;

import java.util.List;

@FunctionalInterface
public interface GetHistory {
    List<EndpointHistory> apply(int page);
}
