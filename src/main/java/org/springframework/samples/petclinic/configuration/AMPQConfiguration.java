package org.springframework.samples.petclinic.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMPQConfiguration {
	public static final String BETIS_QUEUE_NAME = "VIVA_ER_BETI";
	public static final String SEVILLA_QUEUE_NAME = "EL_PIZJUAN_ES_MI_HOGAR";
	public static final String topicExchangeName="myFootballQueue";
	@Bean
	public Queue myQueue() {
	    return new Queue(topicExchangeName, false);
	}
	
	
	/*
	@Bean
	public Declarables topicBindings() {
	    Queue topicQueue1 = new Queue(BETIS_QUEUE_NAME, false);
	    Queue topicQueue2 = new Queue(SEVILLA_QUEUE_NAME, false);

	    TopicExchange topicExchange = new TopicExchange(topicExchangeName);

	    return new Declarables(
	      topicQueue1,
	      topicQueue2,
	      topicExchange,
	      BindingBuilder
	        .bind(topicQueue1)
	        .to(topicExchange).with("*.BETIS.*"),
	      BindingBuilder
	        .bind(topicQueue2)
	        .to(topicExchange).with("*.SEVILLA.*"));
	}*/
}
