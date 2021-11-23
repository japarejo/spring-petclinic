package org.springframework.samples.petclinic.web;





import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.model.PetType;

public class PetTypeFormatterTest {

	@Test
	@Disabled()
	public void printTest() {
		// CONFIGURACIÓN:
		String oraculo="Tortuga";
		PetTypeFormatter ptf=new PetTypeFormatter(null);
		PetType petType=new PetType();
		petType.setName(oraculo);
		Locale locale=Locale.getDefault(); 
				
		// EJECUCIÓN:
		String result=ptf.print(petType, locale);
		
		// COMPROBAR:
		assertEquals(oraculo,result);
								
		
	}
}
