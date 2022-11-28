package org.ferdev.challengetenpo.util.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

@Service
@Qualifier("AsyncEventPublisher")
public class AsyncEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AsyncEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    @Override
    public void publishEvent(Object event) {
        applicationEventPublisher.publishEvent(event);
    }
}
