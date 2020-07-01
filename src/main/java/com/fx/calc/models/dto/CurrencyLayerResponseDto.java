package com.fx.calc.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


/**
 * This represents the structure of the json payload that will be sent back from Currency Layer
 *
 * @author efe ariaroo
 */
public class CurrencyLayerResponseDto {
    private boolean success;
    private Quotes quotes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    public class Quotes {
        @JsonProperty("USDCAD")
        private BigDecimal usdCad;

        public BigDecimal getUsdCad() {
            return usdCad;
        }

        public void setUsdCad(BigDecimal usdCad) {
            this.usdCad = usdCad;
        }
    }
}


