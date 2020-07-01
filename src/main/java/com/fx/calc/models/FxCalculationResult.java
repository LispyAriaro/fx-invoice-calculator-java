package com.fx.calc.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FxCalculationResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("invoiceInCad")
    private BigDecimal invoiceInCad;

    public BigDecimal getInvoiceInCad() {
        return invoiceInCad;
    }

    public void setInvoiceInCad(BigDecimal invoiceInCad) {
        this.invoiceInCad = invoiceInCad;
    }
}