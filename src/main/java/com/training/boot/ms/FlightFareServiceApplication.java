package com.training.boot.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.training.boot.ms.feign.CurrencyConversionProxy;
import com.training.boot.ms.model.CurrencyConverterDTO;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //scan the interface marked with @FeignClient
public class FlightFareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightFareServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
