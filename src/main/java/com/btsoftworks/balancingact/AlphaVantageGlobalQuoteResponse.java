package com.btsoftworks.balancingact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.auto.value.AutoValue;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * {
 *     "Global Quote": {
 *         "01. symbol": "IBM",
 *         "02. open": "118.8000",
 *         "03. high": "119.5700",
 *         "04. low": "114.8700",
 *         "05. price": "114.9400",
 *         "06. volume": "5528913",
 *         "07. latest trading day": "2020-04-07",
 *         "08. previous close": "114.8200",
 *         "09. change": "0.1200",
 *         "10. change percent": "0.1045%"
 *     }
 * }
 */
@AutoValue
@JsonSerialize(as = AlphaVantageGlobalQuoteResponse.class)
@JsonDeserialize(builder = AutoValue_AlphaVantageGlobalQuoteResponse.Builder.class)
public abstract class AlphaVantageGlobalQuoteResponse {

    @NotNull
    @JsonProperty("Global Quote")
    public abstract GlobalQuote globalQuote();

    @NotNull
    public static Builder builder() {
        return new AutoValue_AlphaVantageGlobalQuoteResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @NotNull
        @JsonProperty("Global Quote")
        public abstract Builder setGlobalQuote(GlobalQuote value);

        @NotNull
        public abstract AlphaVantageGlobalQuoteResponse build();
    }

    @AutoValue
    @JsonSerialize(as = GlobalQuote.class)
    @JsonDeserialize(builder = AutoValue_AlphaVantageGlobalQuoteResponse_GlobalQuote.Builder.class)
    public abstract static class GlobalQuote {
        @NotNull
        @JsonProperty("01. symbol")
        public abstract String symbol();

        @NotNull
        @JsonProperty("02. open")
        public abstract double open();

        @NotNull
        @JsonProperty("03. high")
        public abstract double high();

        @NotNull
        @JsonProperty("04. low")
        public abstract double low();

        @NotNull
        @JsonProperty("05. price")
        public abstract double price();

        @NotNull
        @JsonProperty("06. volume")
        public abstract long volume();

        @NotNull
        @JsonProperty("07. latest trading day")
        public abstract LocalDate latestTradingDay();

        @NotNull
        @JsonProperty("08. previous close")
        public abstract double previousClose();

        @NotNull
        @JsonProperty("09. change")
        public abstract double change();

        @NotNull
        @JsonProperty("10. change percent")
        public abstract String changePercent();

        @NotNull
        public static Builder builder() {
            return new AutoValue_AlphaVantageGlobalQuoteResponse_GlobalQuote.Builder();
        }

        @AutoValue.Builder
        public abstract static class Builder {
            @NotNull
            @JsonProperty("01. symbol")
            public abstract Builder setSymbol(String value);

            @NotNull
            @JsonProperty("02. open")
            public abstract Builder setOpen(double value);

            @NotNull
            @JsonProperty("03. high")
            public abstract Builder setHigh(double value);

            @NotNull
            @JsonProperty("04. low")
            public abstract Builder setLow(double value);

            @NotNull
            @JsonProperty("05. price")
            public abstract Builder setPrice(double value);

            @NotNull
            @JsonProperty("06. volume")
            public abstract Builder setVolume(long value);

            @NotNull
            @JsonProperty("07. latest trading day")
            public abstract Builder setLatestTradingDay(LocalDate value);

            @NotNull
            @JsonProperty("08. previous close")
            public abstract Builder setPreviousClose(double value);

            @NotNull
            @JsonProperty("09. change")
            public abstract Builder setChange(double value);

            @NotNull
            @JsonProperty("10. change percent")
            public abstract Builder setChangePercent(String value);

            @NotNull
            public abstract GlobalQuote build();
        }
    }
}
