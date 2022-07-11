package com.training.boot.ms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.boot.ms.model.CurrencyConverterDTO;

@FeignClient(name = "currency-conversion-service", path = "/api/v1")
public interface CurrencyConversionProxy {

	@RequestMapping(value = "/from/{from}/to/{to}")
	public CurrencyConverterDTO convertCurrency(@PathVariable String from, @PathVariable String to);

}
