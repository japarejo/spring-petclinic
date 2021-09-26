package org.springframework.samples.petclinic.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMPQConfiguration {
	@Bean
	public Queue myQueue() {
	    return new Queue("myFootballQueue", false);
	}
}
