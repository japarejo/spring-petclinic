package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.samples.petclinic.service.OwnerService;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/bills")
public class BillAPIController {

	@Autowired
	BillService billService;
	@Autowired
	OwnerService ownerService;
	
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
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Bill createBill(@RequestBody @Valid Bill bill, BindingResult br) {
		if(br.hasErrors()) {			
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,br.getAllErrors().toString());
		}
		Owner owner=ownerService.findOwnerById(bill.getOwner().getId());
		if(owner==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Owner with id="+bill.getOwner().getId()+" does not exist");
		bill.setOwner(owner);
		billService.saveBill(bill);
		return bill;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBill(@RequestBody @Valid Bill bill, BindingResult br,@PathVariable("id") Integer id)
	{
		if(br.hasErrors()) {			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,br.getAllErrors().toString());
		}
		Owner owner=ownerService.findOwnerById(bill.getOwner().getId());
		if(owner==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Owner with id="+bill.getOwner().getId()+" does not exist");
		if(!id.equals(bill.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You cannot change the id of a bill");
		}
		bill.setOwner(owner);
		billService.saveBill(bill);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		Bill b=billService.findBill(id);
		if(b==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		billService.delete(id);	
	}
	
	
}
