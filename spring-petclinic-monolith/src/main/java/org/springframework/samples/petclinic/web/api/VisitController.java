package org.springframework.samples.petclinic.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/visits")
public class VisitController {

	VisitService visitService;
	
	@Autowired
	public VisitController(VisitService visitService) {
		this.visitService=visitService;
	}
	
	@GetMapping("/{id}")
	public Visit getVisit(@PathVariable("id")Integer id) {
		Optional<Visit> visit=visitService.findById(id);
		if(visit.isPresent())			
			return visit.get();
		else
			throw new ResourceNotFoundException();
	}
}
