package com.btsoftworks.balancingact;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public final class AlphaVantageGlobalQuoteResponseConverter implements Converter<AlphaVantageGlobalQuoteResponse, StockQuote> {
    @Override
    public StockQuote convert(AlphaVantageGlobalQuoteResponse alphaVantageGlobalQuoteResponse) {
        return StockQuote.builder()
                .setSymbol(alphaVantageGlobalQuoteResponse.globalQuote().symbol())
                .setPrice(alphaVantageGlobalQuoteResponse.globalQuote().price())
                .setLastUpdated(Instant.now())
                .build();
    }
}
