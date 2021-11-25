package org.springframework.samples.petclinic.service;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class SavePetTest {

	@Mock
	PetRepository petRepo;

	
	
	@Test
	@Transactional
	public void saveNewPetShouldWork() {
		//CONFIGURACIÓN
		Pet newPet=new Pet();
		newPet.setName("Ficifur");
		Owner owner=new Owner();
		PetType petType=new PetType();
		newPet.setType(petType);
		newPet.setOwner(owner);
		owner.addPet(newPet);
		PetService petService=new PetService(petRepo,null);
				
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
		Pet newPet=new Pet();
		newPet.setName("Ficifur");
		Owner owner=new Owner();
		PetType petType=new PetType();
		newPet.setId(49);
		newPet.setType(petType);
		newPet.setOwner(owner);
		owner.addPet(newPet);
		PetService petService=new PetService(petRepo,null);
		
		try {
			// EJECUCIÓN
			petService.savePet(newPet);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (DuplicatedPetNameException e) {
		}
		Pet newPet2=new Pet();
		newPet2.setName("Ficifur");
		newPet2.setOwner(owner);
		newPet2.setType(petType);
		owner.addPet(newPet2);
		
		// EJECUCIÓN & COMPROBACIÓN:		
		assertThrows(
				DuplicatedPetNameException.class, 				
				() -> petService.savePet(newPet2));
		
	}
	
	
}
