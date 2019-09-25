package com.quoteapp;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quoteapp.api.Quote;
import java.util.List;
import org.hibernate.validator.constraints.*;

public class QuoteAppConfiguration extends Configuration {
    @NotEmpty
    private List<Quote> quotes;

    @JsonProperty
    public List<Quote> getQuotes() {
        return quotes;
    }

    @JsonProperty
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
