package org.springframework.samples.petclinic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.web.PetTypeFormatter;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class PetFormatterTest {

	@Mock
	PetService petService;
	
	PetTypeFormatter formatter;
	
	@BeforeEach
	public void setup() {
		System.out.println("Inicializando el formatter");
		formatter=new PetTypeFormatter(petService);
	}
	
	@Test
	@Transactional
	public void printTest() {
		PetType pt=new PetType();
		pt.setName("Tortuga");
		
		String result=formatter.print(pt,Locale.getDefault());
		
		assertEquals("Tortuga",result);
		
	}
	
	@Test
	public void parseTest() {
		//Configuración:
		ArgumentMatcher<Integer> ami=new ArgumentMatcher<Integer>() {
			@Override
			public boolean matches(Integer argument) {
				return argument>12 && argument<22;
			}
		};
		PetType toBeReturned=new PetType();
		toBeReturned.setName("dog");
		List<PetType> mascotas=new ArrayList<PetType>();
		mascotas.add(toBeReturned);	
		when(petService.findPetTypes()).thenReturn(mascotas);
		
		PetType pt=null;
		try {
			pt = formatter.parse("dog", Locale.getDefault());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(pt);
		verify(petService, times(1)).findPetTypes();
	}
	

	
}
