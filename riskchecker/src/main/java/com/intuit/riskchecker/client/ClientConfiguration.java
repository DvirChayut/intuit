package com.intuit.riskchecker.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
