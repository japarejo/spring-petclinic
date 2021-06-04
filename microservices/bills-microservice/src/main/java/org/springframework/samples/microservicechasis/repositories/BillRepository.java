package org.springframework.samples.microservicechasis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.microservicechasis.model.Bill;


public interface BillRepository extends CrudRepository<Bill,Integer> {

	public List<Bill> findAll();
}
