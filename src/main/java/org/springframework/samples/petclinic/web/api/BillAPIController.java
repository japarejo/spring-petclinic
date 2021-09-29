package org.springframework.samples.petclinic.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/bills")
public class BillAPIController {
	
	@Autowired
	BillService billService;		
	
	@GetMapping
	public List<Bill> findAll(){
		return billService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Bill findById(@PathVariable("id") Integer id){
		Bill result=billService.findById(id);
		if(result==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return result;
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Bill createBill(@RequestBody @Valid Bill newBill, BindingResult br) {
		if(br.hasErrors())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,br.getAllErrors().toString());
		billService.save(newBill);
		return newBill;
	}
}
