package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PetIdentityTest {

	@Autowired
	PetService petService;
	
	@Autowired
	OwnerService ownerService;
	
	// Grabar una mascota con un nombre distinto de todas las que tenga un propietario
	// Se debería ejecutar el método sin lanzar la excepción, y la mascota de quedar
	// almacenada en la base de datos.
	@Test
	public void creacionMascotaCorrectaNombreUnico() {
			
	}
	
	// Grabar dos mascotas nueves las dos con el mismo nombre:
	// Se debería lanzar la excepción que indica que se violaría la regla de negocio.
	// Solo hay una mascota grabada en la BD con ese nombre.
	@Test
	public void creacionErroneaMascotaConNombreRepetido() {
		
	}
	// Grabar la misma mascota con el mismo nombre (una mascota que ya exista):
	// No se debería lanzar la excepción y los cambios especificados deberían reflejarse
	// en la base de datos (el nombre no cambia.)	
	@Test
	public void creacionErroneaMascotaConNombreExistente() {
	}
	
	// Intentar grabar  una mascota sin nombre (con el nombre vacío)
	// Se lanza la exception por parte del servicio, y la mascota no se ha grabado en la BD.
	@Test
	public void creacionErroneMascotaSinNombre() {
		
	}
}
