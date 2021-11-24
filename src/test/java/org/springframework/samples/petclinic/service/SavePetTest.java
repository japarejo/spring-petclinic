package org.springframework.samples.petclinic.service;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SavePetTest {

	@Autowired
	PetService petService;
	
	@Autowired
	OwnerService ownerService;
	
	
	@Test
	@Transactional
	public void saveNewPetShouldWork() {
		//CONFIGURACIÓN
		Pet newPet=new Pet();
		newPet.setName("Ficifur");
		Owner owner=ownerService.findOwnerById(1);
		PetType petType=petService.findPetTypes().iterator().next();
		newPet.setType(petType);
		newPet.setOwner(owner);
		owner.addPet(newPet);
		
		try {
			// EJECUCIÓN
			petService.savePet(newPet);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicatedPetNameException e) {
			
			// COMPROBACIÓN:
			fail("The service is throwing an exception!");
		}	
	}
	
	@Test
	public void shouldNotSaveAPetWithTheSameName() {
		//CONFIGURACIÓN
		Pet newPet = new Pet();
		Owner owner=ownerService.findOwnerById(1);
		assumeTrue(!owner.getPets().isEmpty());
		String name=owner.getPets().get(0).getName();
		newPet.setName(name);
		PetType petType=petService.findPetTypes().iterator().next();
		newPet.setType(petType);
		newPet.setOwner(owner);
		owner.addPet(newPet);	
		
		// EJECUCIÓN & COMPROBACIÓN:		
		assertThrows(
				DuplicatedPetNameException.class, 				
				() -> petService.savePet(newPet));
		
	}
	
	
}
