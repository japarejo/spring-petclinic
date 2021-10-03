package org.springframework.samples.petclinic.web.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.samples.petclinic.model.places.PlacesSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="placesFeign",url="https://maps.googleapis.com/maps/api/place/nearbysearch/json")
public interface GooglePlacesApiFeign {

	@GetMapping()
	PlacesSearch search(@RequestParam("key") String key,
						@RequestParam("location") String location, // e.g.: -33.8670522%2C151.1957362
						@RequestParam("keyword") String keyword,
						@RequestParam("radius") int meters,
						@RequestParam("type") String type	// Supported types available at: https://developers.google.com/maps/documentation/places/web-service/supported_types?hl=es_419
						);
}
