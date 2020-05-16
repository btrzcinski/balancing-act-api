package com.btsoftworks.balancingact;

import reactor.core.publisher.Mono;

public interface QuoteService {
    Mono<StockQuote> getQuoteForSymbol(String symbol);
}
