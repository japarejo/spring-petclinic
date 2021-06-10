package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.apiclients.BillClient;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BillController {

	@Autowired
	BillClient billsClient;
	
	@Autowired
	BillService billService;
	
	 @GetMapping("/bills")
	 public ModelAndView allBillsWithRestTemplate() {
		 
		 ModelAndView result=new ModelAndView("bills/listing");		 
		 result.addObject("bills",billService.getAllBills());		 
		 return result;
	 }
	 
	 @GetMapping("/bills/withFeign")
	 public ModelAndView allBillsWithFeign() {		 
		 ModelAndView result=new ModelAndView("bills/listing");		 
		 result.addObject("bills",billsClient.getBills());		 
		 return result;
	 }
	 
	 
	  @GetMapping("/bills/delete/{id}")
	  public ModelAndView deleteBillwithRestTemplate(@PathVariable("id") Integer id) {			 			
			 RestTemplate restTemplate = new RestTemplate();
			 String resourceUrl
			   = "http://localhost:8095/api/v1/bills/"+id;
			 ModelAndView result=null;
			 try {
			 restTemplate.delete(resourceUrl);
			 result=allBillsWithRestTemplate();
			 }catch(HttpClientErrorException exception) {
				 if(exception.getRawStatusCode()==HttpStatus.NOT_FOUND.value()) {
					 result=allBillsWithRestTemplate();
					 result.addObject("message","Unable to delete bill with id='"+id+"', not found!");
				 }				 
			 }			 
			 return result;
		 }
}
