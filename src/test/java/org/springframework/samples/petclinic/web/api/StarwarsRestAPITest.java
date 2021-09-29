package org.springframework.samples.petclinic.web.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.samples.petclinic.model.Planet;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class StarwarsRestAPITest {
	
	@Test
	public void testPlanets() {
		RestTemplate restTemplate=new RestTemplate();
		Planet result=restTemplate.getForObject("https://swapi.dev/api/planets/1/?format=json", Planet.class);
		assertNotNull(result);
		System.out.println(result.getName()+" mass:"+result.getGravity());
		
		try {		
			restTemplate.delete("https://swapi.dev/api/planets/2");
			restTemplate.postForLocation("", null);
		}catch(RestClientException ex) {
			ex.printStackTrace();
		}
	}
}
