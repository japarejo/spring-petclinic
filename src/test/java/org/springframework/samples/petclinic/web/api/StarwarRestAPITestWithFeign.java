package org.springframework.samples.petclinic.web.api;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Planet;
import org.springframework.samples.petclinic.web.PlanetListing;

@SpringBootTest	
public class StarwarRestAPITestWithFeign {

	@Autowired
	StarwarsAPIFeign starwars;
	
	@Test
	public void pruebaDelFeign() {
		PlanetListing planets=starwars.getAllPlanets();
		assertNotNull(planets);
		assertTrue(planets.getResults().size()>0);
		for(Planet p:planets.getResults()) {
			System.out.println(p.getName());
		}
		for(int i=1;i<=20;i++)
			System.out.println("====>>>"+starwars.getPlanet(i).getName());
	}
}
