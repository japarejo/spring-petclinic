package org.springframework.samples.microservicechasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceChasisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceChasisApplication.class, args);
	}

}
