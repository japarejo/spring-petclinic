package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PetIdentityTest {

	@Autowired
	PetService petService;

	@Autowired
	OwnerService ownerService;

	// Grabar una mascota con un nombre distinto de todas las que tenga un
	// propietario
	// Se debería ejecutar el método sin lanzar la excepción, y la mascota de quedar
	// almacenada en la base de datos.
	@Test	
	@Transactional
	public void creacionMascotaCorrectaNombreUnico() {
		// Configuración:
		Pet leonardo = new Pet();
		leonardo.setName("Leonardo (el azul)");
		Owner owner = ownerService.findOwnerById(1);
		owner.addPet(leonardo);
		// Ejecución:
		try {
			petService.savePet(leonardo);
		} catch (DataAccessException | DuplicatedPetNameException e) {
			// Comprobación:
			fail("Se ha lanzado una excepción que no debería, el nombre no está duplicado.");
		}
		Pet foundPet = petService.findPetById(leonardo.getId());
		assertFalse(foundPet == null);
		assertEquals(foundPet.getName(), leonardo.getName());
		assertEquals(foundPet.getOwner(), owner);
	}

	// Grabar dos mascotas nuevas las dos con el mismo nombre:
	// Se debería lanzar la excepción que indica que se violaría la regla de
	// negocio.
	// Solo hay una mascota grabada en la BD con ese nombre.
	@Test
	@Transactional
	public void creacionErroneaMascotaConNombreRepetido() {
		// Configuración:
		Pet donatello = new Pet();
		donatello.setName("Donatello");
		Owner owner = ownerService.findOwnerById(1);
		owner.addPet(donatello);
		try {
			petService.savePet(donatello);
		} catch (DataAccessException | DuplicatedPetNameException e) {
			fail("Se ha lanzado una excepción que no debería, el nombre no está duplicado.");
		}
		Pet donatello2 = new Pet();
		donatello2.setName("Donatello");
		owner.addPet(donatello2);
		assertThrows(DuplicatedPetNameException.class, () ->
 	    				petService.savePet(donatello2)); 
	}		

	// Grabar la misma mascota con el mismo nombre (una mascota que ya exista):
	// No se debería lanzar la excepción y los cambios especificados deberían
	// reflejarse
	// en la base de datos (el nombre no cambia.)
	@Test
	@Transactional
	public void creacionErroneaMascotaConNombreExistente() {
		Owner owner = ownerService.findOwnerById(1);
		Pet pet=owner.getPets().get(0);
		LocalDate date=LocalDate.ofYearDay(1990, 3);
		pet.setBirthDate(date);
		try {
			petService.savePet(pet);
		} catch (DataAccessException | DuplicatedPetNameException e) {
			fail("No debería lanzarse esta excepción");
		}
		
		List<Pet> pets=petService.findPetsByName("Leo");
		assertEquals(1,pets.size());
		assertEquals(date,pets.get(0).getBirthDate());
	}

	// Intentar grabar una mascota sin nombre (con el nombre vacío)
	// Se lanza la exception por parte del servicio, y la mascota no se ha grabado
	// en la BD.
	@Test
	@Transactional
	public void creacionErroneMascotaSinNombre() {
		Pet desconocido=new Pet();
		desconocido.setName("");
		Owner owner = ownerService.findOwnerById(1);
		owner.addPet(desconocido);
		
		assertThrows(Exception.class,
				()-> petService.savePet(desconocido));
		
	}
	/*// Configuración:
		Pet leonardo = new Pet();
		leonardo.setName("Leo");
		Owner owner = ownerService.findOwnerById(1);
		owner.addPet(leonardo);
		//
		assertThrows(DuplicatedPetNameException.class, () -> petService.savePet(leonardo));
	}*/
}
