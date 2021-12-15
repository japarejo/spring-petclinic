package org.springframework.samples.petclinic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import java.text.ParseException;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.web.PetTypeFormatter;

public class PetTypeFormatterTest {

	static String  nombre = "Tortuga";
	static Locale locale;
	static PetType pt;
	static PetRepository pr;
	static PetService ps;
	static PetTypeFormatter sut;
	
	@BeforeAll
	static void configuration(){
		System.out.println("Configurando....");
		pt = new PetType();
		pt.setName(nombre);
		PetRepository pr=new PetRepository() {
			@Override
			public void save(Pet pet) throws DataAccessException {}
			
			@Override
			public List<PetType> findPetTypes() throws DataAccessException {
				List<PetType> result= new ArrayList<PetType>();				
				result.add(pt);
				return result;
			}
			
			@Override
			public Pet findById(int id) throws DataAccessException {
				return null;
			}
		};
		ps=new PetService(pr,null);
		sut = new PetTypeFormatter(ps);
		Locale locale = Locale.getDefault();
		System.out.println("Configuración finalizada!");
	}
		
	
	@Test
	public void printTest() {
		// Configuración:		
		String oraculo=nombre;
		// Ejecución:
		String result = sut.print(pt, locale);
		// Comprobación:
		assertEquals(oraculo, result);
	}

	@Test	
	public void parseTestPositivo() {
		
		// Configuración:			
		PetType oraculo=pt;
		Locale locale = Locale.getDefault();
		PetType result=null;
		// Ejecución:		
		try {
			result = sut.parse(nombre, locale);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Comprobación:
		assertEquals(oraculo, result);		
	}
	
	
	@Test
	public void parseTestNegativo() {
		// Configuración:		
		PetType oraculo=null;
		// Ejecución:	
		// Comprobación:
		assertThrows(
				ParseException.class,
				() -> sut.parse("Camaleón", locale)
				);
			
	}
}
