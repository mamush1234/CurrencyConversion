package com.currency.conversion.service;

import com.currency.conversion.entity.ExchangeRate;
import com.currency.conversion.exceptions.CurrencyNotFoundException;
import com.currency.conversion.model.ExchangeRateModel;
import com.currency.conversion.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ExchangeRateService {
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    public ExchangeRateModel findByCurrencyCode(String currencyCode) throws CurrencyNotFoundException {
        ExchangeRateModel exchangeRateModel = null;
        Optional<ExchangeRate> exchangeRateEntity = exchangeRateRepository.findByCurrencyCode(currencyCode);
        if (exchangeRateEntity.isPresent()) {
            ExchangeRate c = exchangeRateEntity.get();
            exchangeRateModel = assembleExchangeRateModel(c);
        }
        else {
            throw new CurrencyNotFoundException(currencyCode);
        }
        return exchangeRateModel;
    }
    private static ExchangeRateModel assembleExchangeRateModel(ExchangeRate exchangeRateEntity) {
        return new ExchangeRateModel(exchangeRateEntity.getId(), exchangeRateEntity.getCurrencyCode(), exchangeRateEntity.getRate().stripTrailingZeros(), exchangeRateEntity.getCreated(), exchangeRateEntity.getLastUpdated());
    }
}
