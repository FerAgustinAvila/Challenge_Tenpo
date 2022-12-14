package org.ferdev.challengetenpo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
@SuppressWarnings("PMD.TooManyMethods")
public class WebClientConfig {

    @Bean
    public WebClient externalService() {
        return WebClient.create();
    }

}
