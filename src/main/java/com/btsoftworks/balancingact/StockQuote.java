package com.btsoftworks.balancingact;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.auto.value.AutoValue;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@AutoValue
@JsonSerialize(as = StockQuote.class)
@JsonDeserialize(builder = AutoValue_StockQuote.Builder.class)
public abstract class StockQuote {

    @NotNull
    @JsonProperty("symbol")
    public abstract String symbol();

    @NotNull
    @JsonProperty("price")
    public abstract double price();

    @NotNull
    @JsonProperty("lastUpdated")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public abstract Instant lastUpdated();

    @NotNull
    public static Builder builder() {
        return new AutoValue_StockQuote.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @NotNull
        @JsonProperty("symbol")
        public abstract Builder setSymbol(String value);

        @NotNull
        @JsonProperty("price")
        public abstract Builder setPrice(double value);

        @NotNull
        @JsonProperty("lastUpdated")
        public abstract Builder setLastUpdated(Instant value);

        @NotNull
        public abstract StockQuote build();
    }
}
