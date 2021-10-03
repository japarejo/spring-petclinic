package org.springframework.samples.petclinic.web.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.web.api.client.BillApiFeign;

import feign.FeignException;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BillApiTestWithFeign {
	
	@Autowired
	BillApiFeign billApi;
	
	@Test
	public void testGetAll()
	{
		List<Bill> bills=billApi.getAllBills();
		assertNotNull(bills);
		assertFalse(bills.isEmpty());
	}
	
	@Test
	public void testGeById() {
		Bill bill=billApi.findById(1);
		assertNotNull(bill);
		assertEquals(bill.getId(),1);
	}
	
	@Test
	public void testCreateBill() {
		Bill bill=new Bill();
		bill.setAmount(20);
		bill.setConcept("Rutinary visit for checking");
		Bill created=billApi.createBill(bill);
		assertNotNull(created);
		assertNotNull(created.getId());
		assertNotNull(billApi.findById(created.getId()));
	}
	
	@Test
	public void testUpdateBill() {
		int id=1;
		String concept="Free Rutinary visit for checking";
		Bill bill=new Bill();
		bill.setAmount(0);
		bill.setConcept(concept);
		bill.setId(id);
		billApi.updateBill(id, bill);
		Bill updated=billApi.findById(id);
		assertNotNull(updated);
		assertEquals(updated.getAmount(),0);
		assertEquals(updated.getConcept(),concept);
	}
	
	
	@Test
	public void testDeleteBill() {
		int id=2;
		billApi.deleteBill(id);
		try {
			Bill b=billApi.findById(id);
			fail("The previous call should have raised an exception!");
		}catch(FeignException fe) {
			assertEquals(fe.status(),HttpStatus.NOT_FOUND.value());
		}
	}
}
