package org.springframework.samples.microservicechasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway {

	public static void main(String[] args) {
		SpringApplication.run(Gateway.class, args);
	}

	@Bean 
	public RouteLocator myRoutes(RouteLocatorBuilder builder) { 
		return builder.routes()
				.route("bills-route",r -> r
						.path("/api/v1/bills**")
						.filters(f -> f.addResponseHeader("Hello", "World"))
						.uri("http://127.0.0.1:8095"))
				.route("monolith", r->r
						.path("/monolith/**")
						.filters(f -> f.rewritePath("^/monolith", ""))
						.uri("http://localhost:8080"))								
	        .build(); 
	} 

	
}
