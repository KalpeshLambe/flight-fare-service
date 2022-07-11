package com.training.boot.ms.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.boot.ms.dao.FlightFareRepository;
import com.training.boot.ms.feign.CurrencyConversionProxy;
import com.training.boot.ms.model.CurrencyConverterDTO;
import com.training.boot.ms.model.FlightFare;

@RestController
@RequestMapping(value="/api/v1/flight/{flightCode}/fare/{currency}")
public class FlightFareController {

	@Autowired
	private FlightFareRepository repository;
	
	@Value("${base.currency}")
	private String baseCurrency;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CurrencyConversionProxy feignProxy;
	
	@GetMapping
	public FlightFare getSingleFareTicket(@PathVariable String flightCode, @PathVariable String currency)
	{
		FlightFare fare  = this.getFlightFare(flightCode);
		fare.setCurrency(currency);
		
		if(!baseCurrency.equals(currency)) {
			// conversion rate
			fare.setFlightFare(getFlightFare(flightCode).getFlightFare().multiply(getConversionBigDecimalWithFeign(currency)));
		}
		return fare;
	}
	
	
	private FlightFare getFlightFare(String flightCode) {
		
		FlightFare flight = new FlightFare(null, flightCode, null);
		Example<FlightFare> flightFare = Example.of(flight);
		FlightFare fare = repository.findOne(flightFare).get();
		return fare;
	}
	
	// Call the currency conversion service here
	private BigDecimal getConversionBigDecimal(String toCurrency) {
		//RestTemplate restTemplate = new RestTemplate();
		
		Map<String, String> urlPathVariable = new HashMap<String, String>();
		urlPathVariable.put("from", baseCurrency);
		urlPathVariable.put("to", toCurrency);
		
		//final String baseUrl = "http://localhost:7101/api/v1/from/{from}/to/{to}";
		final String baseUrl = "http://currency-conversion-service/api/v1/from/{from}/to/{to}";
		ResponseEntity<CurrencyConverterDTO> responseEntity = restTemplate.getForEntity(baseUrl, CurrencyConverterDTO.class, urlPathVariable);
		CurrencyConverterDTO converter = responseEntity.getBody();
		System.out.println("-------------------" + converter.getEnvironment());
		return converter.getConversionRate();
	}
	
	private BigDecimal getConversionBigDecimalWithFeign(String toCurrency) {
		
		CurrencyConverterDTO converter = feignProxy.convertCurrency(baseCurrency, toCurrency);;
		System.out.println("--------WITH FEIGN-----------");
		return converter.getConversionRate();
	}
	
}
