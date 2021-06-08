package org.springframework.samples.petclinic.apiclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bills",url="http://localhost:8095/api/v1")
public interface BillClient {
	@RequestMapping(method = RequestMethod.GET,value = "/bills") 
	List<Bill> getBills();
}
