package org.springframework.samples.petclinic.web.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.model.Planet;
import org.springframework.samples.petclinic.web.PlanetListing;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="starwars",url="https://swapi.dev/api")
public interface StarwarsAPIFeign {
	@RequestMapping(path="/planets", method = RequestMethod.GET,produces = "application/json")
	PlanetListing getAllPlanets();
	
	@RequestMapping(path ="/planets/{planetId}", method = RequestMethod.GET,produces = "application/json")
	Planet getPlanet(@PathVariable("planetId") int id);

}
