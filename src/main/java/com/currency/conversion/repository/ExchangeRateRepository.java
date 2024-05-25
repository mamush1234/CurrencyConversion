package com.currency.conversion.repository;


import com.currency.conversion.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
	Optional<ExchangeRate> findByCurrencyCode(String currencyCode);
}
