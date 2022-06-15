package org.springframework.samples.microservicechasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BillsMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(BillsMicroservice.class, args);
	}

}
