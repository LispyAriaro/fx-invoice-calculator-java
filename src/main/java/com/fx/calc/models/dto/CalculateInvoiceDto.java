package com.fx.calc.models.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This represents the structure of the json payload that will be sent in the HTTP request
 *
 * @author efe ariaroo
 */
public class CalculateInvoiceDto {
    private static final long serialVersionUID = 1L;

    public BigDecimal getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(BigDecimal usdAmount) {
        this.usdAmount = usdAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @NotNull(message = "USD Amount is a required field")
    @Digits(integer = 20, fraction = 2, message = "USD Amount: max number of decimal digits is 2")
    @DecimalMin(value = "0.00", message = "USD Aamount: minimum value for this field is 0.00")
    private BigDecimal usdAmount = BigDecimal.ZERO;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
}
