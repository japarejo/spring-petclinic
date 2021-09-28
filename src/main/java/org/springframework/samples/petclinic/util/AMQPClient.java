package org.springframework.samples.petclinic.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.samples.petclinic.configuration.AMPQConfiguration;
import org.springframework.stereotype.Component;

@Component
public class AMQPClient {
	@RabbitListener(queues = AMPQConfiguration.topicExchangeName)
	public void neutral(String in) {
		System.out.println("NEUTRAL : "+in);
	}
	/*
	@RabbitListener(queues = AMPQConfiguration.BETIS_QUEUE_NAME)
	public void BETICO(String in) {
	    System.out.println("BETICO : " + in);
	}
	
	@RabbitListener(queues = AMPQConfiguration.SEVILLA_QUEUE_NAME)
	public void SEVILLISTA(String in) {
	    System.out.println("SEVILLISTA : " + in);
	}
	*/
}
