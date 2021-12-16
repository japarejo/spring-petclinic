package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;

@ExtendWith(MockitoExtension.class)
public class PetIndentityTestIsolated {

	PetService petService;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	VisitRepository visitRepository;
	
	@Test
	public void testAddPetWithUniqueNameCorrect() {
		//Configuración:
		petService=new PetService(petRepository,visitRepository);
		Pet pet=new Pet();
		String name="Rafael (el rojo)";
		pet.setName(name);
		Owner owner=new Owner();		
		owner.addPet(pet);				
		// Ejecución:
		try {
			petService.savePet(pet);
		} catch (DataAccessException | DuplicatedPetNameException e) {
		// Comprobación:
			fail("Esta excepción no se debería lanzar");
		}
		verify(petRepository).save(any(Pet.class));		
	}
	
	@Test
	public void testAddPetWithDuplicatedNameNegative() {
		//Configuración:
		petService=new PetService(petRepository,visitRepository);
		Pet pet=new Pet();
		pet.setId(1);
		String name="Rafael (el rojo)";
		pet.setName(name);
		Owner owner=new Owner();		
		owner.addPet(pet);				
		// Ejecución:
		try {
			petService.savePet(pet);
		} catch (DataAccessException | DuplicatedPetNameException e) {
		
			fail("Esta excepción no se debería lanzar");
		}
		verify(petRepository).save(any(Pet.class));
		
		Pet pet2=new Pet();
		pet2.setId(2);
		pet2.setName(pet.getName());
		owner.addPet(pet2);
		// Comprobación:
		assertThrows(DuplicatedPetNameException.class,
					()-> petService.savePet(pet2));
		
		verify(petRepository,times(1)).save(any(Pet.class));
	}
	
}
