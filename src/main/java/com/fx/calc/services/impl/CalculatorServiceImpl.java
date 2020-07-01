package com.fx.calc.services.impl;

import com.fx.calc.exceptions.InvalidDataFormatException;
import com.fx.calc.exceptions.NotFoundException;
import com.fx.calc.models.FxCalculationResult;
import com.fx.calc.models.dto.CalculateInvoiceDto;
import com.fx.calc.models.dto.CurrencyLayerResponseDto;
import com.fx.calc.services.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CalculatorServiceImpl implements CalculatorService {
    private static final Logger log = LoggerFactory.getLogger(CalculatorServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${currency.layer.api.access.key}")
    private String currencyLayerApiAccessKey;


    @Override
    public FxCalculationResult calculate(CalculateInvoiceDto calculateInvoiceDto) throws NotFoundException {
        String fxCallUrl = String.format("http://api.currencylayer.com/historical?access_key=%s&date=%s&currencies=CAD&format=1",
                currencyLayerApiAccessKey, calculateInvoiceDto.getDate());

        ResponseEntity<CurrencyLayerResponseDto> response =
                restTemplate.getForEntity(fxCallUrl, CurrencyLayerResponseDto.class);
        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new NotFoundException("Failed to get a success response from Currency Layer. Please try again later.");
        }

        CurrencyLayerResponseDto fxExchange = response.getBody();
        if(!fxExchange.isSuccess()) {
            throw new NotFoundException("Sorry, could not get foreign exchange rate. Please try again later");
        }

        BigDecimal convertedCadAmount = calculateInvoiceDto.getUsdAmount().multiply(fxExchange.getQuotes().getUsdCad());

        FxCalculationResult result = new FxCalculationResult();
        result.setInvoiceInCad(convertedCadAmount);

        return result;
    }
}
