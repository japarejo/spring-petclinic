package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.repository.BillRepository;
import org.springframework.stereotype.Service;

@Service
public class BillService {

	@Autowired
	BillRepository br;
	
	public void save(Bill b){
		br.save(b);
	}
	
	public List<Bill> findAll(){
		return br.findAll();
	}
	
	public Bill findById(Integer id) {
		Bill result=null;
		Optional<Bill> b=br.findById(id);
		if(b.isPresent())
			result=b.get();
		return result;
	}

	public void deleteById(Integer id) {
		br.deleteById(id);		
	}
	
}
