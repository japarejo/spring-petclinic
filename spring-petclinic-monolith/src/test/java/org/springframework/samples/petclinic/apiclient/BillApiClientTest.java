package org.springframework.samples.petclinic.apiclient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BillApiClientTest {

	int calls=10;
	WebClient client=WebClient.create("http://localhost:8095/api/v1");

	@Test
	public void testSequentialCalls() {		
		/*List<Bill> bills=new ArrayList<Bill>();
		RestTemplate restTemplate=new RestTemplate();		
		String resourceUrl
		   = "http://localhost:8095/api/v1/bills/";
		 /*ResponseEntity<String> response
		   = restTemplate.getForEntity(resourceUrl, String.class);
				
		List<Integer>ids=new ArrayList<Integer>();
		for(int i=0;i<calls;i++)
			ids.add(i%2+1);
		long start=System.currentTimeMillis();
		fetchUsersSequentially(ids).collectList().block();
		System.out.println("Sequential execution time "+(System.currentTimeMillis()-start));*/
	}
	
	@Test
	public void testParallelCalls() {		
		/*List<Bill> bills;
		
		List<Integer>ids=new ArrayList<Integer>();
		for(int i=0;i<calls;i++)
			ids.add(i%2+1);
		long start=System.currentTimeMillis();
		fetchUsersInParallel(ids).collectList().doFinally((e)->System.out.println("Parallel execution time "+(System.currentTimeMillis()-start)));*/								
	}

	public Mono<Bill> getBills(Integer id){
		return client.get().uri("/bills/"+id)
		.accept(MediaType.APPLICATION_JSON)
		.retrieve().bodyToMono(Bill.class);
	}
	
	public Flux<Bill> fetchUsersInParallel(List<Integer> userIds) {
	    return Flux.fromIterable(userIds)
	        .parallel()
	        .runOn(Schedulers.boundedElastic())
	        .flatMap(this::getBills)
	        .ordered((u1, u2) -> u2.getId() - u1.getId());
	}
	
	public Flux<Bill> fetchUsersSequentially(List<Integer> userIds) {
	    return Flux.fromIterable(userIds)	        
	    		.flatMap(this::getBills);
	}
}
