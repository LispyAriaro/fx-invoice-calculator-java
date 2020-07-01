package com.fx.calc.rest;

import com.fx.calc.exceptions.InvalidDataFormatException;
import com.fx.calc.exceptions.NotFoundException;
import com.fx.calc.models.FxCalculationResult;
import com.fx.calc.models.dto.CalculateInvoiceDto;
import com.fx.calc.models.dto.ResponseDto;
import com.fx.calc.services.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author efe ariaroo
 */
@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CalculatorRestController {
    private static final Logger log = LoggerFactory.getLogger(CalculatorRestController.class);

    @Autowired
    private CalculatorService calculatorService;


    @PostMapping("/api/v1/calculator/invoice")
    public ResponseEntity<ResponseDto> handleCalculateInvoice(@Valid @RequestBody CalculateInvoiceDto calculateInvoiceDto, BindingResult fields) throws NotFoundException, InvalidDataFormatException, Exception {
        RestUtil.validate(fields);

        FxCalculationResult result = calculatorService.calculate(calculateInvoiceDto);

        return RestUtil.response(HttpStatus.OK, ResponseDto.Status.success, "", result);
    }
}
