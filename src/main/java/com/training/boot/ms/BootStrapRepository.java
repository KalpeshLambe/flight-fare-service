package com.training.boot.ms;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.training.boot.ms.dao.FlightFareRepository;
import com.training.boot.ms.model.FlightFare;

@Component
public class BootStrapRepository implements CommandLineRunner {

	private FlightFareRepository repository;

	public BootStrapRepository(FlightFareRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {

		FlightFare converter1 = new FlightFare(123L, "S123", BigDecimal.valueOf(100), "USD");
		FlightFare converter2 = new FlightFare(124L, "S124", BigDecimal.valueOf(110), "USD");

		repository.save(converter1);
		repository.save(converter2);

	}

}
