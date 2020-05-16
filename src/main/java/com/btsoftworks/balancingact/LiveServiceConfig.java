package com.btsoftworks.balancingact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;

@Configuration
@Profile("production")
public class LiveServiceConfig {
    @Bean
    public QuoteService quoteService(
            Converter<AlphaVantageGlobalQuoteResponse, StockQuote> quoteConverter) {
        return new AlphaVantageQuoteService(quoteConverter);
    }
}
