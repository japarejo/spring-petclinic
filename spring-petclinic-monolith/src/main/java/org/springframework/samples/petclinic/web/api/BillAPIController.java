package org.springframework.samples.petclinic.web.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/bills")
public class BillAPIController {

	BillService billService;
	
	
	@Autowired
	public BillAPIController(BillService billService) {
		super();
		this.billService = billService;
	}

	@GetMapping()
	public List<Bill> getAllBills(){
		return billService.findAll();
	}
	
	
	@GetMapping("/{billId}")
	public Bill getBill(@PathVariable("billId") Integer billId) {
		Optional<Bill> bill=billService.findById(billId);
		if(!bill.isPresent())
			throw new ResourceNotFoundException("Bill with ID '"+billId+"' not found!");
		return bill.get();		
	}
	
	@PostMapping()
	public ResponseEntity<Bill> createBill(@RequestBody @Valid Bill bill,BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new BadRequestException(bindingResult.getAllErrors().toString());
		else
			billService.save(bill);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bill.getId())
                .toUri();
		
		return ResponseEntity
					.created(location)
					.body(bill);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void modifyBill(@RequestBody @Valid Bill bill, BindingResult bindingResult, @PathVariable("id") Integer id){
		
		if(bindingResult.hasErrors())
			throw new BadRequestException(bindingResult.getAllErrors().toString());
		
		Optional<Bill> billToUpdate=billService.findById(id);
		if(billToUpdate.isPresent())
		{
			BeanUtils.copyProperties(bill, billToUpdate.get(), "id");
			billService.save(billToUpdate.get());
		}else
			throw new ResourceNotFoundException("Bill with ID '"+id+"' not found!");
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMapping(@PathVariable("id") Integer id) {
		Optional<Bill> billToUpdate=billService.findById(id);
		
		if(billToUpdate.isPresent())
		{			
			billService.remove(billToUpdate.get());
		}else
			throw new ResourceNotFoundException("Bill with ID '"+id+"' not found!");
	}
}
