package org.springframework.samples.petclinic.configuration;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfiguration {
/*
	@Autowired(required = false)
	private List<Customizer<Resilience4JCircuitBreakerFactory>> customizers = new ArrayList<>();
	
	@Bean	
	public Resilience4JCircuitBreakerFactory resilience4jCircuitBreakerFactory() {
		Resilience4JCircuitBreakerFactory factory = new Resilience4JCircuitBreakerFactory();
		customizers.forEach(customizer -> customizer.customize(factory));
		return factory;
	} 
	*/	
	
}
