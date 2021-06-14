package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BillService {
	
	@CircuitBreaker(name="getAll",fallbackMethod="defaultBills")	
	public List<Bill> getAllBills(){
		Bill[] bills=new Bill[0];		 
		 RestTemplate restTemplate = new RestTemplate();
		 String resourceUrl	   = "http://localhost:8095/api/v1/bills";
		 ResponseEntity<Bill[]> response
		   = restTemplate.getForEntity(resourceUrl, Bill[].class);
		 bills=response.getBody();
		 return Arrays.asList(bills);
	}
	
	public List<Bill> defaultBills(Throwable t){
		return new ArrayList<Bill>();
	}
}
