package com.btsoftworks.balancingact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class StaticServiceConfig {
    @Bean
    public QuoteService quoteService() {
        return new StaticQuoteService();
    }
}
