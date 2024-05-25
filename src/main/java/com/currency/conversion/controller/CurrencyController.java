package com.currency.conversion.controller;

import com.currency.conversion.exceptions.CurrencyNotFoundException;
import com.currency.conversion.model.CurrencyConversionModel;
import com.currency.conversion.request.CurrencyConversionRequest;
import com.currency.conversion.response.CurrencyConversionResponse;
import com.currency.conversion.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CurrencyController
{

    @PostMapping("/convertCurrency")
public CurrencyConversionResponse convertCurrency(@RequestBody CurrencyConversionRequest request)
{
    double exchangeRateINRtoGBP = 0.0096; //
    double convertedAmount = request.getAmount() * exchangeRateINRtoGBP;
    CurrencyConversionResponse response = new CurrencyConversionResponse();
    response.setCurrency(request.getDestCurrency());
    response.setConvertedAmount(convertedAmount);
    return response;
}

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/currency-converter/from/{from}/to/{to}")
    public ResponseEntity<BigDecimal> convertCurrency(@PathVariable String from, @PathVariable String to,
                                                      @RequestParam BigDecimal quantity) {
        CurrencyConversionModel model;
        try {
            model = currencyConversionService.convertCurrency(quantity, from, to);
        } catch (CurrencyNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(model.getTotalCalculatedAmount());
    }
}