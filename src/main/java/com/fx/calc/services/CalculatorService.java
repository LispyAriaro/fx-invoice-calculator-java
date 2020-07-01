package com.fx.calc.services;

import com.fx.calc.exceptions.NotFoundException;
import com.fx.calc.models.FxCalculationResult;
import com.fx.calc.models.dto.CalculateInvoiceDto;

public interface CalculatorService {
    public FxCalculationResult calculate(CalculateInvoiceDto calculateInvoiceDto) throws NotFoundException;
}
