package com.btsoftworks.balancingact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{symbol}")
    public Mono<StockQuote> getQuoteForSymbol(@PathVariable String symbol) {
        return quoteService.getQuoteForSymbol(symbol);
    }
}
