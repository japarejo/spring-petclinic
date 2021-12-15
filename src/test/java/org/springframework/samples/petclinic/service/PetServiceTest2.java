package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PetServiceTest2 {

	@Autowired
	PetService petService;
	
	@Test
	public void prueba() {
		// Configuración:
		int oraculo=6; // Seis tipos de mascotas
		// Ejecución:
		Collection<PetType> petTypes=petService.findPetTypes();
		// Comprobación:
		assertEquals(oraculo,petTypes.size());				
	}
	
}
