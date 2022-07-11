package com.training.boot.ms.model;

import java.math.BigDecimal;

public class CurrencyConverterDTO {

	private Long id;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal conversionRate;
	private String environment;
	
	
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public CurrencyConverterDTO() {
	}
	public CurrencyConverterDTO(Long id, String currencyFrom, String currencyTo, BigDecimal conversionRate) {
		super();
		this.id = id;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.conversionRate = conversionRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public String getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}
	public BigDecimal getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}
	
	
}
