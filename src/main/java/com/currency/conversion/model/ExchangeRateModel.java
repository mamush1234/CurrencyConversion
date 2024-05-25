package com.currency.conversion.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ExchangeRateModel {
	
    private Long id;

    @NotBlank(message = "{exchangeRate.currencyCode.required}")
    private String currencyCode;

	@Positive(message = "{exchangeRate.rate.positive}")
    @NotNull(message = "{exchangeRate.rate.required}")
    private BigDecimal rate;


    public ExchangeRateModel() {
    }

    public ExchangeRateModel(String currencyCode, BigDecimal rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public ExchangeRateModel(Long id, String currencyCode, BigDecimal rate, LocalDateTime created, LocalDateTime lastUpdated) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}




    @Override
    public int hashCode() {
        return Objects.hash(id, currencyCode, rate);
    }

	@Override
	public String toString() {
		return String.format("ExchangeRateModel [id=%s, currencyCode=%s, rate=%s]", id,
				currencyCode, rate);
	}
}
