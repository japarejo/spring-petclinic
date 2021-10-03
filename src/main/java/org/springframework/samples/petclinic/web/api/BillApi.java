package org.springframework.samples.petclinic.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;

public interface BillApi {

	List<Bill> findAll();

	Bill findById(Integer id);

	ResponseEntity<Bill> createBill(Bill newBill, BindingResult br);

	void updateBill(Integer id, Bill newBill, BindingResult br);

	void deleteBill(Integer id);

}