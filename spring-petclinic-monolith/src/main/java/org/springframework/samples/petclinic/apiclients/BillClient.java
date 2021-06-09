package org.springframework.samples.petclinic.apiclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bills-microservice")
public interface BillClient {
	@RequestMapping(method = RequestMethod.GET,value = "/api/v1/bills") 
	List<Bill> getBills();
}
