package com.currency.conversion.service;

import com.currency.conversion.exceptions.CurrencyNotFoundException;
import com.currency.conversion.model.CurrencyConversionModel;
import com.currency.conversion.model.ExchangeRateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConversionService {

    @Autowired
    private ExchangeRateService exchangeRateService;

    public CurrencyConversionModel convertCurrency(BigDecimal fromQuantity, String from, String to) throws CurrencyNotFoundException {
        final ExchangeRateModel fromModel = exchangeRateService.findByCurrencyCode(from);
        final ExchangeRateModel toModel = exchangeRateService.findByCurrencyCode(to);
        return new CurrencyConversionModel(from, to, fromQuantity, fromQuantity.multiply(fromModel.getRate()).divide(toModel.getRate(), 18, RoundingMode.HALF_UP));
    }
}
