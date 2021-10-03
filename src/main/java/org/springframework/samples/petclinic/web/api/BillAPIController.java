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
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.samples.petclinic.service.VisitService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/bills")
public class BillAPIController implements BillApi {
	
	@Autowired
	BillService billService;		
	
	@Autowired
	VisitService visitService;
	
	@Override
	@Operation(tags = {"Bill"})
	@GetMapping
	public List<Bill> findAll(){
		return billService.findAll();
	}
	
	@Override
	@Operation(tags = {"Bill"})
	@GetMapping(value = "/{id}")
	public Bill findById(@PathVariable("id") Integer id){
		Bill result=billService.findById(id);
		if(result==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return result;
	}
	
	@Override
	@Operation(tags = {"Bill"})
	@PostMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Bill> createBill(@RequestBody @Valid Bill newBill, BindingResult br) {
		if(br.hasErrors())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,br.getAllErrors().toString());
		if(newBill.getVisit()!=null) {
			Optional<Visit> visit=visitService.findById(newBill.getVisit().getId());
			if(visit.isPresent())
				newBill.setVisit(visit.get());
			else
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There does not exist an Visit with id:"+newBill.getVisit().getId());
		}
		billService.save(newBill);
		URI uri=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBill.getId())
                .toUri();
		return ResponseEntity.created(uri).body(newBill);
	}
	@Override
	@Operation(tags = {"Bill"})
	@PutMapping("/{id}")
	public void updateBill(@PathVariable("id") Integer id,@RequestBody @Valid Bill newBill, BindingResult br) {
		if(br.hasErrors())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,br.getAllErrors().toString());
		if(!id.equals(newBill.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You cannot modify the bill with id"+newBill.getId()+" using this url");
		Bill billToUpdate=findById(id);
		BeanUtils.copyProperties(newBill, billToUpdate, "id","version","visit");
		billService.save(billToUpdate);
	}
	
	
	@Override
	@Operation(summary = "This method deletes bills, take good care!",tags = {"Bill"})
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteBill(@PathVariable("id") Integer id) {
		Bill result=billService.findById(id);
		if(result==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		else
			billService.deleteById(id);
	}
	
	
}
