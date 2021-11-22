package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BillController {
	
	@Autowired
	BillService billService;
	
	
	@GetMapping("/bills")
	public ModelAndView showAllBills(){
		ModelAndView result=new ModelAndView();
		result.setViewName("bills/ListBills");
		
		List<Bill> bills=billService.getAllBills();
		result.addObject("bills", bills);
		return result;
	}
	
	@GetMapping("/bills/create")
	public ModelAndView createNewBill() {
		ModelAndView result=new ModelAndView();
		result.setViewName("bills/editBill");
		result.addObject("bill",new Bill());
		return result;
	}
	
	@PostMapping("/bills/save")
	public ModelAndView saveBill(@Valid Bill b, BindingResult r) {
		if(r.hasErrors()) {
			ModelAndView m=new ModelAndView("bills/editBill");
			m.addObject("bill",b);
			return m;
		}else {
			billService.saveBill(b);
			return showAllBills();
		}
	}
	
	@GetMapping("/bills/edit/{id}")
	public ModelAndView editBill(@PathVariable("id") Integer id) {
		ModelAndView m=new ModelAndView("bills/editBill");
		Bill b=billService.findBill(id);
		m.addObject("bill",b);
		return m;
	}
	
	@GetMapping("/bills/delete/{id}")
	public ModelAndView deleteBill(@PathVariable("id") Integer id) {
		billService.delete(id);
		return showAllBills();
	}
	
}
