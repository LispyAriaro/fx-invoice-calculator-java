package com.fx.calc.models.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author efe ariaroo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @NotNull(message = "USD amount id is a required field")
    private BigDecimal usdAmount;

    @NotBlank(message = "Date is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
}
