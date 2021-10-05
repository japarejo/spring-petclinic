package org.springframework.samples.petclinic.web.api.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name="billsFeign",url="http://localhost:8080/api/bills")
public interface BillApiFeign {

	@GetMapping()
	List<Bill> getAllBills(@RequestHeader(value = "Authorization", required = true) String token);
	
	@GetMapping(value = "/{id}")
	public Bill findById(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = true) String token);
	
	@PostMapping(consumes = "application/json")
	public Bill createBill(Bill newBill,@RequestHeader(value = "Authorization", required = true) String token);
	
	@PutMapping(value="/{id}", consumes = "application/json")
	public void updateBill(@PathVariable("id") Integer id, Bill updatedBill, @RequestHeader(value = "Authorization", required = true) String token);
	
	@DeleteMapping("/{id}")
	public void deleteBill(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = true) String token);
}
