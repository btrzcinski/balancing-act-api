package com.btsoftworks.balancingact;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class AlphaVantageQuoteService implements QuoteService {
    private static final String API_KEY = System.getenv("ALPHAVANTAGE_API_KEY");

    private final Converter<AlphaVantageGlobalQuoteResponse, StockQuote> quoteConverter;

    @Autowired
    public AlphaVantageQuoteService(Converter<AlphaVantageGlobalQuoteResponse, StockQuote> quoteConverter) {
        this.quoteConverter = quoteConverter;
    }

    private LoadingCache<String, Mono<StockQuote>> quoteCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(2))
            .build(new CacheLoader<>() {
                @Override
                public Mono<StockQuote> load(String symbol) throws Exception {
                    return getQuoteFromRestApi(symbol);
                }
            });

    private WebClient webClient = WebClient.builder()
            .baseUrl("https://www.alphavantage.co/query?apikey={apikey}")
            .defaultUriVariables(ImmutableMap.of("apikey", API_KEY))
            .build();

    private Mono<StockQuote> getQuoteFromRestApi(String symbol) {
        return webClient.get().uri(uriBuilder ->
                        uriBuilder.queryParam("function", "GLOBAL_QUOTE")
                                .queryParam("symbol", symbol)
                                .build())
                        .retrieve()
                        .bodyToMono(AlphaVantageGlobalQuoteResponse.class)
                        .map(response -> quoteConverter.convert(response))
                        .cache();
    }

    @Override
    public Mono<StockQuote> getQuoteForSymbol(String symbol) {
        return quoteCache.getUnchecked(symbol);
    }
}
