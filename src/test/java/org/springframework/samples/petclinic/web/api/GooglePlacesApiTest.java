package org.springframework.samples.petclinic.web.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.places.Place;
import org.springframework.samples.petclinic.model.places.PlacesSearch;
import org.springframework.samples.petclinic.web.api.client.GooglePlacesApiFeign;

@SpringBootTest
public class GooglePlacesApiTest {

	@Value("${google.places.api.key}")
	String key;
	
	@Autowired
	GooglePlacesApiFeign googlePlaces;
	
	@Test
	public void testSearchNearby() {
		String location="37.3562552%2C-5.9858614"; // Reina Mercedes
		String keyword="tapas";
		String type="restaurant";
		PlacesSearch searchResults=googlePlaces.search(key, location, keyword, 500, type);
		assertNotNull(searchResults);
		assertNotNull(searchResults.getResults());
		assertFalse(searchResults.getResults().isEmpty());
		for(Place p:searchResults.getResults())
			System.out.println(p.getName());
			
	}
}
