package com.btsoftworks.balancingact;

import com.google.common.collect.ImmutableMap;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

public class StaticQuoteService implements QuoteService {
    private static ImmutableMap<String, StockQuote> quoteLookup;

    static {
        Instant lastUpdated = Instant.from(
                LocalDateTime.of(2020, Month.APRIL, 7, 16, 0, 0)
                        .atZone(ZoneId.of("America/New_York")));
        quoteLookup = ImmutableMap.of(
                "BND",
                StockQuote.builder()
                .setSymbol("BND")
                .setPrice(86.31)
                .setLastUpdated(lastUpdated)
                .build(),
                "BNDX",
                StockQuote.builder()
                .setSymbol("BNDX")
                .setPrice(56.42)
                .setLastUpdated(lastUpdated)
                .build(),
                "VTI",
                StockQuote.builder()
                        .setSymbol("VTI")
                        .setPrice(132.16)
                        .setLastUpdated(lastUpdated)
                        .build(),
                "VXUS",
                StockQuote.builder()
                        .setSymbol("VXUS")
                        .setPrice(42.79)
                        .setLastUpdated(lastUpdated)
                        .build());
    }

    @Override
    public Mono<StockQuote> getQuoteForSymbol(String symbol) {
        return Mono.just(quoteLookup.getOrDefault(symbol,
                StockQuote.builder()
                        .setSymbol(symbol)
                        .setPrice(1)
                        .setLastUpdated(Instant.now())
                        .build()));
    }
}
