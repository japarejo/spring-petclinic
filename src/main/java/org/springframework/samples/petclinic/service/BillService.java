package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.repository.BillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillService {
	@Autowired
	BillRepository billRepo;
	
	@Transactional(readOnly = true)
	public List<Bill> getAllBills(){
		return billRepo.findAll();
	}
	
	
	public void saveBill(Bill b) {
		billRepo.save(b);
	}
	
	public Bill findBill(Integer id) {
		Bill result=null;
		Optional<Bill> b=billRepo.findById(id);
		if(b.isPresent())
			result=b.get();
		return result;
	}


	public void delete(Integer id) {
		billRepo.deleteById(id);		
	}
}
