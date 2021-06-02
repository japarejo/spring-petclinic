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
	
	public Optional<Bill> findById(Integer id){	
		return br.findById(id);
	}
	
	public void remove(Bill b) {
		br.delete(b);
	}
	
}
