package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/bills")
public class BillAPIController {

	@Autowired
	BillService billService;
	
	@GetMapping
	public List<Bill> getAll(){
		return billService.getAllBills();
	}
	
	@GetMapping("/{id}")
	public Bill getAll(@PathVariable("id") Integer id) {
		Bill b=billService.findBill(id);
		if(b==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return b;
	}
	
	
}
